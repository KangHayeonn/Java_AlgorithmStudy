import java.io.*;
import java.util.Arrays;

public class ex2212 {
	static int N, K; // 센서의 개수, 집중국의 개수 
	static int[] sensor; // 센서 
	static int[] sensorDiff; // 각각의 센서들의 거리 차이 
	static int ans;
	
	// 카운팅 정렬 (계수 정렬) : 시간복잡도 O(N)
	static int[] counting;
	static int[] res;
		
	static void countingSort(int[] array) {
		int max = ;
		
		for(int i=0; i < array.length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
		}
		
		int[] counting = new int[max + 1];
		
		// 저장된 수들의 값을 탐색하며, 각 수와 대응되는 counting 배열의 인덱스에 해당 수의 개수를 +1 함
		for(int i=0; i < array.length; i++) {
			counting[array[i]]++;
		}
		
		int idx = 0;
		for(int i=0; i < counting.length; i++) {
			if(counting[i] != 0) {
				for(int j=0; j < counting[i]; j++) {
					array[idx++] = i;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		sensor = new int[N];
		sensorDiff = new int[N-1];

		String[] input = br.readLine().split(" ");
		for(int i=0; i < N; i++) {
			sensor[i] = Integer.parseInt(input[i]);
		}
		
		// 센서의 개수와 집중국의 수가 크거나 같으면 거리는 무조건 0
		if(N <= K) {
			System.out.println(0);
			return;
		}
		
		else {
			Arrays.sort(sensor);
//			countingSort(sensor);
			
			for(int i=0; i < N-1; i++) {
				sensorDiff[i] = sensor[i+1]- sensor[i];
			}
			
			Arrays.sort(sensorDiff);
//			countingSort(sensorDiff);
			
			for(int i=0; i < N - K; i++) {
				ans += sensorDiff[i];
			}
			
			System.out.println(ans);
		}
		
	}

}
