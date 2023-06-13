package fo4.recordsearch.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
