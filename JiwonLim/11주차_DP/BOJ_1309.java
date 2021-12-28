package week11_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 동물월
 * https://www.acmicpc.net/problem/1309
 * 
 * 출력)
 * 2*N 배열에 사자를 배치하는 경우의 수
 * 
 * 조건)
 * 가로로도 세로로도 붙어 있게 배치할 수는 없다
 * 한 마리도 배치하지 않는 경우도 하나의 경우의 수
 */
public class BOJ_1309 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 우리의 크기
		int[] dp = new int[100001];
		
		dp[0] = 1;
		dp[1] = 3;
		
		for(int i = 2; i <= n; i++) {
			dp[i] = (dp[i-1]*2 + dp[i-2]) % 9901;
		}
		
		System.out.println(dp[n]);
	
	}

}
