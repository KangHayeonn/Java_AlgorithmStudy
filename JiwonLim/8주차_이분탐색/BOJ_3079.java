package week8_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 입국심사
 * https://www.acmicpc.net/problem/3079
 * 
 * 출력)
 * 상근이와 친구들이 심사를 마치는데 걸리는 시간의 최솟값
 * 
 * 접근법)
 * 심사대 비어있다고 해서 바로 심사받는것이 아닌, 자율적으로 쉬면서 바로 안가도 됨
 * 
 * 정렬된 배열 : 모든 m명이 심사받는데 걸린 시간 (1초 ~ 심사 최장시간 * M명)
 * 		int start = 1;
		  int end = t[N-1] * M;
		
 * mid 초 / 각 심사대 심사 시간
 * 	 	--> mid초 내에 해당 심사대에서 심사받을 수 있는 최대 인원 수
 
 * 이진탐색 통해 각 심사대 별 최대 인원 수 더한 값(=sum)과 M명 비교
 * 		sum >= M : 초가 인원만큼 쉬게 하면 M명 만족, 그 값을 mid 값에 저장 --> 아래배열탐색 end = mid -1
 * 		sum < M : mid초 내에 M명이 절대 심사못받음 --> 위 배열 탐색 start = mid +1
 *
 */

public class BOJ_3079 {

	static long[] t;
	static long rslt = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 입국심사대 개수
		long M = Long.parseLong(st.nextToken());	// 친구들 총명수
		t = new long[N];
		
		for(int i = 0 ; i < N; i++) {
			t[i] = Integer.parseInt(br.readLine());	// i번  심사관이 한명 심사하는데 걸리는 시간
		}		
		Arrays.sort(t); 	
		
		solution(N, M);
		
		System.out.println(rslt);

	}
	
	static void solution(int N, long M) {
		long start = t[0];
		long end = t[N-1] * M;	// 걸릴 수 있는 최대 시간
		
		// 가능 시간 범위 구하기 위한 이진탐색
		while(start <= end) { 
			long finishedPeople = 0;  					// 입국심사 마친 친구들 수
			long mid = (start+end) / 2;		      // mid는 심사하느데 걸리는 시간
			
			// 이진탐색 통해 각 심사대 별 최대 인원 수 더한 값(=sum)과 M명 비교
			for(int i = 0; i < N; i++) {
				// mid초 내에 해당 심사대에서 심사받을 수 있는 최대 인원 수 
				finishedPeople += mid / t[i];
				
				if(finishedPeople >= M) break;
			}
		
			if(finishedPeople >= M) {
				//System.out.println("mid=" + mid + "/ cnt=" + cnt);
				rslt = Math.min(rslt, mid);
				end = mid -1;
			}
			else {
				start = mid +1;
			}
		}
	}

}
