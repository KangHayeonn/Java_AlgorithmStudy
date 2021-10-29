// 최소힙을 구현 맨처음에는 0이 아닌 값이 들어올때마다 삽입연산을 수행한후 절대값을 기준으로 정렬해서 구현 -> 시간초과
// 삽입과 삭제를 할때 절대값을 기준으로 연산 수행 (실패..)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ex11286 {
	static int tc;
	static PriorityQueue<Integer> pq; // 최소힙

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		// 정렬 : 절대값을 기준으로 절대값이 작으면 우선순위를 먼저 가지고 절대값이 같은 경우에는 음수가 우선순위가 높도록 구현 
		pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				if(Math.abs(o1) > Math.abs(o2)) {
					return 1;
				}
				else if (Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				}
				else {
					return -1;
				}
			}	
		});
		
		// 배열에 x가 0이 아니라면 배열에 값을 추가하고 들어오는 값이 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거 
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < tc; i++) {
			int num = Integer.parseInt(br.readLine());
			
			//배열이 비어있는데 절대값이 가장 작은 값을 출력하라면 
			if(pq.isEmpty() && num == 0) {
				sb.append(0).append('\n');
			}
			else if(!pq.isEmpty() && num == 0) {
				sb.append(pq.poll()).append('\n');
			}
			else {
				pq.add(num);
			}
		}
		
		System.out.println(sb);
	}

}
