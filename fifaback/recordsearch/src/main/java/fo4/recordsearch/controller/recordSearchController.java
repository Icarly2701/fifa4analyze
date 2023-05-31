package fo4.recordsearch.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Controller
public class recordSearchController {

    private final String KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiMjAwMDA6MTAiLCJhY2NvdW50X2lkIjoiNTIwNTI1MzA2IiwiYXV0aF9pZCI6IjQiLCJleHAiOjE3NDI0NjU5NzUsImlhdCI6MTY3OTM5Mzk3NSwibmJmIjoxNjc5MzkzOTc1LCJzZXJ2aWNlX2lkIjoiNDMwMDExNDgxIiwidG9rZW5fdHlwZSI6IkFjY2Vzc1Rva2VuIn0.jCTecd78i5sm9vCsHaVai9Y3Cr8k6lT4joMo0tfCINE";
    private  final RestTemplate restTemplate;

    private final String tmp = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
    @GetMapping("/")
    public String home(){return "home";}


    @GetMapping("/userInformation")
    public String getAccessId(@RequestParam String nickname){


//        try{
//            String nickName = nickname;
//            URL url = new URL("https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=" + nickName);
//            BufferedReader bf;
//            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            String result = bf.readLine();
//            System.out.println("result = " + result);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return "/userInformation/level";
    }
}
