package fo4.recordsearch.domain;

import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MatchRecordInfo {
    private String date;
    private String oppNickname;
    private Long oppGoal;
    private JSONObject matchInfo;
}
