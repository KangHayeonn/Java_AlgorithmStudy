
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class BOJ2109 {

    public static void swap(int a[], int b[]){  //여기서 사용하는 배열에 쓸 수 있게 swap함수 구현
        int temp[] = new int[2];
        temp[0] = b[0];
        temp[1] = b[1];
        b[0] = a[0];
        b[1] = a[1];
        a[0] = temp[0];
        a[1] = temp[1];
    }
    public static int partition(int arr[][] , int l, int r){ //퀵소트 여기서 사용하는 배열에 맞게 구현함.
        int pivot , i , j, temp;
        pivot = l;
        i=l;
        j = l+1;
        for (;j<=r;j++){
            if (arr[j][1]<arr[pivot][1]){
                i++;
                if(i!=j)
                    swap(arr[i],arr[j]);
            }
        }
        swap(arr[pivot],arr[i]);
        return i;
    }


    public static void quickSort(int arr[][], int l, int r){        //퀵소트 여기서 사용하는 배열에 맞게 구현함.
        if (l<r){
            int j= partition(arr,l,r);
            quickSort(arr,l,j-1);
            quickSort(arr,j+1,r);
        }
    }

    public static void main(String[] args) throws Exception{
        int N , d , sum=0;
        String[] temp =new String[2];   //문자열을 찢어주기위한 임시변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList list = new ArrayList();   //이 어레이리스트는 우선순위큐를 구현하기 전 임시로 쓰는 어레이입니다. 추후 우선순위큐를 이진트리로(힙이나) 구현하여야 시간복잡도가 줍니다.

        if (N==0){      //0이 들어올 경우 0만 출력하고 프로그램을 종료한다.
            System.out.println(0);
            return;
        }
        int array[][] = new int[N][2];

        for (int i=0;i<N;i++){      //입력을 받는 부분
            temp = br.readLine().split(" ");
            array[i][0] = Integer.parseInt(temp[0]);
            array[i][1] = Integer.parseInt(temp[1]);
        }
        quickSort(array, 0,N-1);    //강연일을 기준으로 퀵소트를 해줍니다.

        d =array[N-1][1];//현재 넣은 마감일
        list.add(array[N-1][0]);
        int max=0, index;

        for (int i=N-2;i>=0;i--){
            if (d!=array[i][1]){//마감일이 같지않다면 방금 전까지의 마감일까지는 넣음
                index =d -array[i][1]; // 몇개를 넣을 수 있을지
                Collections.sort(list, Collections.reverseOrder());
                for(int j=0;j<index;j++) {
                    if (list.isEmpty())
                        break;
                    sum += (int) list.get(0);
                    list.remove(0);
                }
                list.add(array[i][0]);
                d = array[i][1];
            }else{
                list.add(array[i][0]);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        for(int j=0;j<d;j++) {
            if (list.isEmpty())
                break;
            sum += (int) list.get(0);
            list.remove(0);
        }
        System.out.print(sum+max);

    }
}