package week03;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2961 {
    static int N;
    static StringTokenizer st;
    static int[] S, B; // S는 신맛 = 곱, B는 쓴맛 = 합
    static int result = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        S = new int[N];
        B = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
        solution(0, 1, 0);
        System.out.println(result);
    }

    static void solution(int count, int s, int b) {
        if (count == N) {
            result = Math.min(result, Math.abs(s - b));
            return;
        }
        solution(count + 1, s * S[count], b + B[count]);
        solution(count + 1, s, b);
    }

}