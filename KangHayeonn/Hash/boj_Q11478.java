// 서로 다른 부분 문자열의 개수 (백준 11478번)
package baekjoon;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q11478 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String bf = sc.next();
		Set<String> hashSet = new HashSet<String>();
		
		for(int i=1; i<=bf.length(); i++) {
			for(int j=0; j<bf.length(); j++) {
				if(j+i > bf.length()) break;
				else hashSet.add(bf.substring(j, j+i));
			}
		}
		System.out.println(hashSet.size());
		
	}
}
