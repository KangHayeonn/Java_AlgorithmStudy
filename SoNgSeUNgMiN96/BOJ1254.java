import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ1254 {

    public static boolean checker(String ar , int index, int size){ //한글자를 기준으로 한 대칭 확인
        StringBuffer sb = new StringBuffer(ar.substring(index+1,index+size+1));
        String reversedStr = sb.reverse().toString();
        //System.out.println(ar.substring(index-size,index)+" "+reversedStr);
        if(ar.substring(index-size,index).compareTo(reversedStr)==0)
            return true;
        return false;
    }

    public static boolean checkereven(String ar , int size, int length){    //짝수기준으로 대칭을 확인
        StringBuffer sb = new StringBuffer(ar.substring(length-size,length));
        String reversedStr = sb.reverse().toString();
        //System.out.println(ar.substring(length-size*2,length-size)+" "+reversedStr);
        if(ar.substring(length-size*2,length-size).compareTo(reversedStr)==0)
            return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        String array;   //문자열 배열
        int lenth;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = br.readLine();
        lenth =  array.length();
        int size=1,i, resi=lenth-1;
        int resi2=lenth;

        for (i=lenth-2;i>=(lenth/2);i--){
            if(checker(array,i,size)) {  //만족하지 않는경우
                resi =i;
            }
            size++;
        }

        for (i=1;i<=(lenth/2);i++){
            if(checkereven(array,i,lenth)) {  //만족하지 않는경우
                resi2 =lenth - i;
            }
        }
        if((resi+1)*2-1<resi2*2){
            System.out.println((resi+1)*2-1);
        }else{
            System.out.println(resi2*2);
        }
    }
}
