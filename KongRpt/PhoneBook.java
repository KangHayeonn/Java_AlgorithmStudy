/* https://programmers.co.kr/learn/courses/30/lessons/42577 */

import java.util.HashMap;

public class PhoneBook {
    public static void main(String[] args) {

        String[] phone_book = {"119", "97674223", "1195524421"};
        boolean answer = true;

        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < phone_book.length; i++) {
            hm.put(phone_book[i], 0);
        }
        System.out.println(hm.entrySet());
        for (String key : hm.keySet()) {
            for (String k : hm.keySet()) {
                if (key == k) continue;
                try {
                    if (k.substring(0, key.length()).equals(key)) {
                        answer = false;
                    }
                }catch (StringIndexOutOfBoundsException e) {
                    continue;
                }
                if (answer == false) break;
            }
            if (answer == false) break;
        }

        System.out.println(answer);


//        boolean flag = true;
//
//        for (int i = 0; i < phone_book.length; i++) {
//            for (int j = i + 1; j < phone_book.length; j++) {
//                if(phone_book[j].startsWith(phone_book[i])) {
//                    flag = false;
//                }
//            }
//        }
//        System.out.println(flag);
        /* 시간 초과 */

//        String[] phone_book = {"54", "235", "24", "5444444"};
//        boolean answer = true;
//
//        HashMap<String, Integer> hm = new HashMap<>();
//
//        for (int i = 0; i < phone_book.length; i++) {
//            hm.put(phone_book[i], 0);
//        }
//
//        for (String key : hm.keySet()) {
//            for (String k : hm.keySet()) {
//                if ( key == k ) continue;
//                for (int i = 1; i < k.length(); i++) {
//                    if (key.equals(k.substring(0,i))){
//                        answer = false;
//                        break;
//                    }
//                    if (answer == false) break;
//                }
//                if (answer == false) break;
//            }
//            if (answer == false) break;
//        }
//
//        System.out.println(answer);
    }
}
