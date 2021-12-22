import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11054 {
    public static void main(String[] args) throws Exception {
        int N , arr[] , dpIncrease[] ,dpDecrease[] , max= Integer.MIN_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dpIncrease = new int[N];
        dpDecrease = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dpIncrease[i] = 1;
            dpDecrease[i] = 0;
        }
        for (int i = 0; i < arr.length; i++)
            setIncreseDP(arr, dpIncrease, i);
        for (int i = N-1; i >= 0; i--)
            setDecreseDP(arr, dpDecrease, i,N);
        for (int i = 0; i < arr.length; i++)
            max = Math.max(max , dpIncrease[i]+dpDecrease[i]);
        System.out.println(max);
    }

    public static void setIncreseDP(int arr[] , int dp[], int i){
        for (int j = 0; j <= i; j++)
            if (arr[i]>arr[j])
                dp[i] = Math.max(dp[j]+1,dp[i]);
    }

    public static void setDecreseDP(int arr[] , int dp[], int i, int N){
        for (int j = N-1; j >= i; j--)
            if (arr[i]>arr[j])
                dp[i] = Math.max(dp[j]+1,dp[i]);
    }
}
