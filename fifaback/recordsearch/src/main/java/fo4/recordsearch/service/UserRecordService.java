package fo4.recordsearch.service;

import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.repository.RecordRepository;
import fo4.recordsearch.repository.TempRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

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

}
