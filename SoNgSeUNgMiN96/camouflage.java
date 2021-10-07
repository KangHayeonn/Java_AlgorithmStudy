/*
    programmers  위장 문제 풀이.
 */
import java.sql.Struct;
import java.util.HashMap;


public class camouflage {
    static int sum =0;

    public static void combi(int[] array , int pick , int pickcount , int start , int size , int tempsum){
        if (0==pick){   //뽑을게 없다면
            sum += tempsum;
            return;
        }
        for (int i=start;i<size-pick+1;i++){
            combi(array , pick-1 ,pickcount+1 ,i+1,size ,tempsum*array[i]);
        }
    }

    public static int calculateBranch(int numOfClothes, int numOfTypes , int [] array, int pickcount){
        if (numOfTypes==1){     //nOT  가 1일경우
            return  numOfClothes;
        }
        else if(pickcount==1){  //최초의 경우 nOT가 1이 아닐경우
            return numOfClothes + calculateBranch(numOfClothes, numOfTypes, array ,pickcount+1);
        }
        else if (numOfTypes==pickcount){    //딱 뽑을 수만 되었을때
            Integer temp =1;
            for ( int mul : array){
                temp *= mul;
            }
            return temp;
        }
        else {
            sum =0;
            combi(array,pickcount , 0 , 0 ,numOfTypes,1);//여기서의 pickcount가 몇개를 뽑아야하는지 , 그리고 총합을 계산해서 옴
            return  sum + calculateBranch(numOfClothes, numOfTypes, array ,pickcount+1);
        }
    }


    public static int solution(String[][] clothes) {
        int answer = 0;

        int sum =0;
        int numOfClothes = clothes.length;      //의상의 총 수
        int numOfTypes;     //의상 종류의 수
        int array[];        //각각 장착종류에대한 가짓수

        HashMap<String , Integer > hashtest = new HashMap<>();
        for (String temp[] : clothes){
            if (hashtest.containsKey(temp[1])){
                hashtest.put(temp[1],hashtest.get(temp[1])+1);
            }else {
                hashtest.put(temp[1],1);
            }
        }

        numOfTypes = hashtest.size();
        array = new int[numOfTypes];
        int i=0;

        for (Object key :hashtest.keySet()){
            array[i]= hashtest.get(key);
            i++;
        }


        answer = calculateBranch(numOfClothes,numOfTypes , array , 1);

        return answer;
    }

    public static void main(String[] args){
        String[][] clothes = new String[][]{ {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"} , {"bluesunglasses", "face"}};
        System.out.println(solution(clothes));
    }
}
