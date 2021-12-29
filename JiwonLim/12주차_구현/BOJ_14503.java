package week12_구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 로봇 청소기
 * https://www.acmicpc.net/problem/14503
 * 
 * 출력)
 * 청소하는 영역의 개수 
 * 
 */
public class BOJ_14503 {

	static int N, M;
	static int[][] map;
	static int[] dy = {-1,0,1,0};	// 북동남서 
	static int[] dx = {0,1,0,-1};
	static int ans;		
	
	public static void moveRobot(int r, int c, int d) {
	
		// 현재 위치 청소
		map[r][c] = 2;
		
		// 2. 현재위치에서 방향d 기준으로 왼쪽방향부터 차례대로 탐색
		for(int i = 0; i < 4; i++) {
			//왼쪽 방향으로 
			d = (d + 3) % 4;			// 북 기준 왼쪽은 서. 동 기준 왼쪽은 북 (북동남서 순서)
			int nextY = r + dy[d];		// dx[d] 현재방향에서 왼쪽으로 
			int nextX = c + dx[d];		// row y, column x 
			
			// 경계 체크
			if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N ) 
				return;

			// a. 청소안된 빈칸인 경우  
			if(map[nextY][nextX] == 0) {  
				ans++;
				moveRobot( nextY, nextX, d);
				return;
			}
			
		}
		
		// c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우, '바라보는 방향을 유지한 채'로 한 칸 후진
		int prevD = (d + 2) % 4;		// 북 기준 오른쪽(후진)은 남 (북동남서 순서)
		int prevY = r + dy[prevD];
		int prevX = c + dx[prevD];
		
		// 경계 체크
		if(prevX < 0 || prevY < 0 || prevX >= M || prevY >= N ) 
			return;
		
		//후진하는 곳이 벽이 아닌 경우 후진가능 . 청소되어있는 곳도 후진 가능
		if(map[prevY][prevX] != 1 ) {
			moveRobot( prevY, prevX, d);  // '바라보는 방향을 유지한 채'
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// 빈칸 0, 벽 1, 청소 2
		ans = 1;	// 로봇 청소기가 있는 칸은 항상 빈칸이므로 1로 시작
		
		st = new StringTokenizer(br.readLine());
		// 로봇 청소기가 있는 칸의 좌표 (r, c), 방향 d
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());	// 북0 동1 남2 서3
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		moveRobot(r,c,d);

		System.out.println(ans);
	}

}
