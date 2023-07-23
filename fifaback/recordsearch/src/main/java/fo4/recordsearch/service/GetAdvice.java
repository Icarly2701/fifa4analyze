package fo4.recordsearch.service;

import java.util.ArrayList;
import java.util.List;

public class GetAdvice {
    String myFormation;
    List<String> oppFormation;
    String advice;


    public GetAdvice(String myFormation) {
        this.myFormation = myFormation;
        this.oppFormation = oppFormation();
        this.advice = howToPlay();
    }

    private List<String> oppFormation(){
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
        } else if(myFormation.equals("5-2-3") || myFormation.equals("5-3-2")) {
            list.add("4-2-3-1");
            list.add("4-4-2");
        } else if(myFormation.equals("5-2-1-3")){
            list.add("4-2-3-1");
            list.add("4-4-2");
        }
        else {
            list.add("4-2-3-1");
        }

        return list;
    }

    private String howToPlay(){
        String result = "0";
        StringBuilder sb = new StringBuilder();

        if(myFormation.equals("4-2-3-1")){
            sb.append("빠른 역습 위주로 플레이. 중원 및 사이드 전환으로 공간 만들기. 역습, 스루패스, 고급여 풀백을 활용한 사이드 플레이");
        } else if (myFormation.equals("4-3-2-1")) {
            sb.append("안정적으로 경기 운영. 게임이 말리더라도 위축되지말고 전개 위주로 플레이. 경기 템포 조절, 차분하게 플레이");
        } else if (myFormation.equals("4-3-3")) {
            sb.append("빠른 전개. 중원에서의 짧은 패스로 전개. ");
        } else if (myFormation.equals("4-2-2-2")) {
            sb.append("2명의 스트라이커로 헤더 및 빠른 역습. 역습 상황에서 볼란치의 깊은 스루패스. zs, a 패스 적극 활용. 지공시에는 짧은 패스. ");
        } else if (myFormation.equals("4-1-2-1-2")) {
            sb.append("2명의 스트라이커 침투 위주 플레이. 수비시에는 CDM을 활용한 견제. CAM의 스루패스를 활용한 역습");
        } else if (myFormation.equals("4-4-2")) {
            sb.append("퍼터 적극 활용. 투톱을 이용한 침투 플레이. 중앙 미드필더를 이용하여 수비");
        } else if (myFormation.equals("4-2-1-3")) {
            sb.append("빠른 빌드업. 양쪽 윙포워드를 이용한 공간 활용. 사이드 공간에서의 역습 조심");
        } else if (myFormation.startsWith("5")) {
            sb.append("빠른 역습 위주로 플레이. 풀백을 사용한 사이드 공격 전개. 중거리 슛에 취약하므로 SW를 활용한 압박 수비");
        }
        else {
            sb.append("[분석 중에 있습니다.]");
        }



        return sb.toString();
    }
}
