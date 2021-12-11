package week3_BruteForceSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 친구
 * https://www.acmicpc.net/problem/1058
 * 
 * 오류 해결)  arr[i][j] == 'Y' && visited[i][j] == true인 경우인 [2,3]를 빼먹음
6
NYYNYN
YNYNNN
YYNYNN
NNYNNN
YNNNNY
NNNNYN
 * 
 */
public class BOJ_1058 {
	  static int N;
    static char arr[][];
    static boolean visited[][];
    static int[] friends;
    
    // y의 친구(i)를 찾아, y와 친구인 x의 친구수(friends[x])에 더해주기 : x - [y - i]
	  static void check(int x, int y) {
		
        for(int i = 0; i < N; i++){
          if(arr[y][i] == 'Y' && visited[x][i] == false && i != x) {
            //System.out.println("check [" + y + "," + i + "]");
            friends[x]++;
            visited[x][i] = true;	       // x기준으로 방문처리
          }
        }
	}
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visited = new boolean[N][N];	// 친구계산 여부 체크
        friends = new int[N];			    // 각 사람의 2-친구의 수 합 
        
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) { 
                arr[i][j] = s.charAt(j);
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
              if(arr[i][j] == 'Y') {			
                if(visited[i][j] == false) {	// 친구계산 안한 경우 ! (그 전단계에서 check다녀오면서 visited했을 수 있으므로)
                    friends[i]++;	
                    visited[i][j] = true; 
                    //System.out.println("[" + i + "," + j + "]");
                }
                check(i, j);			// j의 친구들 찾기 /** visited여부와 상관없이 항상 check해야 ! **/
              }
            }
	      }
	    Arrays.sort(friends);
	
	    System.out.println(friends[N-1]);	
	}

}
