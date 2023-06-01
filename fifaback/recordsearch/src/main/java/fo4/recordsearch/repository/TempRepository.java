package fo4.recordsearch.repository;

import fo4.recordsearch.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class TempRepository implements RecordRepository{
    private UserInfo userInfo;

    @Override
    public void save(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Optional<UserInfo> findById(String accessId) {
        return Optional.empty();
    }
}
