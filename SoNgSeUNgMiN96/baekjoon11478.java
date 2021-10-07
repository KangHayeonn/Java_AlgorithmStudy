/*
    백준 11478 서로다른 문자열의 수 문제풀이.
 */

import java.util.HashMap;
import java.util.Scanner;

public class baekjoon11478 {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String text = new String();
        text = scan.next();

        HashMap<String , Integer > hashtest = new HashMap<>();

        for (int i=0;i<text.length();i++) {
            for (int j=1;j+i<=text.length();j++){
                if (!hashtest.containsKey(text.substring(i,i+j))) {
                    hashtest.put(text.substring(i, i + j), 1);
                }
            }
        }


    }

}
