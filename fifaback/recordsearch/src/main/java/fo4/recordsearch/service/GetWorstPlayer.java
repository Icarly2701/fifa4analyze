package fo4.recordsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fo4.recordsearch.assignmentdata.GetPlayerName;
import fo4.recordsearch.assignmentdata.GetRankerPlayData;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.*;

@Slf4j
public class GetWorstPlayer {

    public List<String> getWorst(JSONArray players) throws ParseException, JsonProcessingException {
        Long spid = 0L;
        Long spPosition = 0L;
        JSONArray myPlayers = new JSONArray();
        JSONArray myPlayersNoSub = new JSONArray();
        GetRankerPlayData getRankerPlayData = new GetRankerPlayData();
        GetPlayerName getPlayerName = new GetPlayerName();
        JSONArray nameData = getPlayerName.getPlayerName();
        for(int i = 0; i<players.size(); i++ ){
            JSONObject player = (JSONObject) players.get(i);
            spid = (Long) player.get("spId");
            spPosition = (Long) player.get("spPosition");

            if(spPosition != 28) {
                myPlayersNoSub.add((JSONObject) players.get(i));
                JSONObject myPlayer = new JSONObject();
                myPlayer.put("id", spid);
                myPlayer.put("po", spPosition);
                myPlayers.add(myPlayer);
            }
        }

        JSONArray rankPlayers = getRankerPlayData.getRecordInfo(myPlayers);
        LinkedHashMap<Long, Long> worstPlayer = new LinkedHashMap<>();
        log.info("psize = {}", players.size());
        log.info("rsize = {}", rankPlayers.size());
        for(int i = 0; i<myPlayersNoSub.size(); i++ ){
            JSONObject player = (JSONObject) myPlayersNoSub.get(i);
            JSONObject ranker = (JSONObject) rankPlayers.get(i);
            JSONObject rankData = (JSONObject) ranker.get("status");
            JSONObject playerData = (JSONObject) player.get("status");
            Long total = 0L;

            Long playerShoot =(Long) playerData.get("shoot");
            Long playerDribble = (Long) playerData.get("dribbleTry");
            Long playerPass = (Long) playerData.get("passTry");
            Long playerBlock = (Long) playerData.get("block");
            Long playerTackle = (Long) playerData.get("tackle");

            Double rankerShoot = (Double) rankData.get("shoot");
            Double rankerDribble = (Double) rankData.get("dribbleTry");
            Double rankerPass = (Double) rankData.get("passTry");
            Double rankerBlock = (Double) rankData.get("block");
            Double rankerTackle = (Double) rankData.get("tackle");

            if(playerShoot < rankerShoot) total += (rankerShoot.longValue() - playerShoot);
            if(playerPass < rankerPass) total += (rankerPass.longValue() - playerPass);
            if(playerDribble < rankerDribble) total += (rankerDribble.longValue() - playerDribble);
            if(playerBlock < rankerBlock) total += (rankerBlock.longValue() - playerBlock);
            if(playerTackle < rankerTackle) total += (rankerTackle.longValue() - playerTackle);

            worstPlayer.put((Long) player.get("spId"), total);
        }

        List<Map.Entry<Long, Long>> entryList = new ArrayList<>(worstPlayer.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<Long, Long>>() {
            @Override
            public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        LinkedHashMap<Long, Long> topplayers = new LinkedHashMap<>();
        for(Map.Entry<Long, Long> entry : entryList){
            topplayers.put(entry.getKey(), entry.getValue());
        }

        List<String> result = new ArrayList<>();
        int count = 0;
        for(Map.Entry<Long, Long> entry : topplayers.entrySet()){
            if(count == 3) break;
            log.info("key= {}", entry.getKey());
            int index = binarySearch(nameData, entry.getKey());
            JSONObject name = (JSONObject) nameData.get(index);
            result.add((String)name.get("name"));
            count++;
        }
        log.info("result = {}", result);
        return result;
    }

    private static int binarySearch(JSONArray jsonArray, Long targetId) {
        int left = 0;
        int right = (jsonArray.size() - 1);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            JSONObject obj = (JSONObject) jsonArray.get(mid);
            Long id = (Long) obj.get("id");
            if (id.equals(targetId)) {
                return mid; // 원하는 id를 찾았을 때 해당 인덱스 반환
            } else if (id < targetId) {
                left = mid + 1; // 찾고자 하는 id가 중앙값보다 크다면 오른쪽 반을 탐색
            } else {
                right = mid - 1; // 찾고자 하는 id가 중앙값보다 작다면 왼쪽 반을 탐색
            }
        }

        return -1; // id를 찾지 못했을 때 -1 반환
    }
}
