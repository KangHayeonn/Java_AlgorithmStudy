package week8_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 공유기 설치
 * https://www.acmicpc.net/problem/2110
 * 
 * 접근법)
1. 특정 간격을 기준으로 가능한 위치에 공유기를 설치
2. 공유기 수가 더 설치되어야 한다면, 간격 줄이기
	   공유기 수를 줄여야한다면, 간격 늘리기

 */
public class BOJ_2110 {

	public static void main(String[] args) throws IOException {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//집의 개수
		int C = Integer.parseInt(st.nextToken());	//공유기 개수
		int[] house = new int[N];			            // 집의 좌표

		// 집 좌표 입력받기
		for(int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house); 		// 이진탐색 위해 정렬
		
		int start = 1;	// 가능한 최소거리 min gap
    int end = house[N-1] - house[0];	// 가능한 최대 거리 max gap
    int rslt = 0;

    while (start <= end) {
        int mid = (start+end)/2;	// mid는 가장 인접한 두 공유기 사이의 거리gap을 의미
        int prev = house[0];		  // 첫번째 집에는 무조건 공유기 설치한다 가정
        
        int limit = 0;
        int cnt = 0;
        // 모든 공유기 간 간격이 mid이상 되는지
        for(int i = 0; i < N; i++) {
           if(limit <= house[i]) {
              cnt++;
              limit = house[i] + mid;
            }
         }

         if (cnt >= C) {
            //실제 설치될 공유기보다 많이 설치함 --> 오른쪽으로 이동해 더 긴 간격 찾아야
             rslt = mid;		  // 최적의 결과 저장
             start = mid+1;	// 공유기 설치할 수 있는 최대거리 칮아야하기 때문에 더 큰 거리가능한지 확인
         } else {
              //공유기를 c보다 적게 설치함 --> 왼쪽으로 이동해 더 짧은 간격 찾아야(mid를 감소)
             end = mid-1;
         }
     }

     System.out.println(rslt);
	}
}
