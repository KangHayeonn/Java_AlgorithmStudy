// 위장 ( 프로그래머스 LEVEL2 )

import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap <String, Integer> hashmap = new HashMap<String, Integer>();
        for(int i=0; i<clothes.length; i++){
            if(hashmap.containsKey(clothes[i][1])){
                int num = hashmap.get(clothes[i][1]);
                hashmap.replace(clothes[i][1], num= num+1);
            }
            else {
                hashmap.put(clothes[i][1], 1);
            }
        }
        /*
        hashmap.forEach((key, value) -> {
            System.out.println(key + " : " + value);
            //answer += value; // lambda-error
            // (error) local variables referenced from a lambda expression must be final or effectively final. 
        });*/

        for(String key : hashmap.keySet()){
            answer *= hashmap.get(key)+1;
        }

        return answer-1;
    }
}