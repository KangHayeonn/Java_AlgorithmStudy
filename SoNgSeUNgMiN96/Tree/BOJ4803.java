import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ4803 {

    public static int findParent(int a, int arr[]){
        int temp = a;
        while (temp != arr[temp]){
            temp = arr[temp];
        }
        arr[a] = temp;
        return temp;
    }

    public static void main(String[] args) throws Exception {

        int temp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
        int N = 1, M = 1, arr[], T , link[][];
        boolean [] parent;
        int casenum =0;
        HashMap<Integer,Integer> map  = new HashMap<>();
        HashMap<Integer,Integer> cycle  = new HashMap<>();

        while (true) {
            int a,b;
            casenum++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                break;
            arr = new int[N + 1];
            link = new int[M][2];
            parent = new boolean[N+1];
            T = 0;
            map = new HashMap<Integer,Integer>();
            cycle = new HashMap<Integer,Integer>();
            for (int i = 1; i < N + 1; i++) {
                arr[i] = i;
                parent[i] = true;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                link[i][0] = Integer.parseInt(st.nextToken());
                link[i][1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < M; i++) {

                a = findParent(link[i][0],arr);
                b = findParent(link[i][1],arr);

                if(a==b){       //부모가 같다면
                    cycle.put(a,1);
                }
                else if (a>b){
                    arr[a] = b;
                }else {
                    arr[b] =a;
                }
            }
            for (int i=1;i<N+1;i++) {
                findParent(i,arr);
                map.put(arr[i],0);
            }
            for (Integer i : cycle.keySet()){
                map.remove(findParent(i,arr));
            }
            T = map.size();

            if (T==0){
                bw.write("Case "+casenum+": No trees.");
            }else if (T==1){
                bw.write("Case "+casenum+": There is one tree.");
            }else{
                bw.write("Case "+casenum+": A forest of "+T+" trees.");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
