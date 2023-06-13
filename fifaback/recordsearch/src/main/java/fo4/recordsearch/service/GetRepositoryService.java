package fo4.recordsearch.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import fo4.recordsearch.Entity.MatchInfoEntity;
import fo4.recordsearch.Entity.UserInfoEntity;
import fo4.recordsearch.domain.UserInfo;
import fo4.recordsearch.dto.PostDto;
import fo4.recordsearch.repository.JpaRepository;
import fo4.recordsearch.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GetRepositoryService {

    private RecordRepository recordRepository;
    private UserRecordService userRecordService;
    public GetRepositoryService(JpaRepository jpaRepository, UserRecordService userRecordService){

        recordRepository = jpaRepository;
        this.userRecordService = userRecordService;
    }

    public PostDto.userRecord getUserInfo(String nickName) throws ParseException, JsonProcessingException {

        Optional<UserInfoEntity> userInfoEntity = recordRepository.getUserInfo(nickName);
        if (userInfoEntity.isEmpty()){
            return userRecordService.getData(nickName);
        }
        log.info("{}", userInfoEntity);
        PostDto postDto = new PostDto();

        List<PostDto.result> presult = new ArrayList<>();

        List<MatchInfoEntity> matchRecordInfo = recordRepository.getMatchRecordInfo(userInfoEntity.get().getAccess_id());

        for(MatchInfoEntity m : matchRecordInfo){
           PostDto.result result = new PostDto.result();
           result.setOppnickname(m.getOpp_nickname());
           result.setResult(m.getMatch_result());
           result.setTime(m.getMatch_date());
           result.setScore(m.getMatch_score());
           presult.add(result);
        }

        return PostDto.userRecord.builder()
                .nickname(userInfoEntity.get().getNickname())
                .level(userInfoEntity.get().getUserlevel())
                .records(presult)
                .mypomation(userInfoEntity.get().getFormation())
                .tier(userInfoEntity.get().getTier())
                .build();
    }


}
