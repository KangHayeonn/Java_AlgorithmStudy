package week11_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054

arr[]: 입력받은 수
reverse_Arr[] : 입력값 역순
dp1[]: 가장 긴 증가하는 부분 수열 LIS (최장 증가 부분 수열)
dp2[]: 가장 긴 감소하는 부분 수열 LDS (최장 감소 부분 수열) 
가장 긴 바이토닉 부분 수열 = LIS + LDS -1
 */
public class BOJ_11054 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	
		int[] arr = new int[n+1];
		int[] reverse_Arr = new int[n+1];
    // dp[i] = 0번부터 i번까지 구간에서 가장 긴 증가하는 부분수열
		int[] dp1 = new int[n]; 	
		int[] dp2 = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			reverse_Arr[n-1-i] = arr[i];
			dp1[i] = 1; 		// 최장 증가 부분 수열(LIS)의 최소값은 자기자신 1
			dp2[i] = 1;     // 최장 감소 부분 수열(LDS)의 최소값은 자기자신 1
		}
		
		// 증가
		for(int i = 0; i < n; i++) {
			// 0번부터 i번 구간까지 증가 부분
			for(int j = 0; j <= i; j++) {
				// 이전 값(j원소들)중 자신(i원소)보다 값이 작고 && 길이가 가장 긴 dp값을 찾아, 그 길이에 1 더하기
				if(arr[i] > arr[j] && dp1[j] >= dp1[i]) { 	
					dp1[i] = dp1[j] +1;
				}
			}
		}
		
		// 감소 :역순 arr 활용 --> 증가부분과 같은 반복문
		for(int i = 0; i < n; i++) {
			// 0번부터 i번 구간까지 증가 부분
			for(int j = 0; j <= i; j++) {
				if(reverse_Arr[i] > reverse_Arr[j] && dp2[j] >= dp2[i]) { 	
					dp2[i] = dp2[j] +1;
				}
			}
		}

		int ans = 0;
		for(int i = 0; i < n; i++) {
			// 가장 긴 증가수열 구하고, 다음 정수부터 가장 긴 감소수열 구한값 더하기			
			ans = Math.max(ans, (dp1[i] + dp2[n-1-i]));
		}
		
		System.out.println(ans-1);	// 구간 중복 1빼주기
	}

}
