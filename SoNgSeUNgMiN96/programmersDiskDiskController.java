import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


class heapImp<E>{
    public ArrayList heap;

    public heapImp(){
        heap = new ArrayList<E>();
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



public class programmersDiskDiskController {


    public static int solution(int[][] jobs) {
        int answer = 0, sum=0 , time, length = jobs.length;
        int []temp = {0,0};
        heapImp heap = new heapImp<int[]>();
        heap.makeHeap();

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int [] a, int []b) {
                return a[0] - b[0];
            }
        });

        time = jobs[0][0];
        heap.insertMinHeap(jobs[0]);

        int i=1, count=0 , code=1;

        while (count<length){
            code = 1;
            while (i<length&&time>=jobs[i][0]){       //일단 다 넣기
                heap.insertMinHeap(jobs[i]);
                i++;
            }
            if (heap.getHeapSize()>1&&((int[])heap.preview())[0]<=time){        //넣어진 요소가 있고,
                code =0;
                temp = (int[])heap.deleteRootMinHeap();     //하나 뽑기
                count++;
                time += temp[1] ;   //작업을 처리한다.
                sum += (time - temp[0]);    //방금 작업의 대기값을 넣어준다.
            }
            if(code==1&&i<length){    //빼낸 요소가 없다면,
                time = jobs[i][0];      //뒷 첫 작업까지 시간동기화
            }
        }
        answer = sum/length;

        return answer;
    }

    public static void main(String[] args) throws Exception {
        int dist[][] = 	{{0, 3}, {2, 6}, {1, 9}};
        System.out.println(solution(dist));

    }
}
