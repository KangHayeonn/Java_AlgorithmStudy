import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ14502 {

    static int R,C ,zerocount =0 ,zerocountTemp , max=Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {

        int direction[][] = {{-1,0},{1,0},{0,-1},{0,1}};    //상하좌우
        int direcCode =0;

        String temp, split[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> virus = new ArrayList<>();
        ArrayList<int[]> emptySpace = new ArrayList<>();
        int []coorTemp;

        temp = br.readLine();
        split = temp.split(" ");
        R  = Integer.parseInt(split[0]);
        C =  Integer.parseInt(split[1]);

        char [][]Map  = new char[R][C];

        for (int i = 0; i < Map.length; i++) {
            temp = br.readLine();
            temp = temp.replaceAll(" ","");
            Map[i] = temp.toCharArray();
            for (int j = 0; j < C; j++) {
                if(Map[i][j]=='2'){       //바이러스의 좌표 추가
                    coorTemp = new int[2];
                    coorTemp[0] = i;
                    coorTemp[1] = j;
                    virus.add(coorTemp);
                }else if (Map[i][j]=='0'){
                    coorTemp = new int[2];
                    coorTemp[0] = i;
                    coorTemp[1] = j;
                    zerocount++;
                    emptySpace.add(coorTemp);
                }
            }
        }
        zerocountTemp = zerocount;
        setWall(emptySpace,0,0,Map,direction,virus);
        System.out.println(max);
    }
    public static void setWall(ArrayList<int[]> emptyspace, int start ,int count,char [][] Map, int [][]direction,ArrayList<int[]> virus ){
        int wall[];
        if (count==3){
            char Maptemp[][] = new char[R][C];
            for (int i = 0; i < Maptemp.length; i++) {
                for (int j = 0; j < C; j++) {
                    Maptemp[i][j] = Map[i][j];
                }
            }
            zerocount = zerocountTemp-3;
            for (int i=0;i<virus.size();i++)
                spread(Maptemp,virus.get(i),direction);

            if (max<zerocount)
                max = zerocount;
            return;
        }
        for (int i=start;i<emptyspace.size();i++){
            wall = emptyspace.get(i);
            Map[wall[0]][wall[1]] = '1';
            setWall(emptyspace,i+1,count+1,Map,direction,virus);
            Map[wall[0]][wall[1]] = '0';
        }
    }

    public static void spread(char [][]Map , int[] virus , int [][]direction){

        for (int i = 0; i <4; i++) {
            if (virus[0]+direction[i][0]>=0&&virus[0]+direction[i][0]<R&&virus[1]+direction[i][1]>=0&&virus[1]+direction[i][1]<C) {
                if(Map[virus[0]+direction[i][0]][virus[1]+direction[i][1]]=='0'){
                    virus[0] +=direction[i][0];
                    virus[1]+=direction[i][1];
                    Map[virus[0]][virus[1]] ='2';
                    zerocount--;
                    spread(Map,virus,direction);
                    virus[0] -=direction[i][0];
                    virus[1] -=direction[i][1];
                }
            }
        }
    }
}
