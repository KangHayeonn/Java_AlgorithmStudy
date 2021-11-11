import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1461 {

    public static  int abs(int a){
        if (a<0)
            return -a;
        return  a;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp[]  = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int books[] = new int[N];
        temp = br.readLine().split(" ");
        int answer = 0 , i , last=N;

        for ( i=0; i<N;i++ ){
            books[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(books);

        i=0;

        if (abs(books[0]) >abs(books[N-1])){        //좌측거리가 더 크다.
            answer += abs(books[0]);
            for (int j=0;j<N&&j<M;j++){
                if (books[j]<0)       //들수있는 만큼은 최종적으로 들고 책도 포함
                    i++;
                else break;
            }
        }else{
            answer += books[N-1];
            for (int j=N-1;j>=0&&j>(N-1-M);j--){
                if(books[j]>0)
                    last=j;
                else break;
            }
        }

        int j=last-1;

        while (i<last&&books[i]<0){     //books i 가 음수인동안
            answer += (2*abs(books[i]));
            for (int k=0;k<M;k++){
                if (i<last&&books[i]<0){
                    i++;
                }
                else break;
            }
        }
        while (j>=i&&books[j]>0) {     //books j 가 양수인동안
            answer += (2*books[j]);
            for (int k=0;k<M;k++){
                if (j>=i&&books[j]>0){
                    j--;
                }
                else break;
            }
        }
        System.out.println(answer);
    }

}
