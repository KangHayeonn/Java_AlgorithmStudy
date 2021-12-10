// 사회망 서비스(SNS) (백준 2533번)

/* 노드 1부터 얼리어답터일 경우와 아닐 경우로 나누어 경우의 수를 구해줌
 * - DP 이용
 */ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] dpList;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 정점의 개수
		ArrayList<Integer> arr[] = new ArrayList[N+1];
		
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}
		
		dpList = new int[N+1][2]; // 배열의 크기가 2인 이유 : 해당 노드(인덱스)에서 얼리어답터인 경우(O) || 얼리어답터가 아닌 경우(X)
		
		dp(arr, 1, 0);
		System.out.print(Math.min(dpList[1][0], dpList[1][1]));
	}
	public static void dp(ArrayList<Integer>[] tree, int child, int parent) {
		dpList[child][0] = 0; // 해당 노드에서 얼리어답터가 아니라고 가정한 경우 -> 갯수 카운트 0 해줌
		dpList[child][1] = 1; // 해당 노드에서 얼리어답터라고 가정한 경우 -> 갯수 카운트 1 해줌
		
		for(Integer next: tree[child]) {
			if(next != parent) { // 자신이 넘어온 parent값과 같으면 계속 반복됨 (순환있으면 안됨)
				dp(tree, next, child);
				dpList[child][0] += dpList[next][1];
				dpList[child][1] += Math.min(dpList[next][0], dpList[next][1]);
			}
		}
	}
}