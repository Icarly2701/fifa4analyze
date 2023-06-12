package fo4.recordsearch.assignmentdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fo4.recordsearch.ConnectionFifa;
import fo4.recordsearch.MakeURL;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.dto.PostDto;
import fo4.recordsearch.service.SaveRecordService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetRecordInfo {

    private ConnectionFifa connectionFifa = new ConnectionFifa();
    private MakeURL makeURL = new MakeURL();

    public List<String> getRecordInfo(String accessId) throws JsonProcessingException {
        ResponseEntity<String> responseEntity = connectionFifa.makeConnection(makeURL.getRecordidUri(accessId));
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = objectMapper.readValue(responseEntity.getBody(), List.class);
        return data;
    }



}
