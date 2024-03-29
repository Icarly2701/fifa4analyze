package fo4.recordsearch;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
public class MakeURL {

    private int matchtype = 50;
    private int offset = 0;
    private int limit = 10;

    public URI getMatchRecordidUri(String matchRecordId) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/matches/"+ matchRecordId)
                .encode()
                .build()
                .toUri();
        return uri;
    }

    public URI getUseridUri(String nickname) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/users")
                .queryParam("nickname", nickname)
                .encode()
                .build()
                .toUri();
        return uri;
    }

    public URI getRecordidUri(String accessId) {
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

    public URI getBestDivisionRecordidUri(String accessId) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0")
                .path("/users/" + accessId + "/maxdivision")
                .encode()
                .build()
                .toUri();
        return uri;
    }

    public URI getRankerDataUri(JSONArray array){
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.nexon.co.kr/fifaonline4/v1.0/rankers/")
                .path("status")
                .queryParam("matchtype", 50)
                .query("players=" + array.toString())
                .build().toUri();
        return uri;
    }

    public URI getNameDataUri(){
        return UriComponentsBuilder.fromUriString("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json")
                .encode()
                .build()
                .toUri();
    }
}
