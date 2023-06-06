package fo4.recordsearch.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.service.UserRecordService;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class recordSearchController {

    private final String KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiMjAwMDA6MTAiLCJhY2NvdW50X2lkIjoiNTIwNTI1MzA2IiwiYXV0aF9pZCI6IjQiLCJleHAiOjE3NDI0NjU5NzUsImlhdCI6MTY3OTM5Mzk3NSwibmJmIjoxNjc5MzkzOTc1LCJzZXJ2aWNlX2lkIjoiNDMwMDExNDgxIiwidG9rZW5fdHlwZSI6IkFjY2Vzc1Rva2VuIn0.jCTecd78i5sm9vCsHaVai9Y3Cr8k6lT4joMo0tfCINE";
    private final RestTemplate restTemplate = new RestTemplate();
    private String temp ="https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/matches?matchtype={matchtype}&offset={offset}&limit={limit}";

    private String nickname;
    private String accessId;

    private int matchtype = 50;
    private int offset = 0;
    private int limit = 10;

    private UserRecordService userRecordService;

    public recordSearchController(UserRecordService userRecordService) {
        this.userRecordService = userRecordService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/userInformation")
    public void getAccessId(@RequestParam String nickname) throws IOException, ParseException {
        this.nickname = nickname;

        //uri 생성
        URI uri = getUseridUri(nickname);

        //header 생성
        HttpHeaders httpHeaders = getHttpHeaders();

        // 데이터 가져오기
        HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestMessage, String.class);

        //데이터 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(responseEntity.getBody(), Map.class);

//        UserInfo userInfo = new UserInfo();
//        userInfo.setAccessId(String.valueOf(data.get("accessId")));
//        userInfo.setLevel(Integer.parseInt(data.get("level").toString()));
//        userInfo.setNickname(String.valueOf(data.get("nickname")));
//
//        this.accessId = String.valueOf(data.get("accessId"));
//        userRecordService.save(userInfo);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONObject jsonObj = (JSONObject) obj;

        log.info("accessId = {}", jsonObj.get("accessId"));
        UserInfo userInfo = new UserInfo();
        userInfo.setAccessId(String.valueOf(jsonObj.get("accessId")));
        userInfo.setLevel(Integer.parseInt(jsonObj.get("level").toString()));
        userInfo.setNickname(String.valueOf(jsonObj.get("nickname")));

        this.accessId = String.valueOf(jsonObj.get("accessId"));
        userRecordService.save(userInfo);


        getRecordInfo(userInfo);
        getMatchInfo(userInfo.getMatchId().get(0));

        getBestDivisionInfo();

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", KEY);
        return httpHeaders;
    }

    private URI getUseridUri(String nickname) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/users")
                .queryParam("nickname", nickname)
                .encode()
                .build()
                .toUri();
        return uri;
    }

    private URI getRecordidUri() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/users/"+ accessId +"/matches")
                .queryParam("matchtype", matchtype)
                .queryParam("offset", offset)
                .queryParam("limit", limit)
                .encode()
                .build()
                .toUri();
        return uri;
    }

    private void getRecordInfo(UserInfo userInfo) throws IOException{
        URI uri = getRecordidUri();
        
        HttpHeaders httpHeaders = getHttpHeaders();
        
        HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestMessage, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = objectMapper.readValue(responseEntity.getBody(), List.class);

        userInfo.setMatchId(data);
    }

    private void getMatchInfo(String matchRecordId) throws ParseException {

//        6472172152e06e5fb62d7de3
        URI uri = getMatchRecordidUri(matchRecordId);

        //header 생성
        HttpHeaders httpHeaders = getHttpHeaders();

        // 데이터 가져오기
        HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestMessage, String.class);


        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONObject jsonObj = (JSONObject) obj;
        userRecordService.matchRecordHandling(jsonObj);

    }

    private URI getMatchRecordidUri(String matchRecordId) {
        log.info("matchInfoID : {}", matchRecordId);
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/matches/"+ matchRecordId)
                .encode()
                .build()
                .toUri();
        return uri;
    }

    private void getBestDivisionInfo() throws ParseException {
        URI uri = getBestDivisionRecordidUri();

        //header 생성
        HttpHeaders httpHeaders = getHttpHeaders();

        // 데이터 가져오기
        HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestMessage, String.class);

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
            case 2400 : sb.append("프로3"); break;
            case 2300 : sb.append("프로3"); break;

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

        String userBestDivision = sb.toString();


        log.info("officialMatchDivisionRecord = {}", userBestDivision);  // Long 타입


    }

    private URI getBestDivisionRecordidUri() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/users/" + accessId + "/maxdivision")
                .encode()
                .build()
                .toUri();
        return uri;
    }
}
