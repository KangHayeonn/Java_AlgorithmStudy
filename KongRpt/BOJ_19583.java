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
        String e = st.nextToken();
        String q = st.nextToken();

        int sh = Integer.parseInt(s.substring(0,2));
        int sm = Integer.parseInt(s.substring(3,5));
        int eh = Integer.parseInt(e.substring(0,2));
        int em = Integer.parseInt(e.substring(3,5));
        int qh = Integer.parseInt(q.substring(0,2));
        int qm = Integer.parseInt(q.substring(3,5));

        String t, n;
        int th, tm;
        String str;

        while ( (str = br.readLine()) != null ) {
            st = new StringTokenizer(str);
            t = st.nextToken();
            n = st.nextToken();
            th = Integer.parseInt(t.substring(0, 2));
            tm = Integer.parseInt(t.substring(3, 5));

            if (th < sh) {
                hm.put(n, 0);
            } else if (th == sh && tm <= sm) {
                hm.put(n, 0);
            }

            if (eh < th && th < qh) {
                if (hm.containsKey(n)) {
                    sum += 1;
                    hm.remove(n);
                }
            } else if (eh == th && em <= tm) {
                if (hm.containsKey(n)) {
                    sum += 1;
                    hm.remove(n);
                }
            } else if (th == qh && tm <= qm) {
                if (hm.containsKey(n)) {
                    sum += 1;
                    hm.remove(n);
                }
            }
        }

        System.out.print(sum);

    }
}
