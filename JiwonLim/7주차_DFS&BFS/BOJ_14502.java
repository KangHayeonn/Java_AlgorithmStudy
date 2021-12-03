package week7_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

/*
 * 연구소
 * https://www.acmicpc.net/problem/14502
 * 
 * 출력)
 * 얻을 수 있는 안전 영역 크기의 최댓값 <-- 벽을 대각선을 이어지도록 하는것
 * 
 * 조건)
 * 연구소는 크기가 N×M인 직사각형
 * 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳
 * 바이러스는 상하좌우 빈 칸으로 모두 퍼져나갈 수 있다
 * 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 * 
 * 접근법)
 * 1. 벽3개 위치 선정 : makeWall  * 		
 * 2. 바이러스 감염 (지역 0을 2로 ): 2기준 상하좌우 0이면 2로 (DFS), 1만나면 continue
 * 3. 전체 맵에서 안전지역 0 카운트 : 반복문 M*N
 * 		--> 얻을 수 있는 안전 영역 크기의 최댓값 출력
 * 
 */
public class BOJ_14502 {

	/*인접 행렬*/
	static int[][] map;
	static int[][] copy;
	static int arr[];
	//static boolean[][] visited;
	static int N;	
	static int M;	
	static int[] dx = {-1, 1, 0, 0};	// x방향 : 상하
	static int[] dy = {0, 0, -1, 1};	// y방향 : 좌우
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		map = new int[N][M];	
		copy = new int[N][M];	
		int cnt = 0; 	// 얻을 수 있는 안전 영역 크기의 최댓값
		
		// 지도 입력 받기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		makeWall(0);
		System.out.println(answer);
	}
	
	// 벽3개 위치 선정 
	public static void makeWall(int cnt) {
		// 벽 3개 위치 선정완료된 경우
		if(cnt == 3) {
			
			// 맵 복사
			for(int i = 0; i < N;i++) {
				for(int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			virusStartSpot();
			// 안전지역 최대값 구하기
			answer = Math.max(cntSafeArea(), answer);
			return;
		}
		
		// 벽 설치 dfs
		for(int i = 0; i < N;i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					cnt++;
					makeWall(cnt);
					map[i][j] = 0;
					cnt--;
				}
			}
		}
	}
	
	// 각 바이러스 감염 시작위치 dfs로 보내기
	public static void virusStartSpot() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if( copy[i][j] == 2)
					dfs(i ,j);			// 감염된 2번 지역에서 dfs 시작
			}
		}
		
	}
	
	// DFS이용해 바이러스 감염 지역 0을 2로
	public static void dfs(int x, int y) {
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			// 경계선 체크
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
				continue;
			
			if(copy[nextX][nextY] == 0) {
				copy[nextX][nextY] = 2;
				dfs(nextX, nextY);
			}
		}
	}
	
	// 전체 맵에서 0 카운트 : 반복문 M*N
	public static int cntSafeArea() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}
