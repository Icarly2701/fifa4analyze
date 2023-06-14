package fo4.recordsearch.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fo4.recordsearch.MakeHeader;
import fo4.recordsearch.MakeURL;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.dto.PostDto;
import fo4.recordsearch.service.GetRepositoryService;
import fo4.recordsearch.service.SavePostDtoList;
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

    private UserRecordService userRecordService;
    private GetRepositoryService getRepositoryService;
    private SavePostDtoList savePostDtoList;
    private String nickname;

    public recordSearchController(UserRecordService userRecordService, SavePostDtoList savePostDtoList, GetRepositoryService getRepositoryService) {
        this.userRecordService = userRecordService;
        this.savePostDtoList = savePostDtoList;
        this.getRepositoryService = getRepositoryService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/userInformation")
    public PostDto.userRecord getAccessId(@RequestParam String nickname,
                                            @RequestParam String isrenew) throws IOException, ParseException {
        this.nickname = nickname;

        if (isrenew.equals("yes")) {        // 새로고침
            return userRecordService.getData(nickname);
        }
        else{
            log.info("asdfasdf");// 그냥 검색
            return getRepositoryService.getUserInfo(nickname);
        }

    }

}
