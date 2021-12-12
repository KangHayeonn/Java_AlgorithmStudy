package week7_DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 알파벳
 * https://www.acmicpc.net/problem/1987
 * 
 * 출력)
 * 말이 지날 수 있는 최대의 칸 수
 * 
 * 접근법)
 * 백트랙킹 + DFS
 */
public class BOJ_1987 {

	static int R, C;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static boolean[] visited;
	static int rslt = 0;
	
	static void dfs(int x, int y, int cnt) {
		visited[map[x][y] - 'A'] = true;
		
		System.out.println(map[x][y]);
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			// 경계 체크
			if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
				continue;
			}	
			
			if(visited[ map[nextX][nextY] - 'A'] == false) {
				dfs(nextX, nextY, cnt + 1);
			}
		}
		//  *** 다른 루트로 탐색하기 위해 visited 배열을 방문하지 않은 상태로 초기화
		visited[map[x][y] - 'A'] = false;		
		rslt = Math.max(rslt, cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[27];	// A~Z, A방문한경우 visited[0] = true
        
        for(int i = 0; i < R; i++) {
            String s = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);  //System.out.println(map[i][j]-'A');
            }
        }

        dfs(0,0, 1);
        
        System.out.println(rslt);

	}

}
