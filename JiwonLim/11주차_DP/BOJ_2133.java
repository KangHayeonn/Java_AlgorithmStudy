package week11_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 타일 채우기
 * https://www.acmicpc.net/problem/2133
 * 
 * 출력)
 * 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수
 * 
 * 알고리즘)
 * https://yabmoons.tistory.com/536
 * dp[0] = 1
 * dp[홀수] = 0
 * dp[2] = 3 
 * '어떤 경우에도 특별한 모양들은 2가지씩만 존재'
 * dp[4] = dp[2]*dp[2] + 2(N이4일때의 특별모양) = 11
 * dp[6] = dp[4]*dp[2] + dp[2]*2(N이4일때의 특별모양) + 2(N이6일때의 특별모양) = 41
 * dp[8] = dp[6]*dp[2] + dp[4]*2(N이4일때의 특별모양) + dp[2]*2(N이6일때의 특별모양) + 2(N이8일때의 특별모양) = 153
 * 
 */
public class BOJ_2133 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 우리의 크기
		int[] dp = new int[31];
		
		if(n % 2 == 1){ 		// 홀수인 경우
	            System.out.println(0);
	    }
		else {
			dp[0] = 1;
			dp[2] = 3;
			
			for(int i = 4; i <= n; i+=2) {
				dp[i] = dp[i-2] * dp[2];
				for(int j = i-4; j >= 0; j-= 2) {
					dp[i] += dp[j] * 2;
				}
			}
			
			System.out.println(dp[n]);
		}

	}

}
