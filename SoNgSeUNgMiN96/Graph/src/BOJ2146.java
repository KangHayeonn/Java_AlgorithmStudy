import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146{

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

    public static int [][] direc= {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws Exception{

        int N , area=0,dis;
        char map[][];
        int min = Integer.MAX_VALUE;
        boolean visited[][], edgevisited[][];
        ArrayList<coord> edge[] = new ArrayList[5000];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        edgevisited = new boolean[N][N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                map[i][j] = (char)(Integer.parseInt(st.nextToken()));
            }
        }


        for (char i=0;i<N;i++){
            for (char j=0;j<N;j++){
                if (map[i][j]==1){
                    division(map ,i, j,visited,edge,N, area,edgevisited);
                    area++;
                }
            }
        }

        for (int i=0;i<area;i++){
            for (coord temp : edge[i]){
                for (int j=i+1;j<area;j++){

                    for (coord temp2:edge[j]){
                        dis = abs(temp.x-temp2.x)+abs(temp.y-temp2.y) -1;
                        if (dis<min)
                            min = dis;
                    }

                }
            }
        }
        System.out.println(min);
    }
    public static int abs (int a){
        return (a>0)?  a : -a;
    }

    public static void division(char [][]map , char x, char y, boolean[][] visited, ArrayList<coord>[] edge, int N ,int area,boolean[][] edgevisited){

        Queue<coord> queue = new LinkedList<>();
        coord poll , temp;
        edge[area] = new ArrayList<coord>();
        queue.add(new coord(x,y));


        while (queue.size()>0) {
            poll = queue.poll();
            visited[poll.x][poll.y] = true;
            map[poll.x][poll.y] = 2;
            temp = new coord(poll.x, poll.y);
            for (int i = 0; i < 4; i++) {
                temp = new coord((char)(poll.x + direc[i][0]), (char)(poll.y + direc[i][1]));

                if (temp.x >= 0 && temp.x < N && temp.y >= 0 && temp.y < N) {
                    if (map[temp.x][temp.y] == 1 && !visited[temp.x][temp.y]) {
                        queue.add(new coord(temp.x, temp.y));
                        visited[temp.x][temp.y] = true;
                    }
                    if (map[temp.x][temp.y] == 0 && !edgevisited[poll.x][poll.y]) {
                        edge[area].add(poll);
                        edgevisited[poll.x][poll.y] = true;
                    }
                }
            }
        }
    }
}
