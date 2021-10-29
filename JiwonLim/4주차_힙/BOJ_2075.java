package week4_Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * N번째 큰 수 (백준 2075번)
https://www.acmicpc.net/problem/2075

 */
public class BOJ_2075 {

	public static class maxHeap {
		
		private ArrayList<Integer> heap;
		
		public maxHeap() {
			heap = new ArrayList<>();
			heap.add(1000000);	// 인덱스 1부터 시작위해
		}
		
		public void swap(int i, int j) {
			int tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
		}
		
		/* 삽입 */
		public void insert_maxHeap(int x) {
			heap.add(x);
			int pointer = heap.size() - 1;		// 마지막 노드 포인터 역할
			
			// pointer가 root 노드로 이동할 때까지
			// 마지막노드가 부모노드보다 크면 swap 
			while (pointer > 1 && heap.get(pointer) > heap.get(pointer / 2)) {
				swap(pointer, pointer/2);
				pointer = pointer / 2;		// 부모 노드 인덱스로 변경
			}
		}
		
		/* 삭제 */
		// 루트 노드에 힙의 마지막 노드 넣기 --> 힙 마지막 노드 삭제 --> 힙 성질 맞도록 재구성
		public int delete_maxHeap() {
			// 빈 힙일 경우
			if (heap.size() - 1 < 1) {
				return 0;
			}
			
			// 삭제할 최소값 노드 = root 노트 (1부터 시작)
			int del = heap.get(1);
			
			// root 노드에 힙의 마지막 노드 넣기 --> 마지막 노드 제거
			heap.set(1, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);
			
			for(int i = 1; i*2 < heap.size();) {
				
				// right 노드 존재하고 left노드보다 큰 경우
				if ( i * 2 + 1 < heap.size() && heap.get(i*2) < heap.get(i*2+1)) {
					if (heap.get(i) > heap.get(i*2+1)) {
						break;
					}
					swap(i, i*2+1);
					i = i * 2 +1;
				}
				else {	// left 노드가 더 큰 경우
					if (heap.get(i) > heap.get(i*2)) {
						break;
					}
					swap(i, i*2);
					i = i * 2;
				}
			}
			
			return del;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		 
		maxHeap heap = new maxHeap();
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			for(int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				heap.insert_maxHeap(input);	
			}
		}
		
		int rslt = 0;
		for(int i = 0; i < N; i++) {
			rslt = heap.delete_maxHeap();
		}
		
		System.out.println(rslt);
	}

}
