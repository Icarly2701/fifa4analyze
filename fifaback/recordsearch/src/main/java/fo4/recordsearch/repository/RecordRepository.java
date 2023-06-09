package fo4.recordsearch.repository;

import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository {

    void save(UserInfo userInfo);
    void matchRecordSave(MatchRecordInfo matchRecordInfo);
    void save(UserInfoEntity userInfoEntity);
    void matchRecordSave(MatchInfoEntity matchInfo);
    void deleteMatchInfo(String nickname);
    void deleteUserInfo(String nickname);

    List<MatchInfoEntity> getMatchRecordInfo(String accessId);
    Optional<UserInfoEntity> getUserInfo(String nickName);

}
