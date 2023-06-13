package fo4.recordsearch.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matchinfo")
@Getter
@NoArgsConstructor
public class MatchInfoEntity {

    @Column(name = "accessid")
    private String accessid;

    @Id
    @Column(name = "match_id")
    private String match_id;

    @Column(name = "match_result")
    private String match_result;

    @Column(name = "opp_nickname")
    private String opp_nickname;

    @Column(name = "match_score")
    private String match_score;

    @Column(name = "match_date")
    private String match_date;

    public MatchInfoEntity(String accessId, String matchId, String matchResult, String oppNickName, String matchScore, String matchDate) {
        this.accessid = accessId;
        this.match_id = matchId;
        this.match_result = matchResult;
        this.opp_nickname = oppNickName;
        this.match_score = matchScore;
        this.match_date = matchDate;
    }
}
