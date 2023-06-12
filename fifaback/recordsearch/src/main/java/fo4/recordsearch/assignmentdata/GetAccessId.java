package fo4.recordsearch.assignmentdata;

import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.MakeHeader;
import fo4.recordsearch.MakeURL;
import fo4.recordsearch.domain.UserInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public class GetAccessId {

    private ConnectionFifa connectionFifa = new ConnectionFifa();
    private MakeURL makeURL = new MakeURL();

    public UserInfo getAccessId(String nickname) throws ParseException {
        ResponseEntity<String> responseEntity = connectionFifa.makeConnection(makeURL.getUseridUri(nickname));

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());

        JSONObject jsonObj = (JSONObject) obj;
        UserInfo userInfo = new UserInfo();
        userInfo.setAccessId(String.valueOf(jsonObj.get("accessId")));
        userInfo.setLevel(Integer.parseInt(jsonObj.get("level").toString()));
        userInfo.setNickname(String.valueOf(jsonObj.get("nickname")));
        return userInfo;
    }
}


