package fo4.recordsearch.repository;

import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@Slf4j
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
    public List<MatchInfoEntity> getMatchRecordInfo(String accessId) {
        List<MatchInfoEntity> list = em.createQuery(
                "SELECT * FROM matchinfo b WHERE b.accessId = :accessId",
                MatchInfoEntity.class
        ).setParameter("accessId", accessId)
                        .getResultList();

        log.info("{}", list);
        return list;

    }


    public Optional<UserInfoEntity> getUserInfo(String nickName) {
        UserInfoEntity userInfoEntity = em.find(UserInfoEntity.class, nickName);
        return Optional.ofNullable(userInfoEntity);
    }
}
