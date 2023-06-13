package fo4.recordsearch.assignmentdata;

import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.MakeURL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public class GetDivision {
    private MakeURL makeURL = new MakeURL();
    private ConnectionFifa connectionFifa = new ConnectionFifa();
    public String getDivision(String accessId) throws ParseException {
        ResponseEntity<String> responseEntity = connectionFifa.makeConnection(makeURL.getBestDivisionRecordidUri(accessId));

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());

        JSONArray matchDivisionRecord = (JSONArray) obj;
        JSONObject officialMatchDivisionRecord = (JSONObject) matchDivisionRecord.get(0);


        Long userBestDivisionId = (Long) officialMatchDivisionRecord.get("division");
        int temp = userBestDivisionId.intValue();

        StringBuilder sb = new StringBuilder();

        switch (temp){
            case 3100 : sb.append("유망주3"); break;
            case 3000 : sb.append("유망주2"); break;
            case 2900 : sb.append("유망주1"); break;

            case 2800 : sb.append("세미프로3"); break;
            case 2700 : sb.append("세미프로2"); break;
            case 2600 : sb.append("세미프로1"); break;

            case 2500 : sb.append("프로3"); break;
            case 2400 : sb.append("프로2"); break;
            case 2300 : sb.append("프로1"); break;

            case 2200 : sb.append("월드클래스3"); break;
            case 2100 : sb.append("월드클래스2"); break;
            case 2000 : sb.append("월드클래스1"); break;

            case 1300 : sb.append("챌린지3"); break;
            case 1200 : sb.append("챌린지2"); break;
            case 1100 : sb.append("챌린지1"); break;

            case 1000 : sb.append("슈퍼챌린지"); break;
            case 900 : sb.append("챔피언스"); break;
            case 800 : sb.append("슈퍼챔피언스"); break;
        }

        return sb.toString();
    }
}
