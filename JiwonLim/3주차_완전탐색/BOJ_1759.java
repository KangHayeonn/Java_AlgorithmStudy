package week3_BruteForceSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * ��ȣ �����
 * https://www.acmicpc.net/problem/1759
 * 
 * ���� Permutation (���� �߿�) 
 * ������ ��ȣ ����� �� ��� ��� 
 *
 * ���� : 
 * 	C���� ���ĺ� �� L���� �ҹ��� ���ĺ� (�ߺ�x)
 * 	���ĵ� ���ڿ� abc					<-- ó�� �Է¹��� ������ �����ؾ�
 * 	�ּ� ���� 1��, ���� 2���� ����
 * 
 * ���ٹ� : 
 * 	C�� �� L�� �̱� ==>  ���� Permutation (���� �߿�) 
 * 	�����鼭 �ּ� ���� 1��, ���� 2���� �������� üũ 
 * 
 *		���1 ) �� �������� üũ   
 * 		���2 ) �о�� �� ����, ���� �����ؼ�  
 * 		���3 ) ó������ ���� 1��, 2��, 3�� .. L-2�� ���� ����� 
 * 				���� 2��, 3�� �̾Ƽ� �ֱ� 
 * 		
 */
public class BOJ_1759 {

	public static boolean check_condition(char[] a) {
		
		int v = 0; 	// ���� ����
		int c = 0; 	// ���� ����
		
		for(char ch : a) {
			if(ch == 'a' || ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u') v++;
			else c++;
		}
		
		if(v >= 1 && c >= 2) return true;
		return false;
	}
	
	//���� Permutation									
	public static void makePassword(char[] a, int c, int l, boolean[] check, char[] answer, int depth) {	
		if(depth == l) {	// L���� ���ĺ� �� ����
			if(check_condition(answer) == false) return;
			System.out.println(String.valueOf(answer));
			return;
		}
		
		for(int i = 0; i < c; i++) {
			if(!check[i]) {		// ���� �湮 ��������
				if(depth >= 1 && a[i] < answer[depth-1]) continue;		// ���� �� ������ ū �ε����� �迭 ������ ����
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
		int L = Integer.parseInt(s.split(" ")[0]);	//L�� �̱�
		int C = Integer.parseInt(s.split(" ")[1]);	//C�� �߿���
		char[] arr = new char[C];					// �־��� ���ĺ� ���� �迭		
		char[] answer = new char[L];				// ���� ���� �迭
		boolean[] check = new boolean[C];			// �ش� ���ĺ� �湮�ߴ��� ���� üũ 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int i = 0;
		while(st.hasMoreTokens()) {
			arr[i++] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);	// ���� ���� 
		
		makePassword(arr, C, L, check, answer, 0);	//	C�� �� L�� �̱�
	}

}
