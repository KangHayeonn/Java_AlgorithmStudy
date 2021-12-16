import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class BOJ11404 {

    public static void main(String[] args) throws Exception{

        int N, M, shortestMap[][] , start , destination , cost;
        boolean visited[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        shortestMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i!=j){
                    shortestMap[i][j]= 1000000001;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken())-1;
            destination = Integer.parseInt(st.nextToken())-1;
            cost = Integer.parseInt(st.nextToken());
            if (shortestMap[start][destination]>cost){
                shortestMap[start][destination] = cost;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k=0;k<N;k++){
                    if (i!=j&&i!=k&&j!=k){
                        if(shortestMap[j][k]>shortestMap[i][k]+shortestMap[j][i]){
                            shortestMap[j][k] = shortestMap[i][k] +shortestMap[j][i];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j=0;j<N;j++){
                if(shortestMap[i][j]==1000000001)
                    bw.write("0 ");
                else
                    bw.write(shortestMap[i][j]+" ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
