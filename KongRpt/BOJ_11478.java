/* https://www.acmicpc.net/problem/11478 */

import java.io.*;
import java.util.HashMap;

public class BOJ_11478 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        String str = br.readLine();

        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                hm.put(str.substring(i, j + 1), hm.getOrDefault(str.substring(i, j + 1), 0) + 1);
            }
        }

        for (String s : hm.keySet()) {
            sum += 1;
        }

        System.out.println(sum);
    }
}
