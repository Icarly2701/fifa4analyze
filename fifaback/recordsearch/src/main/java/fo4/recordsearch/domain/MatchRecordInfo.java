package fo4.recordsearch.domain;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class MatchRecordInfo {
    private String date;
    private String oppNickname;
    private Long oppGoal;

    private JSONObject matchInfo;
}
