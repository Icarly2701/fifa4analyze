package fo4.recordsearch.domain;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
public class UserInfo {
    private String nickname;
    private String accessId;
    private Integer level;
    private List<String> matchId;
    private String tier;
    private String formation;
}
