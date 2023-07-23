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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private GetAdvice getAdvice;

    private GetWorstPlayer getWorstPlayer;

    private String accessId;

    public UserRecordService(JpaRepository jpaRepository){
        recordRepository = jpaRepository;
    }

    public PostDto.userRecord getData(String nickname, String isrenew) throws ParseException, JsonProcessingException {
        UserInfo userInfo = getAccessId.getAccessId(nickname);
        this.accessId = userInfo.getAccessId();
        userInfo.setMatchId(getRecordInfo.getRecordInfo(accessId));
        userInfo.setTier(getDivision.getDivision(accessId));

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        String formatedNow = now.format(formatter);


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
                GetWorstPlayer getWorstPlayer = new GetWorstPlayer();
                log.info("{}", getWorstPlayer.getWorst(players));
                List<String > worstPlayers = getWorstPlayer.getWorst(players);
                userInfo.setWorstP1(worstPlayers.get(0));
                userInfo.setWorstP2(worstPlayers.get(1));
                userInfo.setWorstP3(worstPlayers.get(2));
                userInfo.setFormation(getFormation.getFormationInfo(matchRecordInfo.getMatchInfo()));
            }
            PostDto.result result = matchRecordHandling(matchRecordInfo);
            MatchInfoEntity matchInfoEntity = getMatchInfoEntity(userInfo, i, result);
            recordRepository.matchRecordSave(matchInfoEntity);
            recordRepository.matchRecordSave(matchRecordInfo);
            savePostDtoList.saveAsList(result);
        }
        getAdvice = new GetAdvice(userInfo.getFormation());
        userInfo.setArch_enemy(getAdvice.getOppFormation());
        userInfo.setAdvice(getAdvice.getAdvice());
        UserInfoEntity userInfoEntity = getUserInfoEntity(userInfo);

        recordRepository.save(userInfo);
        recordRepository.save(userInfoEntity);

        List<PostDto.result> resultList = new ArrayList<>(savePostDtoList.getResultList());
        savePostDtoList.clearList();

        return getBuild(userInfo, resultList, formatedNow);
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
                userInfo.getFormation(),
                userInfo.getArch_enemy(),
                userInfo.getAdvice(),
                userInfo.getWorstP1(),
                userInfo.getWorstP2(),
                userInfo.getWorstP3()
        );
        return userInfoEntity;
    }

    private MatchInfoEntity getMatchInfoEntity(UserInfo userInfo, int i, PostDto.result result) {
        MatchInfoEntity matchInfoEntity = new MatchInfoEntity(
                accessId, userInfo.getMatchId().get(i), result.getResult(), result.getOppnickname(), result.getScore(), result.getTime()
        );
        return matchInfoEntity;
    }

    private PostDto.userRecord getBuild(UserInfo userInfo, List<PostDto.result> resultList, String date) {
        return PostDto.userRecord.builder()
                .nickname(userInfo.getNickname())
                .level(userInfo.getLevel())
                .records(resultList)
                .mypomation(userInfo.getFormation())
                .tier(userInfo.getTier())
                .date(date)
                .worstformation(userInfo.getArch_enemy())
                .playerfix(userInfo.getAdvice())
                .worstP1(userInfo.getWorstP1())
                .worstP2(userInfo.getWorstP2())
                .worstP3(userInfo.getWorstP3())
                .build();
    }



}
