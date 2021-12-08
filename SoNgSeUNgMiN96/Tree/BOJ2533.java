import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


/*
    * 모든 노드는 깊이를 갖는다. *
    1. 자식이 있으면 모두 얼리어답터이다.
    2. 아래계층부터 거슬러 올라오면서 이미 연결된 모두가 얼리어답터이고, 본인이 얼리어답터라면 본인은 얼리 어답터를 포기한다.
    N의 입력에 대해서 정점은 1~N이다.
    N * N 의 행렬로 그래프를 나타낸다.
 */

class Node{

    ArrayList <Integer> linkList;
    boolean earlyadopter;
    int childearlycount;

    Node(){
        linkList = new ArrayList<Integer>();
        earlyadopter = false;
        childearlycount=0;
    }

}

public class BOJ2533 {

    static int early=0;

    public static void main(String[] args) throws Exception {

        int N,A,B, root=0 , max=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언

        N = Integer.parseInt(br.readLine());
        Node graph[] = new Node[N+1];
        boolean visited[] = new boolean[N+1];

        for (int i=1;i<N+1;i++){        //노드 초기화
            graph[i] = new Node();
        }

        for(int i=0;i<N-1;i++){     //노드 입력받기

            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            graph[A].linkList.add(B);
            graph[B].linkList.add(A);
        }


        setEarly(1,graph, visited,0);

        System.out.println(early);


    }
    public static void setEarly(int num,Node graph[] , boolean visited[], int prev){

        ArrayList<Integer> temp= graph[num].linkList;
        visited[num] = true;

        if (temp.contains(prev)){
            temp.remove(temp.indexOf(prev));
        }

        if(temp.size()>0){   //자식이 있다면,
            graph[num].earlyadopter = true;
            early++;
            if(prev!=0)    //부모가 있다면,
                graph[prev].childearlycount++;
        }

        for (int i=0;i<temp.size();i++){
            if (!visited[temp.get(i)])  //방문하지 않았다면.
                setEarly(temp.get(i),graph ,visited,num);
        }

        if (temp.size()!=0&&graph[num].childearlycount==temp.size()){  //모든 자식이 얼리어답터이라면
            graph[num].earlyadopter = false;
            if(prev!=0)
                graph[prev].childearlycount--;
            early--;
        }
    }
}