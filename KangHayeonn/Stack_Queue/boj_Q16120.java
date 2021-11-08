/* [ 알고리즘 ] : ( P == PPAP )
 * 
 * 1. 인덱스 0부터 해당 문자열의 끝까지 반복문
 * 2. P일 경우는 스택에 저장
 * 3. A일 경우 스택에서 두개를 pop해서 P인지 확인 (스택에 최소 2개 이상 있어야함)
 * 4. 3번에서 아니면 NP 바로 출력
 * 5. 3번이 맞으면 A 다음 값이 P인지도 확인 (length 범위를 넘지 않는 선에서 -> length를 넘으면 NP)
 * 6. 5번이 아니면 NP 바로 출력
 * 7. 5번이 맞으면 다음 인덱스로 넘어감 (maybe 4번에서 비교한 P가 스택에 저장)
 * 8. 반복문이 끝나면 check를 통해 NP 출력
 * 9. 스택의 길이가 1이면 해당 값이 P인지 확인 후 맞으면 PPAP 출력 / 아닐 경우 NP 출력 
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q16120 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str= br.readLine();
		Stack<Character> stack = new Stack<>();
		boolean check = false; // true일 경우 NP 출력
		int i;
		
		for(i=0; i<str.length(); i++) {

			if(str.charAt(i)=='P') {
				stack.add('P');
			}
			else {
				if(i == str.length()-1) { // ** 이 부분 중용
					check =true;
					break;
				}
				if(stack.size()>=2) { // ** == 중요
					int count = 0;
					while(count < 2) { // P를 두번 지움
						stack.pop(); 
						count++;
					}
					//System.out.println(str.charAt(i+1));
					if(str.charAt(i+1)=='P') {
						continue;
					}
					else {
						check =true;
					}
				} else {
					check =true;
				}
			}
			if(check==true) break;
		}
		
		if(check == true) System.out.print("NP");
		else {
			if(stack.size()==1 && stack.peek()=='P') {   // ** P 한개만 남았을 때 PPAP (PP 는 NP로 침)
	            System.out.print("PPAP");
	        } else {
	            System.out.print("NP");
	        }
		}
	}
}