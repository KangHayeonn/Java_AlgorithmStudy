package week12_구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 치킨배달
 * https://www.acmicpc.net/problem/15686
 * 
 * 출력)
 * 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 
 * 도시의 치킨 거리의 최솟값
 * 
 * 알고리즘)
 * 1. 집과 치킨집을 각각 ArrayList에 저장 --> O(1) 시간으로 거리계산 위해
 * 2. 여러개의 치킨집 중에서 M개 고르기
		pickChickenHouse(0, 0);	 <-- 백트래킹  
   3. M개 골랐으면 cntDistance()로 집에서부터 치킨집지의 최소거리 구한다
 * 
 */
public class BOJ_15686 {

	static int N;
	static int M;
	static int[][] map;		// 0 빈집, 1 집, 2 치킨집
	static ArrayList<int[]> chicken, house;
	static boolean[] visitedCK;		// 방문한 치킨집 
	static int ans = Integer.MAX_VALUE;
	
	public static void pickChickenHouse(int depth, int cnt) {
		if(cnt == M) {
			//2. 각 경우별 도시의 치킨거리 합 구하기 (집 기준)--> 최소값이지 확인 후 ans 갱신
			cntDistance();
			return;
		}
		
		for(int i = depth; i < chicken.size(); i++) {
			visitedCK[i] = true;
			pickChickenHouse(i+1, cnt+1);
			visitedCK[i] = false;
		}
	}
	
	public static void cntDistance() {
		int minDistance = 0;
		
		for(int[] h : house) {
			// 각 집별로 가까운 치킨매장의 거리를 구하고 갱신
			int dis = Integer.MAX_VALUE;
			for(int i = 0; i < visitedCK.length; i++) {
				if(visitedCK[i]) {
					dis = Math.min(dis, Math.abs(h[0] - chicken.get(i)[0]) + Math.abs(h[1] - chicken.get(i)[1]));
				}
			}
			minDistance += dis;
		}
		
		ans = Math.min(ans, minDistance);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	// 치킨집의 개수
		
		map = new int[N+1][N+1];
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		for(int i = 0 ;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {		
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}
				else if(map[i][j] == 1){
					house.add(new int[] {i, j});
				}
			}
		}
		
		visitedCK = new boolean[chicken.size()];

		//1. 치킨집의 개수 중에서 M개 고르기
		pickChickenHouse(0, 0);		
		
		System.out.println(ans);
	}

}
