import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BOJ1991 {

    public static void main(String[] args) throws Exception {

        String temp, split[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Character[]> hashTree = new HashMap<Character, Character[]>();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언

        char orinroot = 0, root, left, right;
        Character[] child;

        for (int i = 0; i < N; i++) {

            temp = br.readLine();
            split = temp.split(" ");
            root = split[0].charAt(0);
            left = split[1].charAt(0);
            right = split[2].charAt(0);
            if (i == 0)
                orinroot = root;

            child = new Character[2];
            child[0] = left;
            child[1] = right;
            hashTree.put(root, child);
            hashTree.put(left, null);
            hashTree.put(right, null);
        }
        pre(orinroot, hashTree,bw);
        bw.newLine();
        inor(orinroot, hashTree,bw);
        bw.newLine();
        post(orinroot, hashTree,bw);
        bw.flush();
        bw.close();
    }
    static void pre(char orinroot ,HashMap<Character, Character[]> hashTree ,BufferedWriter bw) throws Exception{
        if(orinroot!='.'){
            bw.write(orinroot);
            Character []child = hashTree.get(orinroot);
            if (child==null)
                return;
            pre(child[0], hashTree,bw);
            pre(child[1], hashTree,bw);
        }
    }

    static void inor(char orinroot ,HashMap<Character, Character[]> hashTree ,BufferedWriter bw) throws Exception{
        if(orinroot!='.'){
            Character []child = hashTree.get(orinroot);
            if (child==null)
                return;
            else{
                inor(child[0], hashTree,bw);
                bw.write(orinroot);
                inor(child[1], hashTree,bw);
            }
        }
    }

    static void post(char orinroot ,HashMap<Character, Character[]> hashTree ,BufferedWriter bw) throws Exception{
        if(orinroot!='.'){
            Character []child = hashTree.get(orinroot);
            if (child==null)
                return;
            else {
                post(child[0], hashTree,bw);
                post(child[1], hashTree,bw);
                bw.write(orinroot);
            }
        }
    }
}
