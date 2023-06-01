package fo4.recordsearch.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.service.UserRecordService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Map;

@Controller
public class recordSearchController {

    private final String KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiMjAwMDA6MTAiLCJhY2NvdW50X2lkIjoiNTIwNTI1MzA2IiwiYXV0aF9pZCI6IjQiLCJleHAiOjE3NDI0NjU5NzUsImlhdCI6MTY3OTM5Mzk3NSwibmJmIjoxNjc5MzkzOTc1LCJzZXJ2aWNlX2lkIjoiNDMwMDExNDgxIiwidG9rZW5fdHlwZSI6IkFjY2Vzc1Rva2VuIn0.jCTecd78i5sm9vCsHaVai9Y3Cr8k6lT4joMo0tfCINE";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String tmp = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";

    private String nickname;
    private String accessId;

    private UserRecordService userRecordService;

    public recordSearchController(UserRecordService userRecordService){
        this.userRecordService = userRecordService;
    }

    @GetMapping("/")
    public String home(){return "home";}


    @GetMapping("/userInformation")
    public void getAccessId(@RequestParam String nickname) throws IOException {
            this.nickname = nickname;
            //uri 생성
            URI uri = UriComponentsBuilder
                    .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                    .path("/users")
                    .queryParam("nickname", nickname)
                    .encode()
                    .build()
                    .toUri();

            //header 생성
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("Authorization", KEY);

            // 데이터 가져오기
            HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri,HttpMethod.GET, requestMessage,String.class);

            //데이터 변환
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> data = objectMapper.readValue(responseEntity.getBody(), Map.class);

            UserInfo userInfo = new UserInfo();
            userInfo.setAccessId(data.get("accessId"));
            userInfo.setLevel(Integer.parseInt(data.get("level")));
            userInfo.setNickname(data.get("nickname"));

            this.accessId = data.get("accessId");
            userRecordService.save(userInfo);
    }
}
