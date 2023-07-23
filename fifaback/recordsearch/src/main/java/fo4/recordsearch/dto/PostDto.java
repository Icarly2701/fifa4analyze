package fo4.recordsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class PostDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class userRecord{
        public String nickname;
        public Integer level;
        public String tier;
        public String mypomation;
        public List<PostDto.result> records;
        public String date;
        public String worstpomation;
        public String playfix;
        public String worstP1;
        public String worstP2;
        public String worstP3;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class result{
        public String oppnickname;
        public String result;
        public String score;
        public String time;
    }


}

