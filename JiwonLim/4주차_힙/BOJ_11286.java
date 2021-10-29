package week4_Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * ���� �� (���� 11286��)
https://www.acmicpc.net/problem/11286
*/
public class BOJ_11286 {

	public static class minHeap {
		
		private ArrayList<Integer> heap;
		
		public minHeap() {
			heap = new ArrayList<>();
			heap.add(0);	// �ε��� 1���� ��������
		}
		
		public void swap(int i, int j) {
			int tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
		}
		 
		/* ���� */
		public void insert_minHeap(int x) {
			heap.add(x);
			int pointer = heap.size() - 1;		// ������ ��� ������ ����
			/*while (pointer > 1 && Math.abs(heap.get(pointer)) <= Math.abs(heap.get(pointer / 2))){
				swap(pointer, pointer/2);
				pointer = pointer / 2;		// �θ� ��� �ε����� ����
			}*/
			
			// pointer�� root ���� �̵��� ������
			// ��������� ������ �θ��庸�� ������ swap 
			while( pointer > 1 && Math.abs(heap.get(pointer)) <= Math.abs(heap.get(pointer / 2)) ) {
				if(Math.abs(heap.get(pointer)) < Math.abs(heap.get(pointer / 2))) {
					swap(pointer, pointer/2);
					pointer = pointer / 2;
				}
				else if(Math.abs(heap.get(pointer)) == Math.abs(heap.get(pointer / 2))) {
					// ������ �����鼭 ��������尡 ������ ���
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
		
		/** ���� **/
		// ��Ʈ ��忡 ���� ������ ��� �ֱ� --> �� ������ ��� ���� --> �� ���� �µ��� �籸��
		public int delete_minHeap() {
			// �� ���� ���
			if (heap.size() - 1 < 1) {
				return 0;
			}
			
			// ������ �ּҰ� ��� = root ��Ʈ (1���� ����)
			int del = heap.get(1); 
			
			// root ��忡 ���� ������ ��� �ֱ� --> ������ ��� ����
			heap.set(1, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);
			
			for(int i = 1; i*2 < heap.size();) {
				
				// right ��� �����ϰ� right��� ������ �� ���� ��� --> right�� swap
				if ( i * 2 + 1 < heap.size() && Math.abs(heap.get(i*2)) > Math.abs(heap.get(i*2+1))) {
					if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2+1))) {
						break;
					}
					swap(i, i*2+1);
					i = i * 2 +1;
				}
				else {	// left ��尡 �� ���� ��� or left��� ���� == right��� ����  --> left�� swap
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
