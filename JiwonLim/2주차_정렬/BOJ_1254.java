package week2_sorting;

import java.util.Scanner;

/*
 * 팰린드롬 만들기
 * https://www.acmicpc.net/problem/1254
 * 
 * 출력)
 * 만들 수 있는 가장 짧은 팰린드롬의 길이
 */
public class BOJ_1254 {

	public static boolean checkPalindrome(String w) {	// 0 1 2 3
		int len = w.length();
		for(int i = 0; i < len; i++) {
			if(w.charAt(i) != w.charAt(len-i-1)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		int len = word.length();
		
		if(checkPalindrome(word) == true) {
			System.out.println(len);
		}
		else {
			// i번 인덱스부터 끝까지의 문자열이 팰린드롬인지 확인
			// 팰린드롬 맞다면, 앞의 잘라진 문자열만큼 뒤에 더하면 팰린드롬 만족함
			for(int i = 0; i < len; i++) {
				if(checkPalindrome(word.substring(i))) {
					System.out.println(len+i);
					return;
				}
			}
			
			System.out.println(len*2);	// 주어진 문자 그대로 뒤집어 더하기
		}
			
	}

}
