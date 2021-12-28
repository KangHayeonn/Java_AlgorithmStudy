package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1309 {
    public static int N;
    public static int mod;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mod = 9901;
        N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][2];
        dp[1][0] = dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] * 2) % mod;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
        }

        long ans = (dp[N][0] + dp[N][1] * 2) % mod;

        System.out.println(ans);
    }
}
