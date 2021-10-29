package week4_Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * N��° ū �� (���� 2075��)
https://www.acmicpc.net/problem/2075

 */
public class BOJ_2075 {

	public static class maxHeap {
		
		private ArrayList<Integer> heap;
		
		public maxHeap() {
			heap = new ArrayList<>();
			heap.add(1000000);	// �ε��� 1���� ��������
		}
		
		public void swap(int i, int j) {
			int tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
		}
		
		/* ���� */
		public void insert_maxHeap(int x) {
			heap.add(x);
			int pointer = heap.size() - 1;		// ������ ��� ������ ����
			
			// pointer�� root ���� �̵��� ������
			// ��������尡 �θ��庸�� ũ�� swap 
			while (pointer > 1 && heap.get(pointer) > heap.get(pointer / 2)) {
				swap(pointer, pointer/2);
				pointer = pointer / 2;		// �θ� ��� �ε����� ����
			}
		}
		
		/* ���� */
		// ��Ʈ ��忡 ���� ������ ��� �ֱ� --> �� ������ ��� ���� --> �� ���� �µ��� �籸��
		public int delete_maxHeap() {
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
				
				// right ��� �����ϰ� left��庸�� ū ���
				if ( i * 2 + 1 < heap.size() && heap.get(i*2) < heap.get(i*2+1)) {
					if (heap.get(i) > heap.get(i*2+1)) {
						break;
					}
					swap(i, i*2+1);
					i = i * 2 +1;
				}
				else {	// left ��尡 �� ū ���
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
