package fo4.recordsearch.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fo4.recordsearch.MakeHeader;
import fo4.recordsearch.MakeURL;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.dto.PostDto;
import fo4.recordsearch.service.SavePostDtoList;
import fo4.recordsearch.service.UserRecordService;
import lombok.extern.slf4j.Slf4j;

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
    private SavePostDtoList savePostDtoList;
    private String nickname;

    public recordSearchController(UserRecordService userRecordService, SavePostDtoList savePostDtoList) {
        this.userRecordService = userRecordService;
        this.savePostDtoList = savePostDtoList;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/userInformation")
    public PostDto.userRecord getAccessId(@RequestParam String nickname) throws IOException, ParseException {
        this.nickname = nickname;
        return userRecordService.getData(nickname);
    }


}
