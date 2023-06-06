package fo4.recordsearch.service;

import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.repository.RecordRepository;
import fo4.recordsearch.repository.TempRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserRecordService {
    private RecordRepository recordRepository;
    public UserRecordService(TempRepository tempRepository){
        recordRepository = tempRepository;
    }
    public void save(UserInfo userInfo){
        recordRepository.save(userInfo);
    }

    public void matchRecordHandling(JSONObject jsonObject){
        MatchRecordInfo matchRecordInfo = saveData(jsonObject);
        recordRepository.matchRecordSave(matchRecordInfo);

        log.info("matchData = {}", recordRepository.getMatchRecordInfo());
        log.info("UserInfo = {}", recordRepository.getUserInfo());

        // 포에이션 정보 확인
        log.info("playerFormation = {}", getFormationInfo(getPlayerPositionInfo(recordRepository.getMatchRecordInfo())));
    }

    private static MatchRecordInfo saveData(JSONObject jsonObject) {
        MatchRecordInfo matchRecordInfo = new MatchRecordInfo();
        matchRecordInfo.setDate((String) jsonObject.get("matchDate"));
        JSONArray matchInfo = (JSONArray) jsonObject.get("matchInfo");
        JSONObject oppInfo = (JSONObject) matchInfo.get(0);
        matchRecordInfo.setOppNickname((String) oppInfo.get("nickname"));

//        log.info("tmptmp : {}", (Long) new JSONObject((Map) oppInfo.get("shoot")).get("goalTotal"));

        JSONObject tmp = (JSONObject)  oppInfo.get("shoot");
        matchRecordInfo.setOppGoal((Long) tmp.get("goalTotal"));
        matchRecordInfo.setMatchInfo((JSONObject) matchInfo.get(1));
        return matchRecordInfo;
    }
    public List<Long> getPlayerPositionInfo(MatchRecordInfo matchRecordInfo){

        List<Long> playerPosition = new ArrayList<>();
        JSONArray players = (JSONArray) matchRecordInfo.getMatchInfo().get("player");

        for (int i = 0; i < players.size(); i++){
            JSONObject player = (JSONObject) players.get(i);
            playerPosition.add((Long) player.get("spPosition"));
        }

        return playerPosition;
    }

    public String getFormationInfo(List<Long> positionList) {
        int GK = 0;
        int DF = 0;
        int DM = 0;
        int CM = 0;
        int AM = 0;
        int ST = 0;
        int FW = 0;

        for (int i = 0; i < positionList.size(); i++) {
            Long playerPosition = positionList.get(i);
            if (playerPosition == 0) {
                GK++;
            } else if (1 <= playerPosition && playerPosition <= 8) {
                DF++;
            } else if (9 <= playerPosition && playerPosition <= 11) {
                DM++;
            } else if (12 <= playerPosition && playerPosition <= 16) {
                CM++;
            } else if (17 <= playerPosition && playerPosition <= 19) {
                AM++;
            } else if (20 <= playerPosition && playerPosition <= 22) {
                FW++;
            } else if (23 <= playerPosition && playerPosition <= 27) {
                ST++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (DF > 0) sb.append(DF);
        if (DM > 0) sb.append(DM);
        if (CM > 0) sb.append(CM);
        if (AM > 0) sb.append(AM);
        if (FW > 0) sb.append(FW);
        if (ST > 0) sb.append(ST);


        String result = sb.toString();

        log.info("Formation : {}", result);
        return result;
    }
}
