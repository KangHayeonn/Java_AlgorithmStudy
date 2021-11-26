package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 10% 에서 틀림 -> 왜?????

/* 로직
* 1. 시간을 기준으로 이분탐색을 해야겠다고 생각
* 2. 이분탐색시 start는 0초부터 end는 max * M 까지
* 3. 중간값을 기준으로 심사를 받을 수 있는 인원의 최대를 구하고 만약 받을 수 있다면 왼쪽 경우를 탐색
* 4. 만약 검사를 받을 수 없다면 오른쪽을 탐색
* */
public class boj3089 {
    static int N, M; // 입국심사대, 총 친구 수
    static int[] time; // 시간
    static int maxTime; // 최대시간

    // 이분탐색
    public static int binarySearch() {
        int startTime = 0, endTime = maxTime;
        int mid = 0, sum;

        while(startTime <= endTime){
            sum = 0;
            mid = (startTime + endTime) / 2; // 중간값
            for(int i = 0; i < N; i++){
                sum += mid / time[i] ; // 시간안에 몇명이 할 수 있는지
            }

            if(sum >= M) endTime = mid - 1; // 더한 값이 사람의 수보다 많은 경우 범위를 줄임
            else startTime = mid + 1; // 더한 값이 사람의 수보다 적은 경우 범위를 넓힘
        }

        return startTime;
    }
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        time = new int[N];

        for(int i = 0; i < N; i++){
            int t = Integer.parseInt(br.readLine());
            time[i] = t;
        }

        Arrays.sort(time); // 시간을 오름차순으로 정렬
        maxTime = time[N-1] * M; // 최대시간은 제일 긴 시간 과 총 친구수의 곱

        System.out.println(binarySearch());
//        System.out.println(maxTime);
    }
}
