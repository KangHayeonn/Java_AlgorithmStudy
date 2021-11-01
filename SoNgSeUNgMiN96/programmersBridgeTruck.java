import com.sun.xml.internal.ws.resources.UtilMessages;

import java.util.*;

public class programmersBridgeTruck {

    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int []truck_weights = {7,4,5,6};
        System.out.println(s.solution(2,10,truck_weights));
    }

}


class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer[]> queue = new LinkedList<>();        //큐 생성
        int time =0, sum=0;

        for (int i=0;i<truck_weights.length;i++){   //모든 트럭을 순서대로

            if(queue.size()>0&&time ==queue.peek()[1]){     //현재 시간 기준으로 나올 트럭이 있다면
                sum -=queue.poll()[0];
            }
            if(sum+truck_weights[i]<=weight){       //트럭이 추가로 올라탈 수 있다면
                sum +=truck_weights[i];     //총 무게를 더해주고
                Integer temp[] = new Integer[2];
                temp[0]=truck_weights[i];       //트럭의 무게와 트럭이 나올 시간을 큐에 넣어준다
                temp[1] = time+bridge_length;
                queue.add(temp);
                time++;     //1초 진행
            }else{      //이미 무게 초과인 상태라면 다음 트럭이 올라갈 수 있는 시간으로 이동하고, 동일 트럭을 재검사한다.
                time = queue.peek()[1];
                i--;
            }
        }

        time +=bridge_length;       //마지막 트럭이 다리를 탈출하면 최종시간.

        return time;
    }
}
