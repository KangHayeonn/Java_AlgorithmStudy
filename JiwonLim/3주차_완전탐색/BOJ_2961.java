package week3_BruteForceSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * �����̰� ���� ���ִ� ����
 * https://www.acmicpc.net/problem/2961
 * 
 * ��� N��
 * �Ÿ� S		���� B
 * �Ÿ��� ��*
 * ������ ��+
 * 	--> �Ÿ� ���� ���� ������ 
 * 
 * ���� )
 * 		N�� ��, �ּ� 1�� �̻� ~ N�� ���� 
 * 		�Ÿ�, ���� ���� �ּҵǵ��� --> diff ������ ���� --> diff ��ȯ 
 * 
 * ���ٹ� )
 * 		���� �߿� x ==> ���� Combination  (�ߺ�x)		
 * 		�迭 2�� ����� �Ÿ� sour  �� bitter
 * 		������ �̱� ���� x  --> main���� �ݺ��� ���� �����ֱ� 
 * 		diff �� ��		
 */
public class BOJ_2961 {
	static int min_diff = Integer.MAX_VALUE;		// m���� ��� �̿��� ���� �丮�� �Ÿ��� ���� ���̰� �ּ��� ��
	public static int cal (int[] s, int[] b, int[] a) {
		int s_cnt = 1; int b_cnt = 0;
		for(int i = 0; i < a.length; i++) {		
			if(a[i] == -1)	break;
			s_cnt *= s[a[i]];
			b_cnt += b[a[i]];
		}
		return Math.abs(s_cnt - b_cnt);
	}
	
	public static void combination(int[] s, int[] b, int[] answer, int n, int m, boolean[] check, int idx, int dep) {
		
		if(dep == m) {
			int diff = cal(s, b, answer);
			if (min_diff > diff) min_diff = diff;
			//System.out.println(Arrays.toString(answer));		
			return;
		}
		
		if(idx == n) {		
			return;
		}
		answer[dep] = idx;
		//���� �ܰ�� �̵� 
		combination(s, b, answer, n, m, check, idx+1, dep+1);
		combination(s, b, answer, n, m, check, idx+1, dep);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sour = new int[N];
		int[] bitter = new int[N];
		int[] answer = new int[N];	// ������ ����� �ε��� �ִ� �迭, -1�� �ʱ�ȭ 
		Arrays.fill(answer, -1);
		boolean[] check = new boolean[N];		// �湮 ���� ����
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());			
			bitter[i] = Integer.parseInt(st.nextToken());
			//System.out.println(sour[i]);
			//System.out.println(bitter[i]);
		}
		
		for(int i = 1; i <= N; i++) {
			combination(sour, bitter, answer, N, i, check, 0, 0);		//N�� �� i�� �̱�
		}
		
		System.out.println(min_diff);
	}
}
