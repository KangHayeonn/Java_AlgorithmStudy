import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2110 {

    public static void main(String[] args) throws Exception {

        String temp , split[];
        int N, C;
        String spit[];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);

        int arr []= new int[N];

        for (int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int max= arr[N-1] - arr[0] , min =1, mid , length = arr[N-1] - arr[0] , i , j, count, answer=0;
        int sum =0;

        while (min <=max){
            mid = ( min + max ) / 2;
            i=0; j=N-1; sum=length/mid;count=0;

            for (int k=0;(k<N&&count!=sum);k++){
                if (arr[k]-arr[i]>=mid){
                    count++;
                    i=k;
                }
            }

            if (count+1>=C){
                min = mid+1;
                if (min!=max)
                    answer = min;

                else {
                    count =0;
                    for (int k=0;(k<N&&count!=sum);k++){
                        if (arr[k]-arr[i]>=mid){
                            count++;
                            i=k;
                        }
                    }

                    if (count+1>=C){
                        answer = min;
                    }
                }
            }else {
                max = mid;
                if(max==min)
                    break;
            }
            //System.out.println("max = "+max + " min = "+min);
        }


        System.out.println(answer);
    }

}
