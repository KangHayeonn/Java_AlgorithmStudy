package week6_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 도서관
 * https://www.acmicpc.net/problem/1461
 * 
 * 조건)
 * 0번 자리에 마구 놓인 책 N권있음
 * 각 책의 원래 위치 주어짐(-10000~10000)
 * 단, 한번에 M권의 책을 들 수 있음.
 * 
 * 접근법)
 * 힙 <-- 배열은 인덱스 지정 & 크기 고정하고 이후에 정렬해야. 
 * */
public class BOJ_1461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Integer> book1 = new PriorityQueue<>(Collections.reverseOrder());	// 책의 원래 위치 담을 배열 (음수 위치) 최대힙 <-- 양수로 바꿔서 넣기
		PriorityQueue<Integer> book2 = new PriorityQueue<>(Collections.reverseOrder());	// 책의 원래 위치 담을 배열 (양수 위치) 최대힙 
		int N = Integer.parseInt(st.nextToken());	// 총 권수
		int M = Integer.parseInt(st.nextToken());	// 한번에 들 수 있는 권수
		int cnt = 0;				// 책을 모두 제자리에 놔둘 때 드는 최소 걸음 수
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if(input < 0) {
				book1.add(-input);
			}
			else {
				book2.add(input);
			}
		}
//		System.out.println("book1= " + book1);	
//		System.out.println("book2= " + book2 + "\n");	
		
		if(book1.size() != 0 && book2.size() != 0) {	// 1. 음수 양수 위치 다 있는 경우
			if(N <= M) {
				cnt = Math.min(book1.peek(), book2.peek()) *2 + Math.max(book1.peek(), book2.peek());
			}
			else {
				cnt += Math.max(book1.peek(), book2.peek());		// 가장 먼 위치는 편도 
				if(cnt == book1.peek()) {
					for(int i = 0; i < M; i++) {
						book1.poll();
					}
				}
				else {
					for(int i = 0; i < M; i++) {
						book2.poll();
					}
				}
//				System.out.println("바뀐 book1= " + book1);
//				System.out.println("바뀐 book2= " + book2);
//				System.out.println("cnt " + cnt);
				// 음수 위치
				while(book1.size() != 0 ) {
					cnt += book1.peek()*2; 		// 왕복
					for(int i = 0; i < M; i++) {
						book1.poll();
						if( book1.size() == 0 ) break;
					}
//					System.out.println("book1 " + book1);
//					System.out.println("cnt " + cnt);
				}
				while(book2.size() != 0) {
					cnt += book2.peek()*2; 		// 왕복
					for(int i = 0; i < M; i++) {
						book2.poll();
						if( book2.size() == 0 ) break;
					}
//					System.out.println("book2 " + book2);
//					System.out.println("cnt " + cnt);
				}
			}
		}
		else if(book1.size() != 0 && book2.size() == 0) {	// 2. 모든 위치 음수인 경우
			if(N <= M) {
				cnt = book1.peek();
			}
			else {
				cnt += book1.peek();	// 가장 먼 위치는 편도 
				for(int i = 0; i < M; i++) {
					book1.poll();
				}
				while(book1.size() != 0 ) {
					cnt += book1.peek()*2;
					for(int i = 0; i < M; i++) {
						book1.poll();
						if( book1.size() == 0 ) break;
					}
				}
			}
		}
		else {												// 3. 모든 위치 양수인 경우
			if(N <= M) {
				cnt = book2.peek();
			}
			else {
				cnt += book2.peek();	// 가장 먼 위치는 편도 (왕보x)
				for(int i = 0; i < M; i++) {
					book2.poll();
				}
//				System.out.println("book2 " + book2);
//				System.out.println("cnt " + cnt);
				while(book2.size() != 0) {
					cnt += book2.peek()*2;
					for(int i = 0; i < M; i++) {
						book2.poll();
						if( book2.size() == 0 ) break;
					}
//					System.out.println("book2 " + book2);
//					System.out.println("cnt " + cnt);
				}
			}
		}
		System.out.println(cnt);
	}

}
