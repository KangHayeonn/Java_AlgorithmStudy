/*
    알고리즘 : yellow 와, 격자 모양 카펫은 둘 다 직사각형 구조이다.
    -> yellow는 직사각형 즉 A X B 구조이다. 이 뜻을 해석해 보면 yellow의 개수를 알 때, yellow의 구조는 yellow의
    약수와(루트yellow보다 작은 약수 (짝 약수가 이미 매칭이 될 것이기 때문에) ) 그 yellow/약수 구조이다.
    ex) if yellow == 12  -> 1,2,3 이 구해진다. (루트 12보다 작은) 따라서   (1,12/1) , (2,12/2) , (3,12/3) 3쌍이 있다.
    여기서, 가로는 세로보다 같거나 길기 때문에 짝이 된 약수가 가로이다.  ex) 가로 :12 , 세로 1
    그럼 brown의 경우 직사각형 구조를 유지한 채로 yellow를 감싼다.
    이는 yellow의 좌우에 yellow 세로만큼, 상하에 yellow의 가로만큼 이 추가되고 꼭짓점 부분이 비기 때문에 +4개가 추가됨.
    brown = yellow_height*2 + yellow_width*2 + 4
    즉 yellow의 조합을 탐색하다 brown 공식과 brown이 같다면 우리가 찾는 카펫임.
    이때, 카펫의 세로는 yellow 대비 위아래 1칸이, 가로는 좌우1칸이 길어진 구조이다.
    answer = {yellow_width+2, yellow_height+2}
 */


public class programmersCarpet {
        public static int[] solution(int brown, int yellow) {
            int[] answer = new int[2];
            int height,width;//가로와 세로를 기록할 변수

            for (int i=1;i*i<=yellow;i++){  //i <= 루트 yellow 까지 돌면서 약수를 구한다. 이떄 i가 세로가 되어서, 가로는 항상 세로보다 같거나 크다
                if(yellow%i==0){    //i가 yellow의 약수라면, 가로세로사이즈가 나온다.

                    height = i;  //세로에 대입.
                    width= yellow/i;  //가로에 대입.
                    if((width*2+height*2+4)==brown){    //노랑 가로세로와 브라운의 관계식
                        answer[0] = width+2;       //관계식이 성립할때 큰 너비계산
                        answer[1] = height+2;        //관계식이 성립할때 큰 너비계산
                    }
                }
            }


            return answer;
        }

    public static void main(String[] args) {

            int array [] = solution(10,2);
            System.out.println("["+array[0]+","+array[1]+"]");
    }


}
