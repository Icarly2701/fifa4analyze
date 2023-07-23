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

    @Column(name = "arch_enemy")
    private String oppFormation;

    @Column(name = "advice")
    private String advice;

    @Column(name = "worstP1")
    private String worstP1;

    @Column(name = "worstP2")
    private String worstP2;

    @Column(name = "worstP3")
    private String worstP3;


    public UserInfoEntity(String nickName, String accessId, int userLevel, String tier, String formation, String arch_enemy, String advice, String worstP1, String worstP2, String worstP3) {
        this.nickname = nickName;
        this.access_id = accessId;
        this.userlevel = userLevel;
        this.tier = tier;
        this.formation = formation;
        this.oppFormation = arch_enemy;
        this.advice = advice;
        this.worstP1 = worstP1;
        this.worstP2 = worstP2;
        this.worstP3 = worstP3;
    }
}
