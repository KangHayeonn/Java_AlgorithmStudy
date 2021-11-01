import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ17298 {

    public static void main(String[] args) throws Exception {

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int N , answer[], obig[] = new int[2] , minNum ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언

        String temp[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        temp = new String[N];
        answer = new int[N];
        temp = br.readLine().split(" ");

        minNum = Integer.parseInt(temp[0]);     //최소값

        for (int i=0;i<N;i++){
            if(minNum<Integer.parseInt(temp[i])){
                while (priorityQueue.size()>0&&priorityQueue.peek()[0]<Integer.parseInt(temp[i])) {
                    answer[priorityQueue.poll()[1]] = Integer.parseInt(temp[i]);
                }
            }
            obig = new int[2];
            obig[0] = Integer.parseInt(temp[i]); obig[1] = i;
            priorityQueue.add(obig);
            minNum = Integer.parseInt(temp[i]);
        }



        for (int i:answer){
            if (i!=0)
                bw.write(i+" ");
            else
                bw.write(-1+" ");
        }

        bw.flush();
        bw.close();
    }
}
