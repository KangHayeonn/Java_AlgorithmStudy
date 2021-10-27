import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BOJ2075{

    static HashMap<String, ArrayList> heapMap = new HashMap<String, ArrayList>(); //힙들을 담을 수 있는 힙 맵 구현

    public static void swap(ArrayList list, int a,int b){
        int temp;
        temp = (int)list.get(a);
        list.set(a,list.get(b));
        list.set(b,temp);
    }

    public static boolean makeHeap(String heapName){     //힙을 하나 만들고 힙리스트에 삽입
        ArrayList heap = new ArrayList<Integer>();
        heap.add(0);
        if(heapMap.containsKey(heapName))
            return false;
        else
            heapMap.put(heapName,heap);
        return true;
    }


    public static void insertMaxHeap(String heapName, int data){        //힙이름으로 해당힙을 호출하여, 힙에 데이터 삽입
        ArrayList heap = heapMap.get(heapName);
        heap.set(0,(int)heap.get(0)+1);
        heap.add(data);
        int temp, index = (int)heap.get(0);
        while ((int)heap.get(index)>(int)heap.get(index/2)&&index/2>0){
            swap(heap,index,index/2);
            index/=2;
        }
    }


    public static int deleteRootMaxHeap(String heapName){
        ArrayList heap = heapMap.get(heapName);
        int index = (int)heap.get(0),answer;
        if(index ==0)
            return Integer.MIN_VALUE;
        answer = (int)heap.get(1);
        heap.set(1,heap.get(index));    //마지막 노드를 루트로 넣어줌
        heap.remove(index);
        heap.set(0,(int)heap.get(0)-1);     //마지막노드포인터 갱신

        for (int i=1;i*2<index;){
            if (i*2+1<index){
                if((int)heap.get(i*2)<(int)heap.get(i*2+1)){
                    if((int)heap.get(i)<(int)heap.get(i*2+1)){
                        swap(heap,i,i*2+1);
                        i=i*2+1;
                        continue;
                    }
                    else
                        break;
                }
            }
            if((int)heap.get(i)<(int)heap.get(i*2)) {
                swap(heap, i, i * 2);
                i *= 2;
            }
            else break;
        }
        return answer;
    }

    public static int getIndexHeap(String heapName) {
        ArrayList heap = heapMap.get(heapName);
        return (int)heap.get(0);
    }

    public static void main(String[] args) throws Exception {

        String heapNameTemp = "MaxHeap";
        makeHeap(heapNameTemp);

        int N ,answer=0;
        String temp[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        temp = new String[N];

        for (int i=0;i<N;i++){
            temp = br.readLine().split(" ");
            for (int j=0;j<N;j++) {
                insertMaxHeap(heapNameTemp, Integer.parseInt(temp[j]));
            }
        }

        for (int i=0;i<N;i++) {
            answer = deleteRootMaxHeap(heapNameTemp);
        }
        System.out.println(answer);

    }
}
