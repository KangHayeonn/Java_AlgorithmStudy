import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ2457{

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) , answer =1;

        int monthIdx[] = {0,31,61,92,122,153,184,214,245};
        ArrayList <int[]> flower = new ArrayList<int[]>();


        int temp[];
        String input[]  = new String[4];


        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            temp = new int [4];
            for (int j = 0; j <4; j++) {
                temp[j] = Integer.parseInt(input[j]);
            }
            if (temp[0]<3){
                if (temp[2]<3){     //이 꽃은 버려도 됨.
                    continue;
                }else {
                    temp[0]= 3;
                    temp [1] = 1;
                }
            }if (temp[2]==12){
                if (temp[0]==12)     //이 꽃도 버려도됨.
                    continue;
                else{
                    temp[2]= 11;
                    temp [3] = 31;
                }
            }
            flower.add(temp);
        }

        Collections.sort(flower, new Comparator<int[]>() {      //시작 날짜를 기준으로 정렬.
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]) {     //시작 월이 동일하다면
                    if (o1[1] == o2[1]){        //시작 날짜까지 동일하다면
                        if (o1[2]==o2[2])       //마지막 월이 동일하다면
                            return o2[3] - o1[3] ;      //기간이 긴놈이 앞에간다
                        else
                            return o2[2] - o1[2];        //기간이 긴놈이 앞에간다
                    }else{
                        return o1[1] - o2[1];       //시작날짜가 빠른놈이 앞으로간다.
                    }
                }
                else return o1[0] - o2[0];      //시작월이 다르면 빠른놈이 앞에간다
            }
        });

        int flowtemp[] = flower.get(0);
        if (!(flowtemp[0]==3&&flowtemp[1]==1)){     //첫 꽃이 3월 1일이아니라면
            System.out.println(0);
            System.exit(0);
        }


        int endPointOne = monthIdx[flowtemp[2]-3] + flowtemp[3]-1 , start,end , endPointTwo;


        for (int i=1;(i<flower.size())&&endPointOne !=275;i++){

            int flowerTemp[] = flower.get(i);       //새로운 꽃을 잡는다
            start = monthIdx[flowerTemp[0]-3] + flowerTemp[1]-1;        //새로운 꽃의 시작주소
            end = monthIdx[flowerTemp[2]-3] + flowerTemp[3]-1;      //새로운 꽃의 끝값
            if(start>endPointOne){      //기존꽃과의 빈틈이 발생하면, 빈날이있어 0을 출력하고 종료
                System.out.println(0);
                System.exit(0);
            }


            answer++;       //일단 새 꽃을 추가하고, 기존꽃의 엔드포인트보다 시작점이 작은애들중, 가장 끝지점이 늦은 아이를 찾는다.
            endPointTwo = endPointOne;      //첫 꽃의 끝지점을 넣어줌

            while (i<flower.size()&&start<=endPointOne){        //꽃 사이즈 안에들고 시작점이 기존비교꽃보다 작은경우 끝값을계속 갱신함

                if (endPointTwo<end){
                    endPointTwo = end;
                }
                i++;
                if (i==flower.size())
                    break;
                flowerTemp = flower.get(i);     //현재 꽃을 기준으로 갱신함,.
                start = monthIdx[flowerTemp[0]-3] + flowerTemp[1]-1;
                end = monthIdx[flowerTemp[2]-3] + flowerTemp[3]-1;
            }

            endPointOne = endPointTwo;
            i--;

        }
        if (endPointOne==275)
            System.out.println(answer);
        else
            System.out.println(0);
    }

}

