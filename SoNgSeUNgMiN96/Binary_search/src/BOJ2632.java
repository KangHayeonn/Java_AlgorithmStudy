import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BOJ2632 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int buy,n, m , sumA=0,sumB=0 , answer =0;
        String temp, split[];
        buy = Integer.parseInt(br.readLine());
        temp = br.readLine();
        split = temp.split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        int pizzaA[] = new int[n], pizzaB[] = new int[m] , countA=0,countB=0 , left=0 ,right=0 , mid;
        HashMap<Integer,Integer> partA = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> partB = new HashMap<Integer,Integer>();

        for (int i=0;i<n;i++){
            pizzaA[i] = Integer.parseInt(br.readLine());
            sumA += pizzaA[i];
        }
        for (int i=0;i<m;i++){
            pizzaB[i] = Integer.parseInt(br.readLine());
            sumB += pizzaB[i];
        }

        if(sumA<buy)
            partA.put(sumA,1);
        else if (sumA==buy)
            answer++;
        if(sumB<buy)
            partB.put(sumB,1);
        else if (sumB==buy)
            answer++;

        for (int j=0;j<n-1;j++){
            for (int k=0;k<n;k++){
                sumA = 0;
                for (int q=k;q<k+j+1;q++){
                    sumA += pizzaA[ q%n];
                    if(sumA>buy){
                        break;
                    }
                }
                if (sumA <buy){
                    if(partA.containsKey(sumA)){
                        partA.put(sumA,partA.get(sumA)+1);
                    }else{
                        partA.put(sumA,1);
                    }
                }else if(sumA==buy)
                    answer++;
            }
        }
        for (int j=0;j<m-1;j++){
            for (int k=0;k<m;k++){
                sumB = 0;
                for (int q=k;q<k+j+1;q++){
                    sumB += pizzaB[ q%m];
                    if(sumB>buy){
                        break;
                    }
                }
                if (sumB <buy){
                    if(partB.containsKey(sumB)){
                        partB.put(sumB,partB.get(sumB)+1);
                    }else{
                        partB.put(sumB,1);
                    }
                }else if (sumB==buy){
                    answer++;
                }
            }
        }

        Object[] mapkeyA = partA.keySet().toArray();
        Arrays.sort(mapkeyA);

        Object[] mapkeyB = partB.keySet().toArray();
        Arrays.sort(mapkeyB);

        for (Object i : mapkeyA){
            left = 0;
            right = partB.size()-1;
            while (left<=right){

                mid = (left+right)/2;
                if ( (Integer)mapkeyB[mid]>buy-(Integer)i){
                    right = mid -1;
                }else if ( (Integer) mapkeyB[mid]<buy-(Integer)i){
                    left = mid +1;
                }else{  //같을경우
                    answer += (partA.get(i)*partB.get(mapkeyB[mid]));
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
