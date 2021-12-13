package week5_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 뱀
 * https://www.acmicpc.net/problem/3190
 * 
 * 출력)
 * 게임이 몇 초에 끝나는지 
 * 
 * 접근법)
 * 벽 또는 자기자신의 몸과 부딪히면 게임이 끝
 * 머리를 다음 칸으로
 * 		이동한 칸에 사과O --> 사과 없애기 (몸길이 늘어남)
 * 		이동한 칸에 사과X --> 꼬리가 위치한 칸 비워주기 (즉 몸길이 변화없음)
 * 
 * 주의 !
 * direction 동남서북 (동으로 먼저 이동)
 * 뱀 몸통 저장하는 큐 (꼬리가 앞)
 * 
 */
public class BOJ_3190 {

	static int n;
	static int[][] map; 	// 0 아무것도 없음, 1 사과 있음, 뱀 2
	static Queue<Snake> q;
  // 처음에는 오른쪽을 보고 있으므로(동, 남, 서, 북)
  public static int dx[] = {0, 1, 0, -1}; 	// 방향 주의 ! 동쪽부터
  public static int dy[] = {1, 0, -1, 0};
	static int ans = 0;		// 게임 소요 시간초
	
	static class Position {

	    private int x;
	    private int y;

	    public Position(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    public int getX() {
	        return this.x;
	    }

	    public int getY() {
	        return this.y;
	    }
	}
	
	static class Snake {
		int second;
		char direction;
		
		public Snake(int second, char direction) {
			this.second = second;
			this.direction = direction;
		}
	}
	
	static void moveSnake() {
		int x = 1;	// 뱀 머리
		int y = 1;
		map[x][y] = 2;	    // 뱀 존재하면 '2'
		int direction = 0;	// 회전 방향
		// 뱀 몸통 정보 ( 머리 - 몸통 - 꼬리) : 새로운 머리정보를 넣어줌, 꼬리가 앞
		Queue<Position> snakebody = new LinkedList<>();
		snakebody.offer(new Position(x,y));
		Snake info = q.poll();
		
		while(true) {
			
			int nextX = x + dx[direction];
			int nextY = y + dy[direction];
		
			// 벽 안에 있는 경우 && 뱀 몸 아닌 경우
			if(nextX >= 1 && nextY >= 1 && nextX <= n && nextY <= n && map[nextX][nextY] != 2) {
				// 이동한 칸에 사과O --> 사과 없애기 (몸길이 늘어남)
				if( map[nextX][nextY] == 1) {
					System.out.println("사과O");
					map[nextX][nextY] = 2;
					snakebody.offer(new Position(nextX, nextY));
				}
				// 이동한 칸에 사과X --> 꼬리가 위치한 칸 비워주기 (즉 몸길이 변화없음)
				if( map[nextX][nextY] == 0) {
					System.out.println("사과X");
					map[nextX][nextY] = 2;
					snakebody.offer(new Position(nextX, nextY));
					
					// 뱀 꼬리 비워주기
					Position prev = snakebody.poll();
					map[prev.getX()][prev.getY()] = 0;
				}
			}
			else {		// 벽 or 뱀 자기자신인 경우
				ans++;
				break;
			}
			// 뱀 머리 이동
			x = nextX;
			y = nextY;
			ans++;
			
			/* 회전 방향 주의! */
			if(ans == info.second) {	// 회전할 시간
				if(info.direction == 'L') {		// 왼쪽 90도
					direction = (direction == 0) ? 3 : direction - 1;
				}
				else {		// 오른쪽 90도
					direction = (direction + 1) % 4;
				}
				if(!q.isEmpty())	info = q.poll();
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());	    // NxN 정사각 보드
		int k = Integer.valueOf(br.readLine());	// 사과 개수

		map = new int[n + 1][n + 1];
		q = new LinkedList<>();
		
		// 사과 위치 입력받기
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			map[x][y] = 1;
		}
		
		// 뱀의 방향 변환 횟수
		int L = Integer.valueOf(br.readLine());
		for(int i = 0; i < L; i++) {
			// 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			char c = st.nextToken().charAt(0);
			q.offer(new Snake(x,c));
		}
		
		// 뱀 현재위치 1,1  길이 1
		moveSnake();
		
		System.out.println(ans);
	}

}
