package fo4.recordsearch.domain;


import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String nickname;
    private String accessId;
    private Integer level;
    private List<String> matchId;
}
