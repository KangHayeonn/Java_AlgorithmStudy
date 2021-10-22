
/*
    프로그래머스 전화번호 목록 풀이
 */

import java.util.Arrays;

public class programmers_phone_book2 {

    public static boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);
        boolean answer = true;
        for (int i=0;i<phone_book.length-1;i++){
            int len = phone_book[i].length();
            if(phone_book[i+1].length()>=len){
                if(phone_book[i+1].substring(0,len).equals(phone_book[i]))
                    return false;
            }
        }


        return answer;
    }

    public static void main(String[] args) {

        String[] phone_book = new String[]{"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }
}







