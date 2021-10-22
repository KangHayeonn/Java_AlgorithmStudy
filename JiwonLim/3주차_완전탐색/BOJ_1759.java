package week3_BruteForceSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 암호 만드기
 * https://www.acmicpc.net/problem/1759
 * 
 * 순열 Permutation (순서 중요) 
 * 가능한 암호 경우의 수 모두 출력 
 *
 * 조건 : 
 * 	C가지 알파벳 중 L개의 소문자 알파벳 (중복x)
 * 	정렬된 문자열 abc					<-- 처음 입력받을 때부터 정렬해야
 * 	최소 모음 1개, 자음 2개로 구성
 * 
 * 접근법 : 
 * 	C개 중 L개 뽑기 ==>  순열 Permutation (순서 중요) 
 * 	뽑으면서 최소 모음 1개, 자음 2개로 구성인지 체크 
 * 
 *		방법1 ) 다 만들어놓고 체크   
 * 		방법2 ) 읽어올 때 모음, 자음 구분해서  
 * 		방법3 ) 처음부터 모음 1개, 2개, 3개 .. L-2개 묶음 만들고 
 * 				자음 2개, 3개 뽑아서 넣기 
 * 		
 */
public class BOJ_1759 {

	public static boolean check_condition(char[] a) {
		
		int v = 0; 	// 모음 개수
		int c = 0; 	// 자음 개수
		
		for(char ch : a) {
			if(ch == 'a' || ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u') v++;
			else c++;
		}
		
		if(v >= 1 && c >= 2) return true;
		return false;
	}
	
	//순열 Permutation									
	public static void makePassword(char[] a, int c, int l, boolean[] check, char[] answer, int depth) {	
		if(depth == l) {	// L개의 알파벳 다 뽑음
			if(check_condition(answer) == false) return;
			System.out.println(String.valueOf(answer));
			return;
		}
		
		for(int i = 0; i < c; i++) {
			if(!check[i]) {		// 아직 방문 안했으면
				if(depth >= 1 && a[i] < answer[depth-1]) continue;		// 뽑을 때 전보다 큰 인덱스의 배열 값으로 한정
				check[i] = true;
				answer[depth] = a[i];	
				makePassword(a, c, l, check, answer, depth+1);
				check[i] = false; 		
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String s= br.readLine();
		int L = Integer.parseInt(s.split(" ")[0]);	//L개 뽑기
		int C = Integer.parseInt(s.split(" ")[1]);	//C개 중에서
		char[] arr = new char[C];					// 주어진 알파벳 담을 배열		
		char[] answer = new char[L];				// 정답 담을 배열
		boolean[] check = new boolean[C];			// 해당 알파벳 방문했는지 여부 체크 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int i = 0;
		while(st.hasMoreTokens()) {
			arr[i++] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);	// 정렬 조건 
		
		makePassword(arr, C, L, check, answer, 0);	//	C개 중 L개 뽑기
	}

}
