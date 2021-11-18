import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BOJ1987 {

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        int R,C;
        int direction[][] = {{-1,0},{1,0},{0,-1},{0,1}};    //상하좌우
        int coordinate[] ={0,0};
        int direcCode =0 , count =0;

        HashMap<Character,Character> alpha = new LinkedHashMap<Character,Character>();


        String temp, split[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        temp = br.readLine();
        split = temp.split(" ");
        R  = Integer.parseInt(split[0]);
        C =  Integer.parseInt(split[1]);
        char Map[][] = new char[R][C];

        for (int i=0;i<R;i++){
            temp =br.readLine();
            Map[i]= temp.toCharArray();
        }

        move(coordinate,Map,R,C,count,direction , alpha);

        System.out.println(max);

    }
    public static void move(int []coordinate,char[][] Map, int R,int C, int count, int direction[][] ,HashMap<Character,Character> alpha){
        if(coordinate[0]<0||coordinate[0]>=R||coordinate[1]<0||coordinate[1]>=C){
            if(max<count)
                max= count;
            return;
        }
        if(alpha.containsKey(Map[coordinate[0]][coordinate[1]])){       //Map에 이미 있다면
            if(max<count)
                max= count;
            return;
        }else{
            alpha.put(Map[coordinate[0]][coordinate[1]],Map[coordinate[0]][coordinate[1]]);//일단 알파벳을 넣고
            for (int i=0;i<4;i++){
                coordinate[0] += direction[i][0];
                coordinate[1] += direction[i][1];
                move(coordinate, Map, R, C, count+1, direction, alpha);
                coordinate[0] -= direction[i][0];
                coordinate[1] -= direction[i][1];
            }
            alpha.remove(Map[coordinate[0]][coordinate[1]]);
        }
    }

}
