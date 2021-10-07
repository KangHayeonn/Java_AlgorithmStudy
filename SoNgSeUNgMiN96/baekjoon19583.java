/*
     백준 19583번 문제풀이입니다.
 */
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;


public class baekjoon19583 {

    public static void main(String[] args) throws Exception{
        int sum =0;

        Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String start , end , streamend , name , time = new String();
        start = br.readLine();
        String[] temp =new String[3];
        temp = start.split(" ");
        start = temp[0];
        end = temp[1];
        streamend = temp[2];

        HashMap<String , Integer > hashtest = new HashMap<>();

        while ((time = br.readLine())!=null){
            temp = new String[2];
            temp = time.split(" ");
            time = temp[0];
            name = temp[1];

            if (hashtest.containsKey(name)){
                if (hashtest.get(name)==1&&time.compareTo(end)>=0&&time.compareTo(streamend)<=0){   //출석 전 들어온 학생이 , 개총 이후 스트리밍 이전까지의 채팅기록이 있다면
                    hashtest.put(name,2);
                }
            }else {
                if (time.compareTo(start)<=0){  //현재 학생의 채팅시간이 출석 확인시간보다 같거나 작다면 1을 넣어준다.
                    hashtest.put(name,1);   //1은 출석 전 들어온 상태 ,
                }
            }
        }
        for (String key:hashtest.keySet()){
            if(hashtest.get(key)==2) sum++;
        }

        System.out.println(sum);

    }
}
