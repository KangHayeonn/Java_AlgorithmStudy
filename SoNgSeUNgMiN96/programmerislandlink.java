import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class programmerislandlink {

    static int count;
    public static int solution(int n, int[][] costs) {

        int answer = 0;
        HashMap<Integer, ArrayList> island = new HashMap<Integer,ArrayList>();

        for (int i=0;i<costs.length;i++){
            int arrr[] = {costs[i][1],costs[i][2]};

            if(island.containsKey(costs[i][0])){
                ArrayList temp = island.get(costs[i][0]);
                temp.add(arrr);
            }else{
                answer += costs[i][2];
                ArrayList temp = new ArrayList<int[]>();

                temp.add(arrr);
                island.put(costs[i][0],temp);
            }
            arrr = new int[] {costs[i][0],costs[i][2]};
            if(island.containsKey(costs[i][1])){
                ArrayList temp = island.get(costs[i][1]);
                temp.add(arrr);
            }else{

                ArrayList temp = new ArrayList<int[]>();

                temp.add(arrr);
                island.put(costs[i][1],temp);
            }


        }
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return  -o1[2] + o2[2] ;
            }
        });


        for (Object i: island.keySet()){
            System.out.println("main in key = "+i);


        }


        for (Object i: island.keySet()){
            System.out.println("main in key = "+i);
            ArrayList delete = island.get((int)i);
            island.remove((int)i);
            if(!isLinkedAll(island,costs[0][0])) {
                island.put((int) i, delete);
                System.out.println("여기까진 오나");
            }
            else{
                int arr[] = (int[])delete.get(0);
                answer-= arr[1];
            }
        }


        return answer;

    }

    static boolean  isLinkedAll(HashMap<Integer,ArrayList> island , int start){
        int size = island.keySet().size();
        HashMap <Integer , Integer> visited = new HashMap<Integer, Integer>();
        int temp=0;
        for (Object i :island.keySet()){
            System.out.println("key = "+i);
            visited.put((int)i,0);
        }
        count =0;

        check(island,visited ,start,size);
        return  (size==count);
    }

    static void check(HashMap<Integer,ArrayList> island , HashMap<Integer , Integer> visit,int start , int size){

        if (size==count)
            return ;

        ArrayList temp = island.get(start);
        for (int i=0; i<temp.size(); i++){


            int arr[] = (int[])temp.get(i);
            System.out.println(arr[0]);
            if (visit.get(arr[0])==0){
                visit.put(i,1);

                check(island,visit,arr[1], size);
                count++;
            }else{
                check(island,visit,arr[1], size);
            }

        }


    }
    public static void main(String[] args) throws Exception {

        int  [][]plans = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println(solution(4,plans));
    }




}
