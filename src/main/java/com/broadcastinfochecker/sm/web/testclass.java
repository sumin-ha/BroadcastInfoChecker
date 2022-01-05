package com.broadcastinfochecker.sm.web;

import java.util.ArrayList;
import java.util.List;

public class testclass {

    public Integer[] solution(int[] progresses, int[] speeds) {

        int[] temp = new int[100];
        // 100-93=7 / 스피드로 나눔 7 /
        for(int i=0; i<progresses.length; i++) {
            temp[i] = (100-progresses[i])/speeds[i];
        }
        // 100-30=70 / 스피드로 나눔 2.3x /
        // 100-55=45 / 스피드로 나눔 9 /


        List<Integer> temp2 = new ArrayList<>();
        int count = 0;
        int checkprogresses = 0;
        for(int i=0; i<progresses.length; i++) {

            // 기준이 되는 작업 (가장 왼쪽)
            if(count == 0) {
                checkprogresses = temp[i];
            }

            // 배열 제일 마지막일 때, 그동안 쌓인 기능 수 전부 가산
            if(i == progresses.length-1) {
                temp2.add(count);
                break;
            }

            // 나눈 값이 뒷값보다 작으면 바로 릴리즈
            if(checkprogresses < temp[i+1]) {
                temp2.add(++count);
                count = 0;
            } else if(checkprogresses >= temp[i+1]){
                // 나눈 값이 뒷값보다 같거나 크면 뒷값 체크 후 합릴리즈
                count = count + 2; // 릴리즈 2개 (체크값, 비교하는 다음값)
                int j = 2; // 다음값 이후의 값들도 체크
                while ((i+j) != progresses.length-1 && checkprogresses >= temp[i+j]) {
                    // 전체 길이보다 짧은 상태에서, 다음 값 체크하면서도 나눈값이 같거나 크다면
                    ++count;
                }
                // 체크가 끝나면 리스트에 더함
                temp2.add(count);
                count = 0;
            }
        }

        // 몇개씩 릴리즈 되는지 체크 필요
        // 갯수만큼 index 이동할 필요도 있음
        int size = temp2.size();
        Integer[] answer = temp2.toArray(new Integer[size]);
        return answer;
    }
}
