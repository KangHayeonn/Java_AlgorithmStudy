import java.util.*;

class Solution_전화번호목록 {
    static HashSet<String> set = new HashSet<>();
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        for(int i=0; i < phone_book.length; i++){
            set.add(phone_book[i]);
        }
        
        String s;
        for(int i=0; i < phone_book.length; i++){
            s = phone_book[i];
            String ans = "";
            
            for(int j=0; j < s.length(); j++) {
                ans = s.substring(0, j);
                if(set.contains(ans)){
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
}
