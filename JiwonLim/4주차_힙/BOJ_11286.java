package week4_Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 절댓값 힙 (백준 11286번)
https://www.acmicpc.net/problem/11286
*/
public class BOJ_11286 {

	public static class minHeap {
		
		private ArrayList<Integer> heap;
		
		public minHeap() {
			heap = new ArrayList<>();
			heap.add(0);	// 인덱스 1부터 시작위해
		}
		
		public void swap(int i, int j) {
			int tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
		}
		 
		/* 삽입 */
		public void insert_minHeap(int x) {
			heap.add(x);
			int pointer = heap.size() - 1;		// 마지막 노드 포인터 역할
			/*while (pointer > 1 && Math.abs(heap.get(pointer)) <= Math.abs(heap.get(pointer / 2))){
				swap(pointer, pointer/2);
				pointer = pointer / 2;		// 부모 노드 인덱스로 변경
			}*/
			
			// pointer가 root 노드로 이동할 때까지
			// 마지막노드 절댓값이 부모노드보다 작으면 swap 
			while( pointer > 1 && Math.abs(heap.get(pointer)) <= Math.abs(heap.get(pointer / 2)) ) {
				if(Math.abs(heap.get(pointer)) < Math.abs(heap.get(pointer / 2))) {
					swap(pointer, pointer/2);
					pointer = pointer / 2;
				}
				else if(Math.abs(heap.get(pointer)) == Math.abs(heap.get(pointer / 2))) {
					// 절댓값이 같으면서 마지막노드가 음수인 경우
					if(heap.get(pointer) < heap.get(pointer / 2) ) {
						swap(pointer, pointer/2);
						pointer = pointer / 2;
					}
					else {
						pointer = pointer / 2;
					}
				}
				
				//pointer = pointer / 2;
			}
			System.out.println(heap);
		}
		
		/** 삭제 **/
		// 루트 노드에 힙의 마지막 노드 넣기 --> 힙 마지막 노드 삭제 --> 힙 성질 맞도록 재구성
		public int delete_minHeap() {
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
				
				// right 노드 존재하고 right노드 절댓값이 더 작은 경우 --> right와 swap
				if ( i * 2 + 1 < heap.size() && Math.abs(heap.get(i*2)) > Math.abs(heap.get(i*2+1))) {
					if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2+1))) {
						break;
					}
					swap(i, i*2+1);
					i = i * 2 +1;
				}
				else {	// left 노드가 더 작은 경우 or left노드 절댓값 == right노드 절댓값  --> left와 swap
					if (i * 2 >=  heap.size()) break;
					if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2))) {
						break;
					}
					swap(i, i*2);
					i = i * 2;
				}
			}
			System.out.println("pop : " + heap);
			return del;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		 
		minHeap heap = new minHeap();
		
		for(int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (input != 0) {
				heap.insert_minHeap(input);
			}
			else {
				sb.append(heap.delete_minHeap() + "\n");
			}
		}
		System.out.println(sb.toString());
	}
}
