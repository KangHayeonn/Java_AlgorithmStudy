/* https://www.acmicpc.net/problem/19583 */

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19583 {
    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> hm = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;

        String s = st.nextToken();
        int sh = Integer.parseInt(s.substring(0,2));
        int sm = Integer.parseInt(s.substring(3,5));
        int start = sh * 100 + sm;
        /* 총회 시작 시간 */

        String e = st.nextToken();
        int eh = Integer.parseInt(e.substring(0,2));
        int em = Integer.parseInt(e.substring(3,5));
        int end = eh * 100 + em;
        /* 총회 끝낸 시간 */

        String q = st.nextToken();
        int qh = Integer.parseInt(q.substring(0,2));
        int qm = Integer.parseInt(q.substring(3,5));
        int quit = qh * 100 + qm;
        /* 스트리밍 끝낸 시간 */

        String t, n; /* 채팅 시간, 닉네임 */
        int th, tm; /* 채팅 시간 분할 */
        int pt;

        String str;

        while ( (str = br.readLine()) != null ) {
            st = new StringTokenizer(str);
            t = st.nextToken();
            n = st.nextToken();
            th = Integer.parseInt(t.substring(0, 2));
            tm = Integer.parseInt(t.substring(3, 5));
            pt = th * 100 + tm;

            if (pt <= start) {
                hm.put(n, 0);
            }

            if (pt >= end && pt <= quit) {
                if (hm.containsKey(n)) {
                    sum += 1;
                    hm.remove(n);
                }
            }

        }

        System.out.print(sum);

    }
}
