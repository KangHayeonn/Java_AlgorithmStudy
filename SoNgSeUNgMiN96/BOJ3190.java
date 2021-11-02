import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ3190 {

    static class timeDi{    //명령
        int time;
        char dire;
        timeDi(int time,char dire){this.time = time; this.dire = dire;}
    }

    static class coord{ //좌표
        int x;
        int y;
        coord(int x,int y){this.x = x; this.y = y;}
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int direc[][] = {{0,1},{1,0},{0,-1},{-1,0}};        //우,하,좌,상 방향
        int direccode = 0;  //방향을 결정할코드
        int N ,K, L , time=0 , x=0,y=0 ;

        String temp[];

        Queue<coord> queue = new LinkedList<>();
        Queue<timeDi> queue2 = new LinkedList<>();

        N = Integer.parseInt(br.readLine());
        temp = new String[2];

        int Map[][] = new int[N][N];        //맵에서 땅은 0 뱀은 2 사과는 1로 존재한다.

        K = Integer.parseInt(br.readLine());
        for (int i=0;i<K;i++){
            temp = br.readLine().split(" ");
            Map[Integer.parseInt(temp[0])-1][Integer.parseInt(temp[1])-1] = 1;  //사과 기록
        }

        L = Integer.parseInt(br.readLine());
        for (int i=0;i<L;i++){
            temp = br.readLine().split(" ");
            queue2.add(new timeDi( Integer.parseInt(temp[0])  ,  temp[1].charAt(0)  ));     //명령큐

        }


        Map[x][y] = 2;      //첫 위치를 큐에 넣어준다.
        queue.add(new coord(x,y));

        while (x>=0&&x<N&&y>=0&&y<N){   //map을 벗어나기 전까지

            if(queue2.size()>0&&queue2.peek().time==time){      //혹시 현재 시간에 명령을 수행할게 있다면
                if (queue2.poll().dire=='D'){
                    direccode++;        //명령코드 우측 90도
                }else{
                    direccode+=3;       //명령코드 우측 270도 (좌측90도)
                }
            }
            time++;     //1초 흐르고

            x+=direc[direccode%4][0];       //방향대로 움직이고
            y+=direc[direccode%4][1];

            if (x>=0&&x<N&&y>=0&&y<N){      //맵밖으로 나간게 아니라면
                if(Map[x][y]==2){       //뱀의 꼬리를 밟으면 게임오버
                    break;
                }else if(Map[x][y]==0){ //땅을 밟으면 현재 땅은 뱀이되고 뱀의 꼬리는 땅이된다.

                    coord cod = queue.poll();
                    Map[x][y] = 2;
                    Map[cod.x][cod.y] = 0;
                    queue.add(new coord(x,y));

                }else{      //사과를 먹으면 현재사과만 뱀이되고, 꼬리는 유지
                    Map[x][y] = 2;
                    queue.add(new coord(x,y));
                }
            }
        }
        System.out.println(time);
    }
}
