import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 스택안의 배열은 탑의 높이와 인덱스 번호로 구성
// 케이스 1 : 이전 탑의 길이 < 현재 탑의 길이 -> 수신할 수 없으니까 pop
// 케이스 2 : 이전 탑의 길이 >= 현재 탑의 길이 -> 수신할 수 있으니까 정답에 넣음 

public class ex2493 {
	static int tc; // 테스트 케이스 
	static Stack<int[]> stack;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine()); 
		stack = new Stack<>();
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 높이 입력받기 
		for(int index = 1; index <= tc; index++) {
			int height = Integer.parseInt(st.nextToken()); // 현재 탑의 높이 
			
			// 만약 수신할 탑이 없다면 
			if(stack.isEmpty()) {
				sb.append("0").append(" ");
			}

			// 스택이 비어있지 않다면 비교 
			while(!stack.isEmpty()) {
				if(height > stack.peek()[1]) { // 케이스 1: 현재 탑의 높이가 이전 탑의 높이보다 긴 경우 
					// 수신할 수 없기 때문에
					stack.pop();
				}
				else { // 케이스 2: 현재 탑의 높이가 이전 탑의 높이보다 짧은 경우 
					// 수신할 수 있기 때문에 인덱스 넣어주기 
					sb.append(stack.peek()[0]).append(" ");
					break;
				}
				
				// 스택에서 높이를 뺐는데 수신할 곳이 없게 된다면, 즉  pop을 하고 스택이 비게된다면 -> 예제 (2 9) 
				if(stack.isEmpty()) {
					sb.append("0").append(" ");
				}
			}
			
			// 스택에 인덱스 값과 높이의 값을 넣음 
			stack.push(new int[] {index, height}); // (1 6) (2 9) (3 5) (4 7) (5 4)
		}
				
		System.out.println(sb);
	}

}
