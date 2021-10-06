/* https://programmers.co.kr/learn/courses/30/lessons/42578 */

import java.util.HashMap;

public class Camouflage {
    public static void main(String[] args) {
        int answer = 1;

        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1], 0) + 1);
        }


        for (String str : hm.keySet()) {
            answer *= (hm.get(str) + 1);
        }

        System.out.println(answer - 1);
    }
}
