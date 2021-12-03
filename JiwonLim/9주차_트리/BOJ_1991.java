package week9_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 트리 순회
 * https://www.acmicpc.net/problem/1991
 * 
 * 출력)
 * 이진트리 입력받아 
 * 전위순회(preorder), 중위순회(inorder), 후위순회(postorder) 결과출력
 * 
 * 접근법)
 * 2차원배열
 * 노드의 이름은 A부터 차례대로 알파벳 대문자로 매겨지며, 항상 A가 루트 노드가 된다.
 * B의 왼쪽노드 = tree[1][0], 오른쪽노드 = tree[1][1] 
 */
public class BOJ_1991_2차원배열 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static String[][] tree;
	
	/* 전위순회 : root-left-right */
	public static void preorder(int e) {
		if(tree[e][0] == null && tree[e][1] == null) {	// 왼쪽, 오른쪽 없으면 
			sb.append((char)(e+65));	// 'A'의 아스키코드값은 65
		}
		else {
			sb.append((char)(e+65));
			if(tree[e][0] != null)
				preorder(tree[e][0].charAt(0) -'A'); 
			if(tree[e][1] != null)
				preorder(tree[e][1].charAt(0) -'A'); 
		}
	}
	
	/* 중위순회 : left-root-right */
	public static void inorder(int e) {
		if(tree[e][0] == null && tree[e][1] == null) {	// 왼쪽, 오른쪽 없으면 
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
	
	/* 후위순회 : left-right-root */
	public static void postorder(int e) {
		if(tree[e][0] == null && tree[e][1] == null) {	// 왼쪽, 오른쪽 없으면 
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
		N = Integer.parseInt(br.readLine());	// 노드의 개수
		tree = new String[N][2];		// 트리 저장위한 2차원배열
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			// 그 노드 , 왼쪽자식노드, 오른쪽자신노드
			char element = st.nextToken().charAt(0);
			String left = st.nextToken();
			String right = st.nextToken();
			if(!left.equals("."))	tree[element-'A'][0] = left;	// 아스키코드: A B C -> 0 1 2
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
