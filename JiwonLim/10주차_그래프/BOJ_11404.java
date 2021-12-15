package week10_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 플로이드
 * https://www.acmicpc.net/problem/11404
 * 
 * 출력)
 * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값
 * 
 * 접근법)
 * 플로이드 와샬 = '모든 정점'에서 모든 정점으로의 최단경로  (거쳐가는 정점 기준 수행)
 * 			DP 기술에 의거. 최단경로 계속 갱신 d[2,3] = Math.min(d[2,3], d[2,k] + d[k,3])
 * 
 * 주의)
 * 1. 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
 *    1 4 1
 *    1 4 2 입력되면 1 4 1 선택되어야함!
 *
 * 2. 만약 i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력
 * 
 */
public class BOJ_11404 {

	static final int INF = 999999999;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());	// 도시 개수
		int m = Integer.parseInt(br.readLine());	// 버스 개수
		int[][] graph = new int[n][n]; 
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i != j) {
					graph[i][j] = INF;
				}
			}
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			graph[x-1][y-1] =  Math.min(val, graph[x-1][y-1]);
		}
		
		// 플로이드 와샬 	[i,j] vs [i,k] + [k, j]
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				if(i == k) continue;		// 경유지와 출발지 같으면 건너뛰기
				for(int j = 0; j < n; j++) {
					if(k == j) continue;	// 경유지와 도착지 같으면 건너뛰기
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(graph[i][j] == INF) sb.append("0 ");
				else sb.append(graph[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
