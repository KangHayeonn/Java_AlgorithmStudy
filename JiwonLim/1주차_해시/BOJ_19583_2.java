package week1_Hash;
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_19583_2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		String[] time = s.split(" ");
		String S = time[0];
		String E = time[1];
		String Q = time[2];

		HashMap<String, String> entrance_map = new HashMap<>();
		HashMap<String, String> exit_map = new HashMap<>();
		
		String tmp = null;
		while((tmp=br.readLine()) != null) {	
			if(S.compareTo(tmp.split(" ")[0]) >= 0) {		
				entrance_map.put(tmp.split(" ")[1], null);
			} 	
			else if(E.compareTo(tmp.split(" ")[0]) <= 0 && Q.compareTo(tmp.split(" ")[0]) >= 0) {
				exit_map.put(tmp.split(" ")[1], null);
			}
		}
		int cnt = 0;
		for(String k : entrance_map.keySet()) {
			if(exit_map.containsKey(k)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}