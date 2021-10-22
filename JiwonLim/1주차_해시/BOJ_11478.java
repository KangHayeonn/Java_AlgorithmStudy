//https://www.acmicpc.net/problem/11478
//���� �ٸ� �κ� ���ڿ��� ����

package week1_Hash;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_11478 {

	public static void main(String[] args) throws IOException {
		HashMap<String, String> map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int cnt = 1;
		
		//���� �ٸ� �κ� ���ڿ� --> HashMap�� Ű��
		for(int j = 0; j < s.length(); j++) {
			if(j == s.length()-1) {
				map.put(s.substring(0), null);
				break;
			}
			for(int i = 0; i < s.length(); i++) {
				if(i + cnt > s.length()) break;
				String k = s.substring(i, i+cnt);
				map.put(k, null);
			}
			cnt++;
		}	
		System.out.println(map.size());		
	}
}
