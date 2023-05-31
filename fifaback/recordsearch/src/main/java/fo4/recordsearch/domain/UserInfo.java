package fo4.recordsearch.domain;


import lombok.Data;

@Data
public class UserInfo {
    private String nickname;
    private String accessId;
    private Integer level;
}
