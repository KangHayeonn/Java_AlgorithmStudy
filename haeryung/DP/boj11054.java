package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj11054 {
    static int N; // 수열의 크기
    static int[] dpRight;
    static int[] dpLeft;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dpLeft = new int[N+1];
        dpRight = new int[N+1];
        arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽에서부터 오른쪽까지의 가장 긴 증가하는 부분수열
        for(int i = 0; i < N; i++){
            dpLeft[i] = 1;

            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    dpLeft[i] = Math.max(dpLeft[i], dpLeft[j] + 1);
                }
            }

        }

        // 오른쪽에서부터 왼쪽까지의 가장 긴 감소하는 부분수열
        for(int i = N-1; i >= 0; i--){
            dpRight[i] = 1;

            for(int j = N-1; j > i; j--){
                if(arr[j] < arr[i]){
                    dpRight[i] = Math.max(dpRight[i], dpRight[j] + 1);
                }
            }
        }

        int max = 0;

        // 같은 위치에서의 가장 긴 증가하는 부분수열과 가장 긴 감소하는 부분수열의 dp테이블 값을 더해서 최대값을 구함
        for(int i = 0; i < N; i++){
            max = Math.max(max, dpLeft[i] + dpRight[i]);
        }

        int ans = max - 1;

        System.out.println(ans);
    }
}
