package fo4.recordsearch.service;

import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.dto.PostDto;
import org.json.simple.JSONObject;

public class SaveRecordService {

    PostDto.result record;

    public SaveRecordService(MatchRecordInfo matchRecordInfo) {
        record = new PostDto.result();

        JSONObject MatchInfo = matchRecordInfo.getMatchInfo();
        JSONObject MyMatchInfo = (JSONObject) MatchInfo.get("matchDetail");
        JSONObject MyShoot = (JSONObject) MatchInfo.get("shoot");

        record.oppnickname = matchRecordInfo.getOppNickname();
        record.time = matchRecordInfo.getDate();
        record.result = (String) MyMatchInfo.get("matchResult");

        if(record.result.equals("승")){
            record.score = ((Long) MyShoot.get("goalTotal"))+" : "+matchRecordInfo.getOppGoal();
        }else{
            record.score = matchRecordInfo.getOppGoal()+" : "+((Long) MyShoot.get("goalTotal"));
        }
    }
}
