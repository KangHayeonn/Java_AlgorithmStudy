import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ2470 {
    public static void swap(int arr[], int a, int b){  //여기서 사용하는 배열에 쓸 수 있게 swap함수 구현
        int temp;
        temp = arr[b];
        arr[b] = arr[a];
        arr[a]= temp;
    }

    public static int absolute(int a){
        if (a<0)
            return -a;
        else return a;
    }
    public static int partition(int arr[] , int l, int r){ //퀵소트 여기서 사용하는 배열에 맞게 구현함.
        int pivot , i , j;
        pivot = l;
        i=l;
        j = l+1;
        for (;j<=r;j++){
            if (absolute(arr[j])<absolute(arr[pivot])){
                i++;
                if(i!=j)
                    swap(arr,i,j);
            }
        }
        swap(arr,pivot,i);
        return i;
    }


    public static void quickSort(int arr[], int l, int r){        //퀵소트 여기서 사용하는 배열에 맞게 구현함.
        if (l<r){
            int j= partition(arr,l,r);
            quickSort(arr,l,j-1);
            quickSort(arr,j+1,r);
        }
    }


    public static void main(String[] args) throws Exception{
        int N , partition=0 , d=0;      //partition 이 0이 그대로라면 알칼리만 있는 경우이다. 마지막 인덱스를 출력한다.
        String[] arraytemp;   //문자열을 찢어주기위한 임시변수
        Integer []array;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        arraytemp = new String[N];
        array = new Integer[N];

        arraytemp = br.readLine().split(" ");
        for(int i=0; i<N;i++){
            array[i] = Integer.parseInt(arraytemp[i]);
        }
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return absolute(a)-absolute(b);
            }
        });

        int answer[] = new int[2];   //정답을 기록하는  answer배열
        int diff =absolute( array[0]+array[1]);
        answer[0] =array[0];
        answer[1] =array[1];

        for (int k=1;k+1<N;k++){
            if(diff>absolute(array[k]+array[k+1])){
                diff=absolute(array[k]+array[k+1]);
                answer[0] = array[k];
                answer[1] = array[k+1];
            }
        }
        if(answer[0]>answer[1])
            bw.write(answer[1]+" "+answer[0]);
        else
            bw.write(answer[0]+" "+answer[1]);
        bw.flush();
        bw.close();
    }

}

