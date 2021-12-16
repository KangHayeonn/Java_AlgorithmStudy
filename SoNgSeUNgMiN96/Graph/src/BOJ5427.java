import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ5427 {

    public static class coord{
        char x, y;
        coord(char x,char y){
            this.x = x;
            this.y = y;
        }
        public void settcoord(char x,char y){
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws Exception{
        int N , r,c;

        char map[][];
        boolean visited[][];
        String temp;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            ArrayList <coord> firelist = new ArrayList<>();

            map = new char[r][c];
            visited = new boolean[r][c];
            for (int j=0;j<r;j++){
                temp = br.readLine();
                map[j] = temp.toCharArray();
                for (int k=0;k<c;k++){
                    if (map[j][k]=='*'){

                    }
                }
            }


        }




    }

}
