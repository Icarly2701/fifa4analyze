package fo4.recordsearch.repository;

import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository {

    void save(UserInfo userInfo);
    void matchRecordSave(MatchRecordInfo matchRecordInfo);
    void save(UserInfoEntity userInfoEntity);
    void matchRecordSave(MatchInfoEntity matchInfo);
    Optional<UserInfoEntity> findById(String accessId);

    MatchRecordInfo getMatchRecordInfo();
    UserInfo getUserInfo();

}
