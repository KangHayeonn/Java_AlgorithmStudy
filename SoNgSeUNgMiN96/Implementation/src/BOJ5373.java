import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
    윗면을 기준으로 돌리는 방향이 각각 상단부이다. 윗면은 최초로 상단이 B, 하단이 F , 좨측이 L, 우측이 R이다.
    color 0 : w 흰색, 1 : r 빨강, 2: o 오렌지, 3: g 초록, 4 : b 파랑, 5: y 노랑
    LU : 왼쪽 상단 , U : 상단 , RU : 오른쪽 상단, L : 왼쪽, R : 오른쪽 , LD : 좌측하단, D : 하단, RD : 우측하단
    U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면이다.
    leftcode, rightcode, upcode, downcode 가 뜻하는 바. 0 : 상단을 돌리는 것, 1: 우측을 돌림, 2 : 하단을 돌림, 3 : 좌측을 돌림.
    U면은 B방향 , D면은 F 방향 , 옆 4면은 상단이 각각 U 방향이다.
    윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색이다.
 */

class faces{
    int coreColor; //면의 축
    int LU, U, RU, L, R, LD, D, RD; //한 면의 구성색상변수
    faces left, right, up, down;    //좌측면, 우측면, 윗면, 아랫면
    int leftcode, rightcode, upcode, downcode;

    faces(int coreColor , int LU,int U, int RU, int L, int R, int LD, int D, int RD){ //생성자.
        this.coreColor = coreColor;
        this.U = U;
        this.LU = LU;
        this.RU = RU;
        this.L = L;
        this.R = R;
        this.LD = LD;
        this.D  = D;
        this.RD = RD;
    }
}
class  cube{
    faces U, D, F, B, L, R;
    static faces match[];
    static char colors[] ={'w','r','o','g','b','y'};

    cube(){     //큐브의 면들의 연관관계를 설정합니다.
        U = new faces(0,0,0,0,0,0,0,0,0);       //윗면은 흰색
        D = new faces(5,5,5,5,5,5,5,5,5);       //아랫면은 노랑색
        F = new faces(1,1,1,1,1,1,1,1,1);       //앞면은 빨강색
        B = new faces(2,2,2,2,2,2,2,2,2);       //뒷면은오렌지섹
        L = new faces(3,3,3,3,3,3,3,3,3);       //왼쪽면은 초록색
        R = new faces(4,4,4,4,4,4,4,4,4);       //오른쪽면은 파란색
        U.left = L;     //윗면읜 좌측은 왼쪽면
        U.leftcode = 0;     //왼쪽면의 위가 링크
        U.right = R;        //윗면의 우측은 오른쪽면
        U.rightcode =0;     //오른쪽면의 위가 링크
        U.down = F;     //윗면의 하단면은 앞면
        U.downcode =0;      //앞면의 위가 링크
        U.up = B;       //윗면의 윗면은 뒷면
        U.upcode =0;        //뒷면의 위가 링크

        D.up = F;       //아랫면의 위는 앞면
        D.upcode = 2;           //아래가링크
        D.left = L;     //아랫면의 왼쪽면은 왼쪽면
        D.leftcode =2;      //아래가링크
        D.right = R;        //오른쪽은 오른쪽
        D.rightcode =2;     //아래가 링크
        D.down = B;     //아래는 뒷면
        D.downcode =2;      //아래가 링크

        F.up = U;       //F의 위는 U
        F.upcode = 2;       //U의 아래가 링크
        F.left = L;     //왼쪽은 왼쪽면
        F.leftcode = 1;     //오른쪽링크
        F.right = R;
        F.rightcode =3; //왼쪽면링크
        F.down = D;
        F.downcode = 0; //위쪽면링크

        L.up = U;
        L.upcode = 3;
        L.right =F;
        L.rightcode = 3;
        L.down = D;
        L.downcode = 3;
        L.left = B;
        L.leftcode =1;

        R.right = B;
        R.rightcode = 3;
        R.left = F;
        R.leftcode = 1;
        R.up = U;
        R.upcode = 1;
        R.down = D;
        R.downcode = 1;

        B.up = U;
        B.upcode = 0;
        B.right = L;
        B.rightcode = 3;
        B.left = R;
        B.leftcode =1;
        B.down = D;
        B.downcode = 2;

        match = new faces[6];
        match[0] = U;
        match[1] = D;
        match[2] = F;
        match[3] = B;
        match[4] = L;
        match[5] = R;
    }
    public static void clockwise(int facecode){
        int temp[] , backup[];
        //윗쪽면을 벡업하고, 위쪽면에 왼쪽면을 대입, 왼쪽면에 아랜면을 대입, 아랫면에 오른쪽 면을 대입, 오른쪽면에 벡업을 대입 순서로 진행
        backup = getSide(match[facecode].up,match[facecode].upcode);       //윗면을 벡업
        temp = getSide(match[facecode].left,match[facecode].leftcode);  //왼쪽면을 저장
        setSide(match[facecode].up,match[facecode].upcode,temp,match[facecode].leftcode);    //윗면에 왼쪽면을 대입.
        temp = getSide(match[facecode].down,match[facecode].downcode);  //아랫면을 저장
        setSide(match[facecode].left,match[facecode].leftcode,temp,match[facecode].downcode);    //왼쪽면에 아랫면을 저장
        temp = getSide(match[facecode].right,match[facecode].rightcode);    //오른면을 저장
        setSide(match[facecode].down,match[facecode].downcode ,temp,match[facecode].rightcode);   //아랫면에 오른면을 대입
        setSide(match[facecode].right,match[facecode].rightcode,backup,match[facecode].upcode);    //오른면에 백업(윗면을 대입)


        //이제 본 면을 회전할 차레이다.

        int arr[] = new int[3];
        arr[0] = match[facecode].LU;
        arr[1] = match[facecode].U;
        arr[2] = match[facecode].RU;

        match[facecode].RU=match[facecode].LU;
        match[facecode].U=match[facecode].L;
        match[facecode].LU=match[facecode].LD;
        match[facecode].L = match[facecode].D;
        match[facecode].LD = match[facecode].RD;
        match[facecode].D = match[facecode].R;
        match[facecode].RD = arr[2];
        match[facecode].R = arr[1];
    }

    public static void reverseclockwise(int facecode){
        int temp[] , backup[];
        //윗쪽면을 벡업하고, 위쪽면에 오른면을 대입, 오른면에 아랫면을 대입, 아랫면에 왼쪽 면을 대입, 왼쪽면에 벡업을 대입 순서로 진행
        backup = getSide(match[facecode].up,match[facecode].upcode);       //윗면을 벡업
        temp = getSide(match[facecode].right,match[facecode].rightcode);  //오른쪽면을 저장
        setSide(match[facecode].up,match[facecode].upcode,temp,match[facecode].rightcode);    //윗면에 오른쪽면을 대입.
        temp = getSide(match[facecode].down,match[facecode].downcode);  //아랫면을 저장
        setSide(match[facecode].right,match[facecode].rightcode,temp,match[facecode].downcode);    //오른면에 아랫면을 대
        temp = getSide(match[facecode].left,match[facecode].leftcode);    //왼쪽면을 저장
        setSide(match[facecode].down,match[facecode].downcode ,temp,match[facecode].leftcode);   //아랫면에 왼쪽면을 대입
        setSide(match[facecode].left,match[facecode].leftcode,backup,match[facecode].upcode);    //왼쪽면에 백업(윗면을 대입)

        //이제 본 면을 회전할 차레이다.
        int arr[] = new int[3];
        arr[0] = match[facecode].LU;
        arr[1] = match[facecode].U;
        arr[2] = match[facecode].RU;
        match[facecode].LU=match[facecode].RU;
        match[facecode].U=match[facecode].R;
        match[facecode].RU=match[facecode].RD;
        match[facecode].R=match[facecode].D;
        match[facecode].RD=match[facecode].LD;
        match[facecode].D=match[facecode].L;
        match[facecode].LD=arr[0];
        match[facecode].L=arr[1];
    }
    public static int[] getSide(faces face, int sidecode){
        int temp[] = new int[3];

        switch (sidecode){
            case 0:
                temp[0] = face.LU;
                temp[1] = face.U;
                temp[2] = face.RU;
                break;
            case 1:
                temp[0] = face.RU;
                temp[1] = face.R;
                temp[2] = face.RD;
                break;
            case 2:
                temp[0] = face.LD;
                temp[1] = face.D;
                temp[2] = face.RD;
                break;
            case 3:
                temp[0] = face.LU;
                temp[1] = face.L;
                temp[2] = face.LD;
                break;
            default:
                break;
        }
        return temp;
    }
    public static void setSide(faces face , int sidecode, int temp[],int beforecode){

        switch (sidecode){
            case 0:
                if(beforecode==3){      //윗모서리에 좌측모서리를 대입할 때에는 역으로 뒤집어준다
                    face.RU = temp[0];
                    face.U = temp[1];
                    face.LU = temp[2];
                }else{
                    face.LU = temp[0];
                    face.U = temp[1];
                    face.RU = temp[2];
                }
                break;
            case 1:
                if(beforecode==1||beforecode==0){   //우측모서리에에 우측이나 윗모서리을 대입할때는 그대로 대입
                    face.RU = temp[0];
                    face.R = temp[1];
                    face.RD = temp[2];
                }else {     //아닐때는 반전하여 넣어준다.
                    face.RD = temp[0];
                    face.R = temp[1];
                    face.RU = temp[2];
                }
                break;
            case 2:
                if(beforecode==1){      //하단모서리에 오른쪽 모서리를 대입할때에는 반전
                    face.RD = temp[0];
                    face.D = temp[1];
                    face.LD = temp[2];
                }else {     //아닐때는 원래대로 대입
                    face.LD = temp[0];
                    face.D = temp[1];
                    face.RD = temp[2];
                }
                break;
            case 3:
                if(beforecode==1||beforecode==0){       //왼쪽모서리에 오른쪽이나, 윗모서리를 대입할때는 반전
                    face.LD = temp[0];
                    face.L = temp[1];
                    face.LU = temp[2];
                }else{      //아닐때는 원래대로 대입한다.
                    face.LU = temp[0];
                    face.L = temp[1];
                    face.LD = temp[2];
                }
                break;
            default:
                break;
        }
    }
    public static String printU(){        //윗면 출력
        return printfaces(match[0]);
    }
    public static String printD(){        //아랫면 출력
        return printfaces(match[1]);
    }
    public static String printF(){        //앞면 출력
        return printfaces(match[2]);
    }
    public static String printB(){        //뒷면 출력
        return printfaces(match[3]);
    }
    public static String printL(){        //왼쪽면 출력
        return printfaces(match[4]);
    }
    public static String printR(){        //오른쪽면 출력
        return printfaces(match[5]);
    }

    public static String  printfaces(faces face){     //면을 대입하여, 해당 면을 출력한다.
        String temp="";
        temp +=colors[face.LU]+""+colors[face.U]+""+colors[face.RU]+"\n";
        temp +=colors[face.L]+""+colors[face.coreColor]+""+colors[face.R]+"\n";
        temp +=colors[face.LD]+""+colors[face.D]+""+colors[face.RD]+"\n";
        return temp;
    }
}

public class BOJ5373 {
    public static void main(String[] args) throws Exception{
        int N, M;
        char colors[] ={'w','r','o','g','b','y'};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<Character,Integer> facetocode = new HashMap<>();
        facetocode.put('U',0);
        facetocode.put('D',1);
        facetocode.put('F',2);
        facetocode.put('B',3);
        facetocode.put('L',4);
        facetocode.put('R',5);

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            M = Integer.parseInt(br.readLine());
            cube mainCube = new cube();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <M; j++) {
                char temp[] = st.nextToken().toCharArray();
                if (temp[1]=='+'){
                    mainCube.clockwise(facetocode.get(temp[0]));
                }else{
                    mainCube.reverseclockwise(facetocode.get(temp[0]));
                }
            }
            bw.write(cube.printU());
        }
        bw.flush();
        bw.flush();
    }

}
