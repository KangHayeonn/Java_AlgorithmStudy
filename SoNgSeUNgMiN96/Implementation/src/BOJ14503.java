import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14503 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M , map[][] , direction ,dx[]={-1,0,1,0} , dy[] ={0,-1,0,1} ,answer=0;
        boolean visited[][], success;
        Queue<Point> clean = new LinkedList<>();
        Point temp , poll , robot = new Point();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean [N][M];
        st = new StringTokenizer(br.readLine());
        robot.x = Integer.parseInt(st.nextToken());
        robot.y = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());
        if(direction==1)
            direction=3;
        else if(direction==3)
            direction=1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        clean.add(robot);

        loop : while (clean.size()>0){
            poll = clean.poll();
            if(map[poll.x][poll.y]==1){
                System.out.println();
                continue;
            }
            if(visited[poll.x][poll.y]==false){
                visited[poll.x][poll.y] = true;
                answer++;
            }
            success = false;

            for (int i = 0; i < 4; i++) {
                direction++;
                temp = new Point(poll.x+dx[direction%4],poll.y+dy[direction%4]);
                if(temp.x>=0&&temp.x<N&&temp.y>=0&&temp.y<M&&map[temp.x][temp.y]==0&&visited[temp.x][temp.y]==false){
                    clean.add(temp);
                    success = true;
                    break;
                }
            }
            if(!success){
                direction+=2;
                temp = new Point(poll.x+dx[direction%4],poll.y+dy[direction%4]);
                direction+=2;
                if(temp.x>=0&&temp.x<N&&temp.y>=0&&temp.y<M&&map[temp.x][temp.y]==0){
                    clean.add(temp);
                }
                else break loop;
            }
        }
        System.out.println(answer);
    }
}
