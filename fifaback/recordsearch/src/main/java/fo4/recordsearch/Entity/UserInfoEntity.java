package fo4.recordsearch.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userinfo")
@Getter
@NoArgsConstructor
public class UserInfoEntity {

    @Column(name = "nickname")
    private String nickname;

    @Id
    @Column(name = "access_id")
    private String access_id;

    @Column(name = "userlevel")
    private int userlevel;

    @Column(name = "tier")
    private String tier;

    @Column(name = "formation")
    private String formation;

    public UserInfoEntity(String nickName, String accessId, int userLevel, String tier, String formation) {
        this.nickname = nickName;
        this.access_id = accessId;
        this.userlevel = userLevel;
        this.tier = tier;
        this.formation = formation;
    }
}
