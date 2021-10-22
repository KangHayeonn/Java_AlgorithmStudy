// 전화번호 목록 ( 프로그래머스 LEVEL2 )

import java.util.Arrays;

public class PhoneBook {
	public static void main(String args[]) {
		String[] phone_book = {"442", "97674223", "1195524421"};
		PhoneBook s = new PhoneBook();
		System.out.println(s.solution(phone_book));
	}
	
	public boolean solution(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for(int i=1; i<phone_book.length; i++) {
            int comparison = phone_book[i].indexOf(phone_book[i-1]);
            if(comparison==0) {
                answer = false;
            }
            else continue;
		}
		return answer;
	}
}