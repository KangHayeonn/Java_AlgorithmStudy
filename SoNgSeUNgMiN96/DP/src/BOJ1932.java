import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws Exception{
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int arr[][] = new int[500][500];
        long dp[][] = new long[500][500];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(setDP(arr,dp,N,0,0));
    }

    static long setDP(int [][]arr, long [][]dp ,int N, int i, int j){
        if(i==N-1)
            return dp[i][j] = arr[i][j];
        if(dp[i][j]!=0)
            return dp[i][j];
        return dp[i][j] = arr[i][j] + Math.max(setDP(arr,dp,N,i+1,j),setDP(arr,dp,N,i+1,j+1));
    }
}
