import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ16120 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<Character>();

        String temp ;
        int size;
        temp =  br.readLine();
        for (int i=0;i<temp.length();i++){

            stack.add(temp.charAt(i));
            size =stack.size();

            if(stack.size()>=4&&stack.elementAt(size-1)=='P'&&stack.elementAt(size-2)=='A'&&stack.elementAt(size-3)=='P'&&stack.elementAt(size-4)=='P'){
                for(int j=0;j<3;j++)
                    stack.pop();
            }
        }

        if (stack.size()==1&&stack.peek()=='P'){
            System.out.print("PPAP");
        }else
            System.out.print("NP");
    }
}
