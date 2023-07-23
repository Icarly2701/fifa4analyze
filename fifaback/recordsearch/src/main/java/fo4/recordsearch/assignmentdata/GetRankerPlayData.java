package fo4.recordsearch.assignmentdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.MakeURL;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public class GetRankerPlayData {
    private ConnectionFifa connectionFifa = new ConnectionFifa();
    private MakeURL makeURL = new MakeURL();

    public JSONArray getRecordInfo(JSONArray myPlayers) throws JsonProcessingException, ParseException {
        ResponseEntity<String> responseEntity = connectionFifa.makeConnection(makeURL.getRankerDataUri(myPlayers));

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONArray jsonObj = (JSONArray) obj;

        return jsonObj;
    }
}
