import java.util.Scanner;

/*
    이전 블록이 침범을 하는 경우의 수는 1 , 2, 3, 번째칸을 기준으로,
    침범이 없는 경우, 1 번만 침범 , 2번만 침범 , 3번만 침범 , 1, 2번 침범 ,2,3번 침범 ,1, 3번침범 , 1,2,3번  침범 8 가지의 경우가 있다.
    0   침범이 없는 경우 1번만 침범 , 3번만 침범 , 1,2,3번 침범 3가지 케이스로 진행이 된다. (마지막일 경우 return 0)
    1   1번만 침범의 경우 상대를 침범하지 않는 경우와  2 ,3번을 침범하며 진행하는 경우로 나뉜다. (마지막일 경우 return 1)
    2   2번만 침범의 경우 1, 3번을 침범하는 경우로 나뉜다.  (마지막일경우 return  0)
    3   3번만 침범의 경우 상대를 침범하지 않는 경우와, 1 ,2 번을 침범하며 진행하는 경우로 나뉜다. (마지막일경우 return 1)
    4   1, 2번 침범의 경우 3번만 침범하는 경우로 진행이 된다. (마지막일경우 return  0)
    5   2, 3번 침범의 경우 1번만 침범하는 경우로 진행이 된다. (마지막일경우 return  0)
    6   1, 3번 침범의 경우 2 번만 침범하는 경우로 진행이 된다. (마지막일경우 return  0)
    7   1, 2, 3번 침범의 경우 침범 을 하지 않는 경우로 진행이된다. (마지막일 경우 return 1)
 */
public class BOJ2133 {
    public static void main(String[] args) {
        int N, dp[][];
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        dp = new int[N][8];
        System.out.println(setDP(dp, N, 0,0)) ;
    }

    private static int setDP(int[][] dp, int N,int depth, int code) {
        if (N-1==depth){
            switch (code){
                case 0: return 0;
                case 1: return 1;
                case 2: return 0;
                case 3: return 1;
                case 4: return 0;
                case 5: return 0;
                case 6: return 0;
                case 7: return 1;
            }
        }
        if (dp[depth][code]!=0){
            return dp[depth][code];
        }
        switch (code){
            case 0: return dp[depth][0] = setDP(dp,N,depth+1,1)+setDP(dp,N,depth+1,3)+setDP(dp,N,depth+1,7);
            case 1: return dp[depth][1] = setDP(dp,N,depth+1,0)+setDP(dp,N,depth+1,5);
            case 2: return dp[depth][2] = setDP(dp,N,depth+1,6);
            case 3: return dp[depth][3] = setDP(dp,N,depth+1,0)+setDP(dp,N,depth+1,4);
            case 4: return dp[depth][4] = setDP(dp,N,depth+1,3);
            case 5: return dp[depth][5] = setDP(dp,N,depth+1,1);
            case 6: return dp[depth][6] = setDP(dp,N,depth+1,2);
            case 7: return dp[depth][7] = setDP(dp,N,depth+1,0);
            default:return 0;
        }
    }
}
