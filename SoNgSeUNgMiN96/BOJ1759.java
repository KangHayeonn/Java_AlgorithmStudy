/*
    알고리즘: L개의 문자로 이루어진 암호를 유추할 수 있는 C개의 문자가 주어진다.
    주어진 문자들에 C개중 L개를 뽑아보고, 그 문자열이 모음1개이상 자음2개이상의 단어라면
    해당 문자를 오름차순으로 정렬한다.
    그리고 정렬한 문자를 Arraylist에 추가한다.
    최종적으로 뽑은 문자들이 추가된 Arraylist또한 오름차순으로 정렬한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BOJ1759 {
    static int C;
    static ArrayList<String> answer = new ArrayList<String>();

    public static void combi(String origin, int p, int pcount , char pick[] , int start){
        //origin은 재료배열, p는 뽑을 수  , pcount는 뽑은 수 , pick은 뽑은 재료를 저장할 배열, start는 현재 뽑을 요소의 시작점

        if (p==pcount){ //다 뽑은경우 pick에 들어있는 재료로 계산.
            int con =0,vow=0;
            for(int i=0;i<p;i++) {
                if(pick[i]=='a'||pick[i]=='e'||pick[i]=='i'||pick[i]=='o'||pick[i]=='u')
                    vow++;
                else
                    con++;
            }
            if(con>=2&&vow>=1) {
                char temptem[] = new char[p];
                String temp="";

                for(int i=0;i<p;i++)
                    temptem[i]= pick[i];
                Arrays.sort(temptem);
                for(int i=0;i<p;i++)
                    temp+=temptem[i];
                answer.add(temp);
            }
            return;
        }

        for(int i=start;i<C-p+pcount+1;i++){    //pick에 뽑은것을 넣어주고, 재귀
            pick[pcount]=origin.charAt(i);
            combi(origin,p,pcount+1,pick,i+1);
        }
    }

    public static void main(String[] args) throws Exception {
        int L;

        String[] temp =new String[2];   //문자열을 찢어주기위한 임시변수
        char [] pick;
        String alpha;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        temp = br.readLine().split(" ");
        L = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        pick = new char[L];

        alpha = br.readLine();
        alpha = alpha.replaceAll("\\s+","");

        combi(alpha,L,0,pick,0);

        Collections.sort(answer, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String i :answer){
            System.out.println(i);
        }
    }
}
