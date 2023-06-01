package fo4.recordsearch.service;

import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.repository.RecordRepository;
import fo4.recordsearch.repository.TempRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRecordService {
    private RecordRepository recordRepository;
    public UserRecordService(TempRepository tempRepository){
        recordRepository = tempRepository;
    }
    public void save(UserInfo userInfo){
        recordRepository.save(userInfo);
    }
}
