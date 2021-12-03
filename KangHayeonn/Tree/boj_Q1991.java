// 트리 순회 (백준 1991번)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1991 {
	static class Node {
		String c;
		Node left, right;
		
		public Node(String c) {
			this.c = c;
		}
	}
	public static class Tree{
		Node root;
		
		public void create(String rootC, String leftC, String rightC) {
			if(root ==null) {
				root= new Node(rootC);
				
				if(!leftC.equals(".")) root.left = new Node(leftC);
				if(!rightC.equals(".")) root.right = new Node(rightC);
			} else {
				search(root, rootC, leftC, rightC);
			}
		}
		
		public void search(Node root, String rootC, String leftC, String rightC) {
			if(root==null) {
				return;
			}else if(root.c.equals(rootC)) {
				if(!leftC.equals(".")) root.left = new Node(leftC);
				if(!rightC.equals(".")) root.right = new Node(rightC);
			}else {
				search(root.left, rootC, leftC, rightC);
				search(root.right, rootC, leftC, rightC);
			}
		}
		
		public static void preOrder(Node node) {
			if(node ==null) return;
			
			System.out.print(node.c);
			preOrder(node.left);
			preOrder(node.right);
		}
		public static void inOrder(Node node) {
			if(node ==null) return;
			
			inOrder(node.left);
			System.out.print(node.c);
			inOrder(node.right);
		}
		public static void postOrder(Node node) {
			if(node ==null) return;
			
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.c);
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		//Node root = new Node('A');
		Tree tree = new Tree();
		StringTokenizer st;
		
		for(int i=0; i<count; i++) {
			st = new StringTokenizer(br.readLine());
			tree.create(st.nextToken(), st.nextToken(), st.nextToken());
		}
		
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postOrder(tree.root);
	}
}
