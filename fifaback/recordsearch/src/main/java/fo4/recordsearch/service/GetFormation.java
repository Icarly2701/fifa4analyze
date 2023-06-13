package fo4.recordsearch.service;

import fo4.recordsearch.domain.MatchRecordInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class GetFormation {

    private List<Long> getPlayerPositionInfo(JSONObject matchRecordInfo){

        List<Long> playerPosition = new ArrayList<>();
        JSONArray players = (JSONArray) matchRecordInfo.get("player");
        for (int i = 0; i < players.size(); i++){
            JSONObject player = (JSONObject) players.get(i);
            playerPosition.add((Long) player.get("spPosition"));
        }

        return playerPosition;
    }

    public String getFormationInfo(JSONObject matchRecordInfo) {

        List<Long> positionList = getPlayerPositionInfo(matchRecordInfo);

        int GK = 0;
        int DF = 0;
        int DM = 0;
        int CM = 0;
        int AM = 0;
        int ST = 0;
        int FW = 0;

        for (int i = 0; i < positionList.size(); i++) {
            Long playerPosition = positionList.get(i);
            if (playerPosition == 0) {
                GK++;
            } else if (1 <= playerPosition && playerPosition <= 8) {
                DF++;
            } else if (9 <= playerPosition && playerPosition <= 11) {
                DM++;
            } else if (12 <= playerPosition && playerPosition <= 16) {
                CM++;
            } else if (17 <= playerPosition && playerPosition <= 19) {
                AM++;
            } else if (20 <= playerPosition && playerPosition <= 22) {
                FW++;
            } else if (23 <= playerPosition && playerPosition <= 27) {
                ST++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (DF > 0) sb.append(DF);
        if (DM > 0) sb.append('-' + DM);
        if (CM > 0) sb.append('-' + CM);
        if (AM > 0) sb.append('-' + AM);
        if (FW > 0) sb.append('-' + FW);
        if (ST > 0) sb.append('-' + ST);
        String result = sb.toString();
        return result;
    }
}
