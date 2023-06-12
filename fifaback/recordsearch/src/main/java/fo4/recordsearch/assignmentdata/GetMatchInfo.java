package fo4.recordsearch.assignmentdata;

import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.MakeURL;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.dto.PostDto;
import fo4.recordsearch.service.UserRecordService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public class GetMatchInfo {

    private ConnectionFifa connectionFifa = new ConnectionFifa();
    private MakeURL makeURL = new MakeURL();

    public JSONObject getMatchInfo(String matchRecordID) throws ParseException {
        ResponseEntity<String> responseEntity = connectionFifa.makeConnection(makeURL.getMatchRecordidUri(matchRecordID));

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONObject jsonObj = (JSONObject) obj;

        return jsonObj;
    }

    public MatchRecordInfo saveData(JSONObject jsonObject, String nickname) {
        MatchRecordInfo matchRecordInfo = new MatchRecordInfo();
        matchRecordInfo.setDate((String) jsonObject.get("matchDate"));

        JSONArray matchInfo = (JSONArray) jsonObject.get("matchInfo");
        JSONObject info = (JSONObject) matchInfo.get(0);

        if(((String)info.get("nickname")).equals(nickname)){
            JSONObject offInfo = (JSONObject) matchInfo.get(1);
            JSONObject tmp = (JSONObject) offInfo.get("shoot");

            matchRecordInfo.setMatchInfo((JSONObject) matchInfo.get(0));
            matchRecordInfo.setOppNickname((String) offInfo.get("nickname"));
            matchRecordInfo.setOppGoal((Long) tmp.get("goalTotal"));
        }else{
            JSONObject tmp = (JSONObject) info.get("shoot");

            matchRecordInfo.setOppNickname((String)info.get("nickname"));
            matchRecordInfo.setOppGoal((Long) tmp.get("goalTotal"));
            matchRecordInfo.setMatchInfo((JSONObject) matchInfo.get(1));
        }

        return matchRecordInfo;
    }
}
