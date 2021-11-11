import java.util.Arrays;
import java.util.Comparator;

public class programmersCamera {

    public static int solution(int[][] routes) {

        int answer = 0;
        int endPoint;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                return o1[0] - o2[0];
            }
        });

        endPoint = routes[0][1];        //첫 차량의 종료지점
        answer++;

        for (int i=0;i<routes.length;i++){
            if (routes[i][1]<=endPoint){ //뒷차량의 종료지점이 적다면 , 앤드는 계속 갱신을 하되
                endPoint = routes[i][1];

            }
            else if (routes[i][0]>endPoint){       //뒷 차량의 종료지점이 더 뒤에있고 , 스타트지점이 뒤라면,

                answer++;
                endPoint = routes[i][1];
            }
            System.out.println(endPoint);
        }



        return answer;


    }

    public static void main(String[] args) {
        int arr[][]= {{0,12},{1,12},{2,12},{3,12},{5,6}, {6,12},{10,12}};
        System.out.println(solution(arr));
    }
}


