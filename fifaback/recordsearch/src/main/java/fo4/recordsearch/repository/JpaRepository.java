package fo4.recordsearch.repository;

import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public class JpaRepository implements RecordRepository{


    private UserInfo userInfo;
    private MatchRecordInfo matchRecordInfo;
    private final EntityManager em;

    public JpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void matchRecordSave(MatchRecordInfo matchRecordInfo) {
        this.matchRecordInfo = matchRecordInfo;
    }

    @Override
    public void save(UserInfoEntity userInfoEntity) {
        em.persist(userInfoEntity);
    }

    @Override
    public void matchRecordSave(MatchInfoEntity matchInfoEntity) {
        em.persist(matchInfoEntity);
    }

    @Override
    public Optional<UserInfoEntity> findById(String accessId) {
//        List<String> matchIdList = Optional.ofNullable(em.find(MatchInfoEntity.class, accessId))
//                .orElseGet(ArrayList::new);
        return null;
    }

    @Override
    public MatchRecordInfo getMatchRecordInfo() {
        return null;
    }

    @Override
    public UserInfo getUserInfo() {
        return null;
    }


    public Optional<UserInfoEntity> getUserInfo(String accessId) {
        UserInfoEntity userInfoEntity = em.find(UserInfoEntity.class, accessId);
        return Optional.ofNullable(userInfoEntity);
    }
}
