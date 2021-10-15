import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;


//ver2 입니다.


public class BOJ1254 {

    public static boolean checker2(String ar , int size, int length){    //뒤에서부터 대칭이 되는지를 확인
        StringBuffer sb = new StringBuffer(ar.substring(length-size,length));
        String origin = sb.toString();
        String reversedStr = sb.reverse().toString();

        if(reversedStr.compareTo(origin)==0)
            return true;
        return false;
    }


    public static void main(String[] args) throws Exception {
        String array;   //문자열 배열
        int lenth;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = br.readLine();
        lenth =  array.length();

        int resi = 0;


        for (int i=1;i<=lenth;i++){     //뒤에서 부터 문자열이 팰린드롬인지 확인하고 가장 긴 기존펠린드롬길이를 구한다.
            if(checker2(array,i,lenth)) {  //만족하는 경우        
                resi =i;
            }
        }

        System.out.println((lenth-resi)*2+resi);    //기존팰린드롬 앞의 아닌문자를 뒤에 붙이기때문에 그 수치는 펠린드롬이아닌문자열*2 + 펠린드롬인 문자열
    }
}

//ver 1은 아래 주석처리리

/*

public static boolean checker(String ar , int index, int size){
        StringBuffer sb = new StringBuffer(ar.substring(index+1,index+size+1));
        String reversedStr = sb.reverse().toString();

        //System.out.println(ar.substring(index-size,index)+" "+reversedStr);
        if(ar.substring(index-size,index).compareTo(reversedStr)==0)
            return true;
        return false;
    }

    public static boolean checkereven(String ar , int size, int length){
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

 */
