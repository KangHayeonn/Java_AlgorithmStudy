/*
    알고리즘 : brown과, yellow를 각각 편하게 a, b라고 함.
    식 1: b = x * y
    식 2: a = (x+y+2)*2
    식 1을 통해 x=b/y
    식 2에 대입하고 양변에 y를 곱한다.
    => 2y^2 + (4-a)y +2b
    y에 대한 2차방정식이된다. (여기서 두 근은 각각 가로, 세로 가 될 수 있음. 큰 근을 가로라고 하자
    두 근은 ( a-4 + sqrt((4-a)^2 -16b )/4 ,a-4 - sqrt((4-a)^2 -16b )/4  )가 된다.
    이 근은 yellow의 가로와 세로를 의미함으로
    answer는 각각 2를 더해주면 된다.
 */


public class programmersCarpet_ver2 {

    public static int[] solution(int a, int b) {
        int[] answer = {((a-4)+(int)Math.sqrt((4-a)*(4-a)-16*b))/4+2,((a-4)-(int)Math.sqrt((4-a)*(4-a)-16*b))/4+2};
        return answer;
    }

    public static void main(String[] args) {
            int array [] = solution(10,2);
            System.out.println("["+array[0]+","+array[1]+"]");
    }


}
