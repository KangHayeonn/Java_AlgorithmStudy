package BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 상근이는 벽(#),불(*)을 만나면 이동 불가 , 빈공간(.)을 만나면 이동 가능
// 불은 벽(#)인 경우 퍼지지 않고 빈공간(.)이면 불 퍼뜨림.
// 상근이(@)와 만나면 좌표를 불(*)로 바꾸고 상근이는 다른 곳으로 동시에 이동 가능

public class boj5427 {
    static int R,C;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {-1,0,1,0};
    static char arr[][];
    static boolean visit[][];
    static int map[][];
    static Queue<Point> fire;
    static Point start;
    static boolean isPossible; // 빌딩 탈출 여부

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        for(int tc=0; tc<testCases; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            isPossible = false;
            arr = new char[R][C];
            map = new int[R][C];
            fire = new LinkedList<>();

            for(int r=0; r<R; r++) {
                String str = br.readLine();
                for(int c=0; c<C; c++) {
                    char ch = str.charAt(c);

                    if(ch == '@') // 상근이의 경우
                        start = new Point(c,r); // 시작점 넣기
                    else if(ch == '*') // 불인 경우
                        fire.add(new Point(c,r)); // 불 넣기

                    arr[r][c] = ch;
                }
            }

            /*for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    System.out.print(arr[r][c] + " ");
                }
                System.out.println();
            }*/

            bfs();


            /*for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    System.out.print(arr[r][c] + " ");
                }
                System.out.println();
            }*/

            /*for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    System.out.print(map[r][c] + " ");
                }
                System.out.println();
            }*/
            if(!isPossible) {
                System.out.println("IMPOSSIBLE");
            }
        }
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start); // 상근이의 위치부터 시작

        while(!queue.isEmpty()) {
            int len = fire.size();
            for(int l = 0; l < len; l++) {
                Point po = fire.poll();
                for(int d=0; d<4; d++) {
                    int newX = po.x + dx[d];
                    int newY = po.y + dy[d];
                    if(0<=newY && newY<R && 0<=newX && newX<C ) {
                        if(arr[newY][newX]!='#' && arr[newY][newX]!='*') { // 벽이거나 불이 아닌 경우 불 퍼트리기
                            arr[newY][newX] = '*';
                            fire.add(new Point(newX,newY));
                        }
                    }
                }
            }

            len = queue.size();
            for(int l = 0; l < len; l++) {
                Point po = queue.poll();
                // 상근이가 탈출할 곳이 없다면
                if((po.x == 0) || (po.x == C-1) || (po.y == 0) || (po.y == R-1)) {
                    isPossible = true;
                    System.out.println(map[po.y][po.x] + 1); // 시간 늘려줌
                    return;
                }
                // 상근이가 이동할 수 있는 곳 찾기
                for(int d=0; d<4; d++) {
                    int newX = po.x + dx[d];
                    int newY = po.y + dy[d];
                    if(0<=newY && newY<R && 0<=newX && newX<C ){
                        // 빈공간이라면
                        if(arr[newY][newX]=='.') {
                            map[newY][newX] = map[po.y][po.x] + 1; // 시간을 늘려줌
                            arr[newY][newX] = '@';
                            queue.add(new Point(newX, newY));
                        }
                    }
                }
            }

        }

    }

}
