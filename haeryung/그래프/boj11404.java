package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj11404 {
    public static int N; // n개의 도시
    public static int M; //m개의 버스
    public static int[][] shortDistance; // 최소비용
    public static int INF = 1000000000; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        shortDistance = new int[N+1][N+1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 같은 도시라면 거리는 0
                if (i == j) continue; //shortDistance[i][j] = 0;
                else shortDistance[i][j] = INF;
            }
        }

        for(int i = 0; i < M; i++){
            String[] input = br.readLine().split(" ");

            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            //System.out.println(start + " " + end + " " + cost);
            shortDistance[start][end] = Math.min(shortDistance[start][end], cost);
        }

        for(int k = 1; k <= N; k++){ // 중간에 거치는 노드
            for(int i = 1; i <= N; i++){ // 시작 노드
                for(int j = 1; j <= N; j++){  // 도착 노드
                    shortDistance[i][j] = Math.min(shortDistance[i][k] + shortDistance[k][j], shortDistance[i][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(shortDistance[i][j] >= INF){
                    sb.append("0").append(" ");
                }
                else{
                    sb.append(shortDistance[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
