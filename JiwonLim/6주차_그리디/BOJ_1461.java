package week6_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * ������
 * https://www.acmicpc.net/problem/1461
 * 
 * ����)
 * 0�� �ڸ��� ���� ���� å N������
 * �� å�� ���� ��ġ �־���(-10000~10000)
 * ��, �ѹ��� M���� å�� �� �� ����.
 * 
 * ���ٹ�)
 * �� <-- �迭�� �ε��� ���� & ũ�� �����ϰ� ���Ŀ� �����ؾ�. 
 * */
public class BOJ_1461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Integer> book1 = new PriorityQueue<>(Collections.reverseOrder());	// å�� ���� ��ġ ���� �迭 (���� ��ġ) �ִ��� <-- ����� �ٲ㼭 �ֱ�
		PriorityQueue<Integer> book2 = new PriorityQueue<>(Collections.reverseOrder());	// å�� ���� ��ġ ���� �迭 (��� ��ġ) �ִ��� 
		int N = Integer.parseInt(st.nextToken());	// �� �Ǽ�
		int M = Integer.parseInt(st.nextToken());	// �ѹ��� �� �� �ִ� �Ǽ�
		int cnt = 0;				// å�� ��� ���ڸ��� ���� �� ��� �ּ� ���� ��
		
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
		
		if(book1.size() != 0 && book2.size() != 0) {	// 1. ���� ��� ��ġ �� �ִ� ���
			if(N <= M) {
				cnt = Math.min(book1.peek(), book2.peek()) *2 + Math.max(book1.peek(), book2.peek());
			}
			else {
				cnt += Math.max(book1.peek(), book2.peek());		// ���� �� ��ġ�� �� 
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
//				System.out.println("�ٲ� book1= " + book1);
//				System.out.println("�ٲ� book2= " + book2);
//				System.out.println("cnt " + cnt);
				// ���� ��ġ
				while(book1.size() != 0 ) {
					cnt += book1.peek()*2; 		// �պ�
					for(int i = 0; i < M; i++) {
						book1.poll();
						if( book1.size() == 0 ) break;
					}
//					System.out.println("book1 " + book1);
//					System.out.println("cnt " + cnt);
				}
				while(book2.size() != 0) {
					cnt += book2.peek()*2; 		// �պ�
					for(int i = 0; i < M; i++) {
						book2.poll();
						if( book2.size() == 0 ) break;
					}
//					System.out.println("book2 " + book2);
//					System.out.println("cnt " + cnt);
				}
			}
		}
		else if(book1.size() != 0 && book2.size() == 0) {	// 2. ��� ��ġ ������ ���
			if(N <= M) {
				cnt = book1.peek();
			}
			else {
				cnt += book1.peek();	// ���� �� ��ġ�� �� 
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
		else {												// 3. ��� ��ġ ����� ���
			if(N <= M) {
				cnt = book2.peek();
			}
			else {
				cnt += book2.peek();	// ���� �� ��ġ�� �� (�պ�x)
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
