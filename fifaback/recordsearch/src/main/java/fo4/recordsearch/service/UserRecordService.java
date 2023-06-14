package fo4.recordsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.MakeHeader;
import fo4.recordsearch.MakeURL;
import fo4.recordsearch.assignmentdata.GetAccessId;
import fo4.recordsearch.assignmentdata.GetDivision;
import fo4.recordsearch.assignmentdata.GetMatchInfo;
import fo4.recordsearch.assignmentdata.GetRecordInfo;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.dto.PostDto;
import fo4.recordsearch.repository.JpaRepository;
import fo4.recordsearch.repository.RecordRepository;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserRecordService {
    private RecordRepository recordRepository;

    private GetAccessId getAccessId = new GetAccessId();
    private GetRecordInfo getRecordInfo = new GetRecordInfo();
    private GetMatchInfo getMatchInfo = new GetMatchInfo();
    private GetDivision getDivision = new GetDivision();
    private SavePostDtoList savePostDtoList = new SavePostDtoList();
    private GetFormation getFormation = new GetFormation();
    private String accessId;

    public UserRecordService(JpaRepository jpaRepository){
        recordRepository = jpaRepository;
    }

    public PostDto.userRecord getData(String nickname, String isrenew) throws ParseException, JsonProcessingException {
        UserInfo userInfo = getAccessId.getAccessId(nickname);
        this.accessId = userInfo.getAccessId();
        userInfo.setMatchId(getRecordInfo.getRecordInfo(accessId));
        userInfo.setTier(getDivision.getDivision(accessId));

        if(isrenew.equals("yes")){
            recordRepository.deleteMatchInfo(nickname);
            recordRepository.deleteUserInfo(nickname);
        }

        int j = 0;
        for(int i = 0; i < userInfo.getMatchId().size(); i++) {
            JSONObject matchInfo = getMatchInfo.getMatchInfo(userInfo.getMatchId().get(i));
            MatchRecordInfo matchRecordInfo = getMatchInfo.saveData(matchInfo, userInfo.getNickname());
            if(i == j) {
                JSONArray players = (JSONArray) matchRecordInfo.getMatchInfo().get("player");
                if(players.size() == 0){
                    j++;
                }
                userInfo.setFormation(getFormation.getFormationInfo(matchRecordInfo.getMatchInfo()));
            }
            PostDto.result result = matchRecordHandling(matchRecordInfo);
            MatchInfoEntity matchInfoEntity = getMatchInfoEntity(userInfo, i, result);
            recordRepository.matchRecordSave(matchInfoEntity);
            recordRepository.matchRecordSave(matchRecordInfo);
            savePostDtoList.saveAsList(result);
        }

        UserInfoEntity userInfoEntity = getUserInfoEntity(userInfo);

        recordRepository.save(userInfo);
        recordRepository.save(userInfoEntity);

        List<PostDto.result> resultList = new ArrayList<>(savePostDtoList.getResultList());
        savePostDtoList.clearList();

        return getBuild(userInfo, resultList);
    }

    public PostDto.result matchRecordHandling(MatchRecordInfo matchRecordInfo){
        recordRepository.matchRecordSave(matchRecordInfo);
        SaveRecordService saveRecordService = new SaveRecordService(matchRecordInfo);
        return saveRecordService.record;
    }

    private UserInfoEntity getUserInfoEntity(UserInfo userInfo) {
        UserInfoEntity userInfoEntity = new UserInfoEntity(
                userInfo.getNickname(),
                userInfo.getAccessId(),
                userInfo.getLevel(),
                userInfo.getTier(),
                userInfo.getFormation()
        );
        return userInfoEntity;
    }

    private MatchInfoEntity getMatchInfoEntity(UserInfo userInfo, int i, PostDto.result result) {
        MatchInfoEntity matchInfoEntity = new MatchInfoEntity(
                accessId, userInfo.getMatchId().get(i), result.getResult(), result.getOppnickname(), result.getScore(), result.getTime()
        );
        return matchInfoEntity;
    }

    private PostDto.userRecord getBuild(UserInfo userInfo, List<PostDto.result> resultList) {
        return PostDto.userRecord.builder()
                .nickname(userInfo.getNickname())
                .level(userInfo.getLevel())
                .records(resultList)
                .mypomation(userInfo.getFormation())
                .tier(userInfo.getTier())
                .build();
    }



}
