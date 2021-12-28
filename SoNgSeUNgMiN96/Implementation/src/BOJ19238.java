import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Texi{
    int x,y,k,ktemp;
    Texi(int x, int y, int k, int ktemp){
        this.x = x;
        this.y= y;
        this.k = k;
        this.ktemp = ktemp;
    }
}

public class BOJ19238 {

    public static void main(String[] args) throws Exception {
        int N , M , K, MAP[][] , ktemp=0;
        boolean visited[][];
        HashMap<String,Point> cusdes = new HashMap<String,Point>();
        String customer="";
        Point tempcus, tempdes;

        // N = 맵 사이즈 , M = 손님 수 , K = 연료의 양 , MAP = 맵 , Ktemp는 손님을 태운후연료량
        Queue<Texi> driving;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Texi texi;   //택시의 좌표를 담을 포인트 변수
        Texi temp , poll;   //임시로 좌표를 담을 temp 변수
        int dx[] = {-1,0,0,1} , dy[] = {0,-1,1,0};

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                MAP[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        texi = new Texi(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,K,0);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            tempcus = new Point(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
            String tt = tempcus.toString();
            tempdes = new Point(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
            cusdes.put(tt,tempdes);
        }

        for (int i = 0; i < M; i++) {

            driving = new LinkedList();
            driving.add(texi);
            boolean success =false;

            visited = new boolean[N][N];
            visited[texi.x][texi.y] = true;

            loop1 :while (driving.size()>0){     //손님을 태우는 구간

                poll = driving.poll();
                tempcus = new Point(poll.x,poll.y);

                if (cusdes.containsKey(tempcus.toString())&&poll.k>=0) {  //이게 지금 태워야할 손님이라면
                    if (!success) {
                        texi = poll;
                        success =true;
                        texi.ktemp = 0;
                        ktemp = texi.k;
                        customer = tempcus.toString();
                        break loop1;
                    }
                }

                if(poll.k==0)
                    continue;

                for (int j = 0; j < 4; j++) {       //4방향 탐색
                    temp = new Texi(poll.x + dx[j], poll.y + dy[j], poll.k - 1,0);
                    if(!(temp.x>=0&&temp.x<N&&temp.y<N&&temp.y>=0)||MAP[temp.x][temp.y]==1||visited[temp.x][temp.y]==true)     //맵 사이즈 안이 아니라면
                        continue;

                    tempcus = new Point(temp.x,temp.y);
                    if (cusdes.containsKey(tempcus.toString())&&poll.k>=0) {  //이게 지금 태워야할 손님이라면
                        if (!success){  //처음 발견한 손님인가?
                            success = true;
                            texi = temp;
                            ktemp = texi.k;
                            customer = tempcus.toString();
                        }else{
                            if (ktemp>temp.k)
                                continue;
                            else if (ktemp<temp.k){
                                texi = temp;
                                ktemp = texi.k;
                                customer = tempcus.toString();
                            }else if (texi.x>temp.x){       //행이 작을때
                                texi = temp;
                                ktemp = texi.k;
                                customer = tempcus.toString();
                            }else if (texi.x==temp.x&&texi.y>temp.y){
                                texi = temp;
                                ktemp = texi.k;
                                customer = tempcus.toString();
                            }
                        }
                    }
                    visited[temp.x][temp.y]=true;
                    driving.add(temp);
                }
            }

            if (!success){
                System.out.println(-1);
                System.exit(0);
            }

            success =false;
            driving = new LinkedList();
            driving.add(texi);
            visited = new boolean[N][N];
            visited[texi.x][texi.y] = true;

            loop2 : while (driving.size()>0){     //손님을 내려줄 구간.
                poll = driving.poll();
                if(poll.k==0)
                    continue;
                for (int j = 0; j < 4; j++) {
                    temp = new Texi(poll.x + dx[j], poll.y + dy[j], poll.k - 1,poll.ktemp+1);
                    if(!(temp.x>=0&&temp.x<N&&temp.y<N&&temp.y>=0)||MAP[temp.x][temp.y]==1||visited[temp.x][temp.y]==true)     //맵 사이즈 안이 아니라면
                        continue;
                    tempcus = new Point(temp.x,temp.y);

                    if (tempcus.toString().equals(cusdes.get(customer.toString()).toString())&&temp.k>=0) {  //이게 지금 손님의 도착지라면
                        texi = temp;
                        texi.k = texi.k + texi.ktemp*2;
                        success = true;
                        break loop2;
                    }
                    visited[temp.x][temp.y]=true;
                    driving.add(temp);
                }
            }
            if (!success){
                System.out.println(-1);
                System.exit(0);
            }
            cusdes.remove(customer.toString());
        }
        System.out.println(texi.k);
    }
}
