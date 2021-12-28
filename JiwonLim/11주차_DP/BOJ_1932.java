//정수 삼각형
//https://www.acmicpc.net/problem/1932
package week11_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
접근법)
'몇 번째 줄'까지의 최적의 답을 구하는 것이 아니라, '(1,1)에서 (i,j)칸'까지의 최적의 답을 구하는 것
 모든 칸에 대해 개별적인 메모이제이션을 해야
 dp[i][j] = (1,1)에서 (i,j)까지 내려오는 데 얻을 수 있는 수의 합의 최대값이라 정의하고 풀기
 모든 경로의 경우의 수 찾아 최종적으로 가장 작은 누적합 을 찾는 것
 */

/*
				00
			10		11
		20		21		22
	30		31		32		33
40		41		42		43		44

graph[i][j]의 왼자식 = graph[i+1][j]
			        오른자식 = graph[i+1][j+1]
*/

public class BOJ_1932 {

	static int[][] graph;
	static int[][] dp;		// (1,1)에서 (i,j)까지 내려오는데 얻을 수 있는 합의 최대값
	
	// 아래에서 위로 (상향식)
	static int sol(int x, int y) {   
		
		if(x == 0) return graph[x][y];	// 맨위 
		if(y == 0) return dp[x][y] = graph[x][y] + sol(x-1, y);		// 왼쪽벽이므로 부모는 하나
		
		if(dp[x][y] == -1) {
			return dp[x][y] = graph[x][y] + Math.max(sol(x-1, y-1), sol(x-1, y));
		}
		return dp[x][y];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());	// 삼각형 크기
		graph = new int[501][501]; 
		dp = new int[501][501]; 
		int max = -1;
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j <= i; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;		// 정수 범위 0부터이므로
			}
		}
		
		// 맨 아래의 n개의 지점까지 가는데까지의 정수합 비교해 max출력 
		for(int i = 0; i < n; i++) {
			int tmp = sol(n-1, i);
			max = tmp > max ? tmp : max;
		}
			
		System.out.println(max);
	}

}
