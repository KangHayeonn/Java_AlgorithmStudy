import java.io.*;

public class ex1254 {
	static String input;
	static String str;
	static String reverse_input;
	
	static String reverse(String str) {
		String s = "";
		
		for(int i = str.length()-1; i >= 0; i--) {
			s += str.charAt(i);
		}
		return s;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		reverse_input = "";
		
		int len = input.length();
		for(int i=0; i < len; i++) {
			str = input.substring(i);
			reverse_input = reverse(str);
			
			if(str.equals(reverse_input)) {
				System.out.println(len + i);
				break;
			}
		}
		
		
	}

}
