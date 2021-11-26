// 피자판매 (백준 2632번)

/* [ 알고리즘 ]
 * 
 * - 부분 수열의 합을 이용해 A피자와 B피자 조각별로 나올 수 있는 합을 배열에 담아줌 (SumA, SumB)
 * - SumA에서 하나 SumB에서 하나 원소(a,b)를 골라서 더해준 뒤 내가 사고자 하는 피자 크기와 동일한지 비교
 * - 위의 경우 해당 케이스의 경우의 수를 구해줌 (a가 A에 몇개나 동일하게 있는지, b가 B에 몇개나 동일하게 있는지)
 * 
 * # 주의점
 * 1. 연속된 조각만 판매 (부분 수열의 합을 구할 때 고려)
 * 2. 피자이므로 첫번째 인덱스와 마지막 인덱스가 연결되어야 함
 *  
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2632 {
	static ArrayList<Integer> SumA = new ArrayList<>();
	static ArrayList<Integer> SumB = new ArrayList<>();
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int buySize = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // A피자 조각의 개수
		int n = Integer.parseInt(st.nextToken()); // B피자 조각의 개수
		
		int[] A = new int[m];
		int[] B = new int[n];
		
		for(int i=0; i<m; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		for(int i=0; i<n; i++) {
			B[i] = Integer.parseInt(br.readLine());
		}
		
		// 부분 수열의 합 구하기 (A)
		for(int i=0; i<m; i++) {
			boolean[] check = new boolean[m];
			check[i] = true;
			if(i==0) funcSum(SumA, A, check, i+1, A[i], buySize, m);
			else funcSum(SumA, A, check, i+1, A[i], buySize, m-1);
		}
		
		// 부분 수열의 합 구하기 (B)
		for(int i=0; i<n; i++) {
			boolean[] check = new boolean[n];
			check[i] = true;
			if(i==0) funcSum(SumB, B, check, i+1, B[i], buySize, n);
			else funcSum(SumB, B, check, i+1, B[i], buySize, n-1);
		}
		
		SumA.add(0); // 안뽑는 경우도 포함해야함
		SumB.add(0);
		
		Collections.sort(SumA);
		Collections.sort(SumB);
		
		int answer = 0; // 최종 답 (피자를 판매하는 방법의 가지 수)
		int aIdx = 0;
		int bIdx = SumB.size()-1;
		
		while(aIdx < SumA.size() && bIdx >= 0) {
			int valueA = SumA.get(aIdx);
			int valueB = SumB.get(bIdx);
			int sum = valueA + valueB;
            
			if(sum == buySize) {
				int countA = 0, countB = 0;
				while(aIdx < SumA.size() && SumA.get(aIdx)==valueA) { // 이 부분을 분리하면 시간초과
					countA++; 
					aIdx++;
				}
				while(bIdx >=0 && SumB.get(bIdx)==valueB) {
					countB++; 
					bIdx--;
				}
				answer += countA * countB;
			}
			if(sum < buySize) aIdx++; // 이부분 때문에 사이즈를 한쪽은 0부터 한쪽은 length-1부터 함
			else if(sum > buySize) bIdx--; 
		}
		
		System.out.print(answer);
	}
	
	public static void funcSum(ArrayList<Integer> arr, int[] List, boolean[] check, int idx, int sum, int target, int count) {
		if(count==0) return;
		if(idx==List.length) idx = 0;
		
		arr.add(sum);
		
        if(check[idx]==false ) {
            sum += List[idx];
            check[idx] = true;
            if(sum <= target) funcSum(arr, List, check, idx+1, sum, target, count-1);
        }

	}
}