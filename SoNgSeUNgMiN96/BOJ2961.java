/*
    알고리즘 : 재료가 N 개가 주어진다.
    어떤 재료의 조합이 신맛의 곱과 쓴맛의 합의 차이가 적은지를 모르기때문에
    재료를 1개부터 N개까지 뽑는 모든 경우의수를 (조합) 완전 탐색한다.
    재료의 수 N <=10
    이기때문에 N=10일 경우를 가정하여도 ,  10C1 ~10C10의 합은  2^10-1 =1023 시나리오밖에 되지 않아 충분하다.(뽑지않을경우를 빼줌)
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2961 {

    static int min = Integer.MAX_VALUE , N; //min 에 Integer Max로 초기화. N을 combi에서 활용하기위헤 전역변수 선언.

    public static void combi(int origin[][], int p, int pcount , int pick[][] , int start){
        //origin은 재료배열, p는 뽑을 수  , pcount는 뽑은 수 , pick은 뽑은 재료를 저장할 배열, start는 현재 뽑을 요소의 시작점

        if (p==pcount){ //다 뽑은경우 pick에 들어있는 재료로 계산.
            int sour =1,bitter=0;
            for(int i=0;i<p;i++) {
                sour *= pick[i][0];
                bitter += pick[i][1];
            }
            int temp=Math.abs(sour-bitter);
            if(min>temp)
                min= temp;
            return;
        }

        for(int i=start;i<N-p+pcount+1;i++){    //pick에 뽑은것을 넣어주고, 재귀
            pick[pcount][0]=origin[i][0];
            pick[pcount][1]=origin[i][1];
            combi(origin,p,pcount+1,pick,i+1);
        }
    }

    public static void main(String[] args) throws Exception {
        int ingredients[][],pick[][];
        String[] temp =new String[2];   //문자열을 찢어주기위한 임시변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ingredients = new int[N][2];       //요리의 재료들을 저장할 변수
        pick = new int[N][2]; //뽑을 재료들을 저장할 변수

        for(int i=0;i<N;i++){   //입력
            temp = br.readLine().split(" ");
            ingredients[i][0] =Integer.parseInt(temp[0]);
            ingredients[i][1] =Integer.parseInt(temp[1]);
        }


        for(int i=1;i<=N;i++) {     //1개부터 N개까 재료를 뽑아본다.
            combi(ingredients,i,0,pick,0);
        }

        System.out.println(min);

    }
}
