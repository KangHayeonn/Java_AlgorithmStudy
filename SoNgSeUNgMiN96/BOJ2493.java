import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


class heapImp2<E>{      //지난번 구현해둔 힙 재탕!
    public ArrayList<E> heap;

    public heapImp2(){
        heap = new ArrayList<>();
    }

    public int getHeapSize(){
        return heap.size();
    }

    public void makeHeap(){
        E first = null;
        heap.add(first);
    }

    public void swap(ArrayList list, int a,int b){
        Object temp;
        temp = list.get(a);
        list.set(a,list.get(b));
        list.set(b,temp);
    }

    public void insertMinHeap(E data){        //힙이름으로 해당힙을 호출하여, 힙에 데이터 삽입
        heap.add(data);
        int index = heap.size()-1;

        while (index/2>0&&((int[])heap.get(index))[1]<((int[])heap.get(index/2))[1]){
            swap(heap,index,index/2);
            index/=2;
        }
    }
    public E preview(){
        return (E)heap.get(1);
    }


    public E deleteRootMinHeap(){
        int index = heap.size()-1;
        E answer;
        if(index ==0)
            return null;

        answer = (E)heap.get(1);
        heap.set(1,heap.get(index));    //마지막 노드를 루트로 넣어줌
        heap.remove(index);

        for (int i=1;i*2<index;){   //
            if (i*2+1<index){       //오른쪽 자식도 존재한다면,
                if(((int[])heap.get(i*2))[1]>((int[])heap.get(i*2+1))[1]){      //그리고 오쪽 자식이 작다면
                    if(((int[])heap.get(i))[1]>((int[])heap.get(i*2+1))[1]){        //그리고 오른쪽 자식이 나보다 작다면
                        swap(heap,i,i*2+1);
                        i=i*2+1;
                        continue;
                    }
                    else
                        break;
                }
            }
            if(((int[])heap.get(i))[1]>((int[])heap.get(i*2))[1]) {
                swap(heap, i, i * 2);
                i *= 2;
            }
            else break;
        }
        return answer;
    }
}



public class BOJ2493 {

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
        int N  , answer[] , minheight ;

        int[] tower;
        String temp[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        temp = new String[N];
        answer = new int[N];
        temp = br.readLine().split(" ");

        heapImp2 heap = new heapImp2<int[]>();
        heap.makeHeap();

        minheight = Integer.parseInt(temp[N-1]);       //높이 동기화


        for (int i=N-1;i>=0;i--){
            if(minheight<Integer.parseInt(temp[i])){        //최저층보다 높은 층이 나타난다면
                while (heap.getHeapSize()>1&&((int[])heap.preview())[1]<Integer.parseInt(temp[i])){   //방금 빌딩보다 작은 애들을
                    answer[((int[])heap.deleteRootMinHeap())[0]] = i+1 ;        //answer에 기록
                }
                tower = new int[2];
                tower[0] = i; tower[1] = Integer.parseInt(temp[i]);
                heap.insertMinHeap(tower);
                minheight = Integer.parseInt(temp[i]);
            }
            else {      //만약 높이가 작다면 (같음도 포함?)
                tower = new int[2];
                tower[0] = i; tower[1] = Integer.parseInt(temp[i]);
                heap.insertMinHeap(tower);
                minheight = Integer.parseInt(temp[i]);  //최저층 동기화
            }
        }
        for (int i=0;i<N;i++){
            bw.write(answer[i]+" ");
        }
        bw.flush();
        bw.close();
    }
}
