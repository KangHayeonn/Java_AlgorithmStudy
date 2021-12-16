import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1043 {

    static int answer;

    public static void main(String[] args) throws Exception{
        int N,M, lie , party;
        HashSet<Integer> lier = new HashSet<Integer>();//HashSet생성
        HashSet<Integer> parties;
        boolean visited[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st =  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] partylist = new ArrayList[N];
        M = Integer.parseInt(st.nextToken());
        visited= new boolean[M];
        HashSet<Integer> partypeople[]  = new HashSet[M];
        answer = M;
        for (int i = 0; i < N; i++) {
            partylist[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            partypeople[i] = new HashSet<Integer>();
        }

        st =  new StringTokenizer(br.readLine());
        lie = Integer.parseInt(st.nextToken());

        for (int i = 0; i < lie; i++) {
            lier.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i <M; i++) {
            st =  new StringTokenizer(br.readLine());
            party = Integer.parseInt(st.nextToken());
            parties = new HashSet<>();
            int people;
            partypeople[i] = parties;
            for (int j = 0; j < party; j++) {
                people = Integer.parseInt(st.nextToken());
                parties.add(people);
                partylist[people-1].add(i);
            }
        }
        for (int i = 0; i < M; i++) {
            HashSet<Integer> liercopy = new HashSet<>();
            liercopy.addAll(lier);
            liercopy.retainAll(partypeople[i]);
            if(liercopy.size()>0){  //겹친 사람이 있었다면
                addpeople(lier ,partypeople, partylist, i,visited);
            }
        }
        System.out.println(answer);
    }

    private static void addpeople(HashSet<Integer> lier ,HashSet<Integer> partypeople[] , ArrayList<Integer>[] partylist,int i, boolean[] visited) {
        HashSet<Integer> liercopy = new HashSet<>();
        liercopy.addAll(lier);
        if (visited[i]){
            return;
        }else {
            answer--;
            visited[i] = true;
        }

        HashSet<Integer> partypeoplecopy = new HashSet<>();
        partypeoplecopy.addAll(partypeople[i]);
        partypeoplecopy.removeAll(liercopy);
        lier.addAll(partypeoplecopy);
        for (Integer o :partypeoplecopy){        //partypeople[i] 에 새로 추가된 사람들이 있고, 새로 추가된 사람들의 파티리스트를 돈다.
            for ( Integer j: partylist[o-1]){
                addpeople(lier ,partypeople, partylist, j,visited);
            }
        }
    }
}
