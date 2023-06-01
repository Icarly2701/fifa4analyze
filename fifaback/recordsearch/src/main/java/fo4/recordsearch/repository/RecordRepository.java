package fo4.recordsearch.repository;

import fo4.recordsearch.domain.UserInfo;

import java.util.Optional;

public interface RecordRepository {

    void save(UserInfo userInfo);

    Optional<UserInfo> findById(String accessId);

}
