package fo4.recordsearch.assignmentdata;

import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.MakeURL;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public class GetPlayerName {

    private ConnectionFifa connectionFifa = new ConnectionFifa();
    private MakeURL makeURL = new MakeURL();

    public JSONArray getPlayerName() throws ParseException {
        ResponseEntity<String> responseEntity = connectionFifa.makeConnection(makeURL.getNameDataUri());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONArray jsonObj = (JSONArray) obj;

        return jsonObj;
    }
}
