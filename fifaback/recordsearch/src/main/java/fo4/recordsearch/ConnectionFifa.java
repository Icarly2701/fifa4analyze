package fo4.recordsearch;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class ConnectionFifa {

    private final RestTemplate restTemplate = new RestTemplate();
    private MakeHeader makeHeader = new MakeHeader();

    public ResponseEntity<String> makeConnection(URI uri) {
        //header 생성
        HttpHeaders httpHeaders = makeHeader.getHttpHeaders();

        // 데이터 가져오기
        HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestMessage, String.class);

        return responseEntity;
    }
}
