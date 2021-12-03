package week9_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Ʈ�� ��ȸ
 * https://www.acmicpc.net/problem/1991
 * 
 * ���)
 * ����Ʈ�� �Է¹޾� 
 * ������ȸ(preorder), ������ȸ(inorder), ������ȸ(postorder) ������
 * 
 * ���ٹ�)
 * 2�����迭
 * ����� �̸��� A���� ���ʴ�� ���ĺ� �빮�ڷ� �Ű�����, �׻� A�� ��Ʈ ��尡 �ȴ�.
 * B�� ���ʳ�� = tree[1][0], �����ʳ�� = tree[1][1] 
 */
public class BOJ_1991_2�����迭 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static String[][] tree;
	
	/* ������ȸ : root-left-right */
	public static void preorder(int e) {
		if(tree[e][0] == null && tree[e][1] == null) {	// ����, ������ ������ 
			sb.append((char)(e+65));	// 'A'�� �ƽ�Ű�ڵ尪�� 65
		}
		else {
			sb.append((char)(e+65));
			if(tree[e][0] != null)
				preorder(tree[e][0].charAt(0) -'A'); 
			if(tree[e][1] != null)
				preorder(tree[e][1].charAt(0) -'A'); 
		}
	}
	
	/* ������ȸ : left-root-right */
	public static void inorder(int e) {
		if(tree[e][0] == null && tree[e][1] == null) {	// ����, ������ ������ 
			sb.append((char)(e+65));
		}
		else {
			if(tree[e][0] != null)
				inorder(tree[e][0].charAt(0) -'A'); 
			sb.append((char)(e+65));
			if(tree[e][1] != null)
				inorder(tree[e][1].charAt(0) -'A'); 
		}
	}
	
	/* ������ȸ : left-right-root */
	public static void postorder(int e) {
		if(tree[e][0] == null && tree[e][1] == null) {	// ����, ������ ������ 
			sb.append((char)(e+65));
		}
		else {
			if(tree[e][0] != null)
				postorder(tree[e][0].charAt(0) -'A'); 
			if(tree[e][1] != null)
				postorder(tree[e][1].charAt(0) -'A'); 
			sb.append((char)(e+65));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// ����� ����
		tree = new String[N][2];		// Ʈ�� �������� 2�����迭
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			// �� ��� , �����ڽĳ��, �������ڽų��
			char element = st.nextToken().charAt(0);
			String left = st.nextToken();
			String right = st.nextToken();
			if(!left.equals("."))	tree[element-'A'][0] = left;	// �ƽ�Ű�ڵ�: A B C -> 0 1 2
			if(!right.equals("."))	tree[element-'A'][1] = right;
		}
		
		preorder(0);
		sb.append("\n");
		inorder(0);
		sb.append("\n");
		postorder(0);
		
		System.out.println(sb);
	}

}
