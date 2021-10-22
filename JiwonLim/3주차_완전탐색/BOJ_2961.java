package week3_BruteForceSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 도영이가 만든 맛있는 음식
 * https://www.acmicpc.net/problem/2961
 * 
 * 재료 N개
 * 신맛 S		쓴맛 B
 * 신맛은 곱*
 * 쓴맛은 합+
 * 	--> 신맛 쓴맛 차이 적도록 
 * 
 * 조건 )
 * 		N개 중, 최소 1개 이상 ~ N개 이하 
 * 		신맛, 쓴맛 차이 최소되도록 --> diff 변수에 저장 --> diff 반환 
 * 
 * 접근법 )
 * 		순선 중요 x ==> 조합 Combination  (중복x)		
 * 		배열 2개 만들기 신맛 sour  쓴 bitter
 * 		고정된 뽑기 개수 x  --> main에서 반복문 통해 돌려주기 
 * 		diff 값 비교		
 */
public class BOJ_2961 {
	static int min_diff = Integer.MAX_VALUE;		// m개의 재료 이용해 만든 요리의 신맛과 쓴맛 차이가 최소일 때
	public static int cal (int[] s, int[] b, int[] a) {
		int s_cnt = 1; int b_cnt = 0;
		for(int i = 0; i < a.length; i++) {		
			if(a[i] == -1)	break;
			s_cnt *= s[a[i]];
			b_cnt += b[a[i]];
		}
		return Math.abs(s_cnt - b_cnt);
	}
	
	public static void combination(int[] s, int[] b, int[] answer, int n, int m, int idx, int dep) {
		
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
		//다음 단계로 이동 
		combination(s, b, answer, n, m, idx+1, dep+1);
		combination(s, b, answer, n, m, idx+1, dep);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sour = new int[N];
		int[] bitter = new int[N];
		int[] answer = new int[N];	// 선택한 재료의 인덱스 넣는 배열, -1로 초기화 
		Arrays.fill(answer, -1);
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());			
			bitter[i] = Integer.parseInt(st.nextToken());
			//System.out.println(sour[i]);
			//System.out.println(bitter[i]);
		}
		
		for(int i = 1; i <= N; i++) {
			combination(sour, bitter, answer, N, i, 0, 0);		//N개 중 i개 뽑기
		}
		
		System.out.println(min_diff);
	}
}
