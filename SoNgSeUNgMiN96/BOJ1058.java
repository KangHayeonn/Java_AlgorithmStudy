/*
    알고리즘:  2-친구의 수가 가장많은 사람을 구하는 문제이다.
    우선 문제에서 2-친구에대한 설명이 부실해서 보충설명을 적자면, 친구는 나의 직접적인 친구를 의미하고 2-친구는 내친구의 친구까지 포괄한다.
    다시말해서 A가 친구가 B밖에 없어도, B의친구가 A,C,D라면 C,D는 A의 2-친구이다. (2차관계까지를 포함한다는 의미인것으로 해석했다)
    그렇다면 이 문제는 입력받을 1차 친구맵을 받고, 2-친구를 저장할 2-친구맵을 만든 후 N명을 각각 탐색한다.
    탐색은 i라는 사람의 친구 j가 있었을때, 2-친구맵에 j가 친구임을 추가해주고, j의 친구들을 다시 i의 2-친구맵에 추가해준다.
    이과정을 N까지 반복하여 i의 탐색이 종료되었을때, 마찬가지로 N명의 사람에대해 이를 반복한다.
    사람은 최대 50명이니 최대시나리오는 50*50*50 = 125000회까지 돌 수 있다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1058 {

    public static void main(String[] args) throws Exception {
        int N, temp, max=Integer.MIN_VALUE;
        String friends[];
        int friendmap[][] = new int[50][50];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        friends = new String[N];

        for (int i = 0; i < N; i++) {   //입력
            friends[i] = br.readLine();
        }

        for (int i = 0; i < N; i++) {
            for (int j=0;j<N;j++) {
                if (friends[i].charAt(j)=='Y'){     //현재 i와 j가 친구라면, 2-친구표에 추가하고, 그친구의 친구들도 추가해준다.
                    friendmap[i][j] = 1;
                    for (int k=0;k<N;k++){
                        if (k!=i&&friends[j].charAt(k)=='Y')        //현재 j의친구 k가 i가아니라면, i와 제2친구이다.
                            friendmap[i][k]=1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {       //제2친구 map을 돈다.
            temp = 0;
            for (int j=0;j<N;j++) {     //제2친구의 수를 세어준다.
                if(friendmap[i][j]==1)
                    temp++;
            }
            if(max<temp)        //가장많은 제2친구를 가진 사람의 수가 저장됨.
                max = temp;
        }
        System.out.println(max);
    }
}
