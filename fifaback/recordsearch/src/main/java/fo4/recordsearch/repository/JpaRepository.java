package fo4.recordsearch.repository;

import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.domain.MatchRecordInfo;
import fo4.recordsearch.domain.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@Slf4j
public class JpaRepository implements RecordRepository {

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
    public List<MatchInfoEntity> getMatchRecordInfo(String access_id) {
        log.info("access_id = {}", access_id);
        List<MatchInfoEntity> list = em.createQuery(
                        "SELECT b FROM MatchInfoEntity b  WHERE b.access_id = :access_id",
                        MatchInfoEntity.class
                ).setParameter("access_id", access_id)
                .getResultList();
        return list;
    }

    @Override
    public Optional<UserInfoEntity> getUserInfo(String nickName) {
        TypedQuery<UserInfoEntity> user = em.createQuery(
                "SELECT b FROM UserInfoEntity b WHERE b.nickname = :nickname",
                UserInfoEntity.class
        ).setParameter("nickname", nickName);
        try{
            UserInfoEntity singleResult = user.getSingleResult();
            return Optional.ofNullable(singleResult);
        }catch(NoResultException e){
            return Optional.ofNullable(null);
        }
    }

    @Override
    public void deleteMatchInfo(String nickname){
        Optional<UserInfoEntity> userInfo = getUserInfo(nickname);
        List<MatchInfoEntity> matchRecordInfo = getMatchRecordInfo(userInfo.get().getAccess_id());
        for(MatchInfoEntity m : matchRecordInfo)
            em.remove(m);
    }

    @Override
    public void deleteUserInfo(String nickname) {
        Optional<UserInfoEntity> userInfo = getUserInfo(nickname);
        em.remove(userInfo.get());
    }


}
