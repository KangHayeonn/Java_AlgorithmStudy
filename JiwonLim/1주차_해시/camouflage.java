// 해시_위장
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1; 
        HashMap<String, Integer> map = new HashMap<>();	
        
        for(int i = 0; i < clothes.length; i++){
            if(map.containsKey(clothes[i][1])){
                map.put(clothes[i][1], map.get(clothes[i][1]) +1);
            }
            else{
                map.put(clothes[i][1], 1);
            }
        }
       for(Entry<String, Integer> e : map.entrySet() ) {
            answer *= (e.getValue() +1);
        }
        return answer -= 1;
    }

    public static void main(String[] args) throws Exception {

        String[][] tmp = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        int result = solution(tmp);
        System.out.println(result);
    }
}