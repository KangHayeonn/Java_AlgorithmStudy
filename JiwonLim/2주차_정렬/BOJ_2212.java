package week2_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 센서
 * https://www.acmicpc.net/problem/2212
 * 
 * 출력)
 * 최대 K개의 집중국의 수신가능 영역 길이 합의 최솟값
 * 
 * 접근법)
 * 1. 입력받은 센서 좌표 arr 낮은숫자순으로 정렬
 * 2. 각 센서 사이의 거리를 배열로 distance 만든 후 정렬
 * 3. distance 배열의 값들을 모두 rslt에 더하기
 * 4. 집중국의 개수 K-1 만큼 distance배열에서 값이 큰 순서대로 rslt에서 빼기
 * 
 * 런타임에러 해결)
 * N <= K 인 경우
 * 
 */
public class BOJ_2212 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 센서의 개수	
		int K = Integer.parseInt(br.readLine());	// 집중국의 개수
		int[] arr = new int[N];
		int[] distance = new int[N-1];
		int rslt = 0;
		
		if(N <= K) {
			System.out.println(0);
			return;
		}
		
		int i = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			arr[i++] = Integer.parseInt(st.nextToken());	// N개의 센서의 좌표
		}
		Arrays.sort(arr);
		
		for(i = 0; i < N-1; i++) {
			distance[i] = arr[i+1] - arr[i];
			rslt += distance[i];
		}
		Arrays.sort(distance);
		
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(distance));
		
		// N > K인 경우
		for(i = 0; i < K-1; i++) {
			rslt -= distance[N-2-i];
		}
		
		System.out.println(rslt);
	}

}
