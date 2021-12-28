package week2_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 두 용액
 * https://www.acmicpc.net/problem/2470
 * 
 * 접근법)
 * 입력값이 100,000 이하이므로
 *  "투포인터" 통해 찾기
 * 
 */
public class BOJ_2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 전체 용액의 수
		int[] arr = new int[N];
		int i = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			arr[i++] = Integer.parseInt(st.nextToken());	// 용액의 특성값을 나타내는 서로다른 N개의 정수
		}
		Arrays.sort(arr);
		
		// 투포인터 : start, end 두 개의 포인터 이용해 조작
		// start .... end  : arr의 인덱스인 start와 end의 가운데로 갈수록 절댓값이 작아지면 0과 가까움
		int start = 0;
		int end = arr.length - 1;
		int gap = Integer.MAX_VALUE;
		int a1 = 0, a2 = 0;		
		
		int sum, tmp;
		while(start < end) {
			sum = arr[start] + arr[end];
			tmp = Math.abs(sum);
			
			if(tmp < gap) {
				gap = tmp;
				a1 = arr[start];
				a2 = arr[end];				
			}
			
			// 0에 더 가까운 쪽으로 포인터값 증가시키기
			// 두 용액의 합 > 0 = 오른쪽 값이 왼쪽보다 큼 -> 합의 값 줄이기 위해 오른쪽범위 줄임
			// 두 용액의 합 <= 0 = 왼쪽 값이 오른쪽보다 큼 -> 합의 값 늘리기 위해 왼쪽범위 줄임
			if(sum > 0) end--;
			else	start++;
			
		}
		
		System.out.println(a1 + " " +  a2);

	}

}
