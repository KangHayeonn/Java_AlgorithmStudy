/*
    programmers  위장 문제 풀이.
 */
import java.sql.Struct;
import java.util.HashMap;


public class camouflage2 {

    public static int solution(String[][] clothes) {

        int answer = 1;

        HashMap<String , Integer > hashtest = new HashMap<>();
        for (String temp[] : clothes){
            if (hashtest.containsKey(temp[1])){
                hashtest.put(temp[1],hashtest.get(temp[1])+1);
            }else {
                hashtest.put(temp[1],1);
            }
        }

        for (Object key :hashtest.keySet()){
            answer *= (hashtest.get(key)+1);
        }

        return answer-1;
    }

    public static void main(String[] args){
        String[][] clothes = new String[][]{ {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"} , {"bluesunglasses", "face"}};
        System.out.println(solution(clothes));
    }
}
