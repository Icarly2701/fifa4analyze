package fo4.recordsearch.repository;

import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository {

    void save(UserInfo userInfo);
    void matchRecordSave(MatchRecordInfo matchRecordInfo);
    Optional<UserInfo> findById(String accessId);

    MatchRecordInfo getMatchRecordInfo();
    UserInfo getUserInfo();

}
