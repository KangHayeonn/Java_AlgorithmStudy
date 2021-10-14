import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class BOJ2212 {

    public static void main(String[] args) throws Exception{
        int N,i ,size , K, sum=0;
        Integer station[] , dis[];
        String temp[];
        HashMap<Integer , Integer> map1 = new HashMap<>();  //중복을 제거하기위한 해시맵 선언


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        pick = new int[K];
        temp = new String[N];
        temp = br.readLine().split(" ");

        for (i=0;i<N;i++){      //우선 해시맵에 넣어준다
            map1.put(Integer.parseInt(temp[i]),Integer.parseInt(temp[i]));
        }
        size = map1.size();
        station = new Integer[size];
        dis = new Integer[size-1];
        i=0;

        for (Integer key: map1.keySet()){       //중복을 제거한 집중국들의 좌표가 station 에 들어간다.
            station[i++] = map1.get(key);
        }

        Arrays.sort(station, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a-b;
            }
        });

        for (i=0;i<size-1; i++){
            dis[i] = station[i+1] - station[i];
        }
        Arrays.sort(dis, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a-b;
            }
        });

        for(i=0;i<size-K;i++){
            sum+=dis[i];
        }
        System.out.print(sum);

    }
}



/*
예제 입력 :
6
2
1 6 9 3 6 7

예제 출력 : 5
 */