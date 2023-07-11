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

    public List<String> oppFormation(String myFormation){
        List<String> list = new ArrayList<>();

        if(myFormation.equals("4-2-3-1")){
            list.add("4-3-3");
            list.add("3-5-2");
        } else if (myFormation.equals("4-3-2-1")) {
            list.add("4-4-2");
            list.add("4-1-2-1-2");
        } else if (myFormation.equals("4-3-3")) {
            list.add("4-4-2");
            list.add("4-2-2-2");
        } else if (myFormation.equals("4-2-2-2")) {
            list.add("4-1-2-1-2");
            list.add("4-3-3");
        } else if (myFormation.equals("4-1-2-1-2")) {
            list.add("4-4-2");
            list.add("4-2-2-2");
        } else if (myFormation.equals("4-4-2")) {
            list.add("4-3-1-2");
            list.add("5-3-2");
        } else if (myFormation.equals("4-2-1-3")) {
            list.add("5-3-2");
            list.add("5-2-3");
        }else {
            list.add("4-2-3-1");
        }

        return list;
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


        StringBuffer sb = new StringBuffer();

        if (DF > 0) sb.append(DF);
        if (DM > 0) {sb.append('-'); sb.append(DM);}
        if (CM > 0) {sb.append('-'); sb.append(CM);}
        if (AM > 0) {sb.append('-'); sb.append(AM);}
        if (FW > 0) {sb.append('-'); sb.append(FW);}
        if (ST > 0) {sb.append('-'); sb.append(ST);}



        String result = sb.toString();
        return result;
    }
}
