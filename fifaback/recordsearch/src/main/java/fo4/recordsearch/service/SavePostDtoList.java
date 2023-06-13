package fo4.recordsearch.service;

import fo4.recordsearch.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavePostDtoList {
    private SaveRecordService saveRecordService;

    private List<PostDto.result> resultList = new ArrayList<>();

    public List<PostDto.result> getResultList() {
        return resultList;
    }

    public void saveAsList(PostDto.result record){
        resultList.add(record);
    }

    public void clearList(){resultList.clear();}
}
