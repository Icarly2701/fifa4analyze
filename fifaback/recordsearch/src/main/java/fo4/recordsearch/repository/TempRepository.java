//package fo4.recordsearch.repository;
//
//import fo4.recordsearch.domain.MatchRecordInfo;
//import fo4.recordsearch.domain.UserInfo;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//import java.util.Optional;
//
//@Repository
//public class TempRepository implements RecordRepository{
//    private UserInfo userInfo;
//    private MatchRecordInfo matchRecordInfo;
//
//    @Override
//    public void save(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    @Override
//    public void matchRecordSave(MatchRecordInfo matchRecordInfo) {
//        this.matchRecordInfo = matchRecordInfo;
//    }
//
//    @Override
//    public Optional<UserInfo> findById(String accessId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public MatchRecordInfo getMatchRecordInfo() {
//        return matchRecordInfo;
//    }
//
//    @Override
//    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//}
