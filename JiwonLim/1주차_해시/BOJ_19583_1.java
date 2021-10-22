//https://www.acmicpc.net/problem/19583
//싸이버개강총회 
package week1_Hash;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19583_1 {

	public static void main(String[] args) throws IOException {
		HashMap<String, String> entrance_map = new HashMap<>();
		HashMap<String, String> exit_map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		String S = st.nextToken();
		String E = st.nextToken();
		String Q = st.nextToken();
		
		int start = Integer.parseInt(S.split(":")[0] + S.split(":")[1]);
		int end = Integer.parseInt(E.split(":")[0] + E.split(":")[1]);
		int stream_end = Integer.parseInt(Q.split(":")[0] + Q.split(":")[1]);
		
		String s = null;
		while((s=br.readLine()) != null) {	
			String T = s.split(" ")[0];
			int check_t = Integer.parseInt(T.split(":")[0] + T.split(":")[1]);
			
			if(exit_map.containsKey(s.split(" ")[1])) continue;
			if(check_t <= start) {	
				entrance_map.put(s.split(" ")[1], null);
			}
			
			if(entrance_map.containsKey(s.split(" ")[1]) && (end <= check_t && check_t <= stream_end)) {	
				exit_map.put(s.split(" ")[1], null);
			}
		}
		System.out.println(exit_map.size());
	}
}
