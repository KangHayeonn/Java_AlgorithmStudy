import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ3079 {
    public static void main(String[] args) throws Exception {

        String temp , split[];
        int N,M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        temp = br.readLine();
        split = temp.split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        long  left =0,mid , sum=0;
        int min =Integer.MAX_VALUE;

        int arr[] = new int[N];

        for (int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            if (min>arr[i])
                min = arr[i];
        }
        long right = 1000000000*(long)min;

        while (left!=right) {
            mid = (left + right) / 2;
            sum = 0;

            for (int i = 0; i < N; i++) {
                sum += mid / arr[i];
            }
            if (sum >= M)
                right = mid;
            else
                left = mid + 1;
        }
        System.out.println(left);
    }
}
