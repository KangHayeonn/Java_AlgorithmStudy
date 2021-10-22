import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 여러가지 종류의 재료의 조합을 구한다. ( 인덱스를 기준으로 조합을 구함 -> (0) (1) (2) (3) (0 1) (0 2) (0 3) 이런식으로
// 구한 조합의 인덱스를 가지고 쓴맛과 신맛의 배열에 입력값을 추가한 다음 합을 구함 (신맛은 곱하고 쓴맛은 더하기)
// 구해진 값을 토대로 쓴맛과 신맛의 차이를 구한 후 가장 최소 값을 출력

public class ex2961 {
	static int tc;
	static int[] idx;
	static int[] s; // 신맛 -> 곱하기
	static int[] b; // 쓴맛 -> 더하기 
	static boolean[] visited;
	static ArrayList<Integer> sourSum = new ArrayList<>();
	static ArrayList<Integer> bitterSum = new ArrayList<>();
	static int res = Integer.MAX_VALUE;
	
	// 재료 종류의 조합을 구하기 
	public static void comb(int[] Index, boolean[] visited, int start, int r, int[] s, int[] b) {
		if(r == 0) {
			getSum(Index, visited, s, b);
			return;
		}
		else {
			for(int i = start; i < tc; i++) {
				visited[i] = true;
				comb(Index, visited, i + 1, r - 1, s, b);
				visited[i] = false;
			}
		}
	}
	
	public static void getSum(int[] Index, boolean[] visited, int[] s, int[] b) {
		int sSum = 1;
		int bSum = 0;
		
		for(int i=0; i < Index.length; i++) {
			if(visited[i] == true) {
				sSum *= s[i];
				bSum += b[i];
			}
		}
		sourSum.add(sSum);
		bitterSum.add(bSum);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		s = new int[tc];
		b = new int[tc];
		idx = new int[tc];
		visited = new boolean[tc];
		
		for(int i=0; i < tc; i++) {
			idx[i] = i;
		}
		
		for(int i = 0; i < tc; i++) {
			String[] input = br.readLine().split(" ");
			s[i] = Integer.parseInt(input[0]);
			b[i] = Integer.parseInt(input[1]);
		}

		for(int i = 1; i <= tc; i++) {
			comb(idx, visited, 0, i, s, b);
		}
		
		int diff = 0;
		
		for(int i=0; i < sourSum.size(); i++) {
			diff = Math.abs(sourSum.get(i) - bitterSum.get(i));
			
			if(res > diff) {
				res = diff;
			}
		}
		
		System.out.println(res);
	}

}
