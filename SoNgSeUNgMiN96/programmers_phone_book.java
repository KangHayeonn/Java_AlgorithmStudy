/*
    프로그래머스 전화번호 목록 풀이
 */

import java.util.HashMap;
import java.util.Objects;

public class programmers_phone_book {

    public static boolean solution(String[] phone_book) {

        boolean answer = true;
        String temp,temp2;
        int len, len2;
        HashMap<String , String > hashMap = new HashMap<>();
        for (int i=0;i<phone_book.length;i++){
            hashMap.put(phone_book[i],phone_book[i]);
        }
        System.out.println(hashMap);
        for(Object key:hashMap.keySet()){
            temp = key.toString();
            len = temp.length();
            for(Object key2:hashMap.keySet()) {
                if(Objects.equals(key,key2)){
                    temp2 = key2.toString();
                    len2 = temp2.length();
                    if(len<=len2&&temp.charAt(0)==temp2.charAt(0)){
                        temp2 = temp2.substring(0,len);
                        if(temp.equals(temp2))
                            return false;
                    }
                }
            }
        }


        return answer;
    }

    public static void main(String[] args) {

        String[] phone_book = new String[]{"123", "456", "789"};
        System.out.println(solution(phone_book));
    }
}





