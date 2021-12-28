import java.util.Scanner;

public class BOJ1309 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int dp[][] = new int[100000][3];
        System.out.println((setDP(dp,N,N-1,0)+setDP(dp,N,N-1,1)+setDP(dp,N,N-1,2))%9901);
    }
    static int setDP(int dp[][], int N,int i,int m){
        if (i==0)
            return 1;
        if( dp[i][m]!=0)
            return dp[i][m];

        if (m==0)
            return dp[i][m] = (setDP(dp,N,i-1,0) +setDP(dp,N,i-1,1)+setDP(dp,N,i-1,2))%9901;
        else if(m==1)
            return dp[i][m] = (setDP(dp,N,i-1,0) +setDP(dp,N,i-1,2))%9901;
        else
            return dp[i][m] = (setDP(dp,N,i-1,0) +setDP(dp,N,i-1,1))%9901;
    }

}
