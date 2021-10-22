import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 친구이거나 친구의 친구인 사람이 많은 사람을 구하는 문제 
 *     012
 *   0 NYY
 *   1 YNN
 *   2 YYN
 *   자기 자신을 우선 탐색할때 제외한다. 
 *   먼저 친구를 찾는다 0번쨰 인덱스를 기준으로 생각하면 1번과 2번이 친구이다.
 *   1번과 2번의 친구 중에서 친구의 친구가 자기 자신이 아니고 친구의 친구가 있다면 ans 배열의 값을 늘려준다. 
 *   visited 배열을 사용하지 않으면 이미 친구라고 계산한 값을 다시 더해주기 떄문에 사용해야한다. 
 *   최대값을 구하기 위해 배열에 넣고 정렬하여 마지막에 있는 값을 뽑아낸다. 
 * */
 
public class ex1058 {
	static int tc;
	static char[][] friend_map;
	static int[] ans;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		friend_map = new char[tc + 1][tc + 1];
		ans = new int[tc];
		visited = new boolean[tc][tc];
		
		for(int i=0; i < tc; i++) {
			String str = br.readLine();
			for(int j=0; j < tc; j++) {
				friend_map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i < tc; i++) {
			for(int j=0; j < tc; j++) {
				// 자기 자신인 것은 제외하기 
				if(i == j) {
					continue;
				}
				
				if(friend_map[i][j] == 'Y') { // 친구라면 
					if(!visited[i][j]) {
						ans[i]++; // 해당 노드의 친구의 수를 카운트하기
						visited[i][j] = true;
					}
					
					// 친구의 친구를 찾기 
					for(int k=0; k < tc; k++) {
						if(i != k && friend_map[j][k] == 'Y' && !visited[i][k]) { // 친구의 친구가 내가 아니고 친구의 친구가 있다면, 방문하지 않았다면  
							ans[i]++;
							visited[i][k] = true;
						}
					}
				}
				
			}
		}
		
		// 최대값을 가지고 오기 위해서 정렬 
		Arrays.sort(ans);

		// 최대 값을 뽑아냄 
		System.out.println(ans[tc - 1]);
	}

}
