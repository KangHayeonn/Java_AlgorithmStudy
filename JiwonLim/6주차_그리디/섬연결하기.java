/*
MST (최소신장트리)
= spanning tree 중 사용된 간선들의 가중치 합이 최소인 트리

[ 크루스칼 Kruskal 알고리즘 ]
그리디 (greedy)이용해 그래프의 모든 정점을 최소비용으로 연결하는 최적의 해답 구하는 것
간선 선택 기반 알고리즘
	
[ 과정 ]
그래프 간선 가중치 오름차순 정렬
정렬된 간선 리스트에서 순서대로 사이클 형성하지 않는 간선 택
	→ 가장 낮은 간선 가중치 먼저 선택
	→ 사이클 형성 간선 제외 (find 함수로 검사)
해당 간선을 현재의 MST 집합에 추가 (union 함수)
pq를 다 돌면 MST완성

find 함수: 
start와 end가 연결되어있는지 확인(공통 부모 체크)
공통 부모 있으면 사이클 형성하므로 가중치 합 누적x
	없으면 둘 연결하고 가중치 합을 누적o
*/

import java.util.PriorityQueue;
import java.util.Arrays;
class Node implements Comparable<Node>{
	int start;
	int end;
	int weight;
	
	public Node(int s, int e, int w) {
		this.start = s;
		this.end = e;
		this.weight = w;
}
	
	@Override
	public int compareTo(Node n) {	// 가중치 기준 오름차순 정렬
		return this.weight - n.weight;
	}
      @Override
      public String toString(){
             return "island(" 
            + this.start + ", " + this.end + ", " + this.weight + ")"; 
      }

}

class Solution {
    static PriorityQueue<Node> pq;
    static int[] parent;

      /* 최상위 부모 찾는 함수(재귀) */
      public static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);  // x의 부모의 부모
	}
	/* 최상위 노드 합치는 함수*/
	public static void union(int a, int b) {
		parent[a]=b; // 해당 간선을 현재의 MST 집합에 추가
	}
    
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        pq = new PriorityQueue<>();	// 최소힙
        parent = new int[n];	// 사이클 여부 확인 위해
        for(int i = 0; i < n; i++) {
			parent[i] = i;      // 부모는 자기 자신으로 초기화
		}
        
        // 간선, 가중치 정보 우선순위 큐에 넣기
		for(int i=0; i<costs.length; i++) {
			int s = costs[i][0];
			int e = costs[i][1];
			int w = costs[i][2];
			
			pq.add(new Node(s, e, w)); 
		}
        
        while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int parentS = find(node.start);
			int parentE = find(node.end);
			System.out.println(parentS +"/"+ parentE);
			
                   		// 부모가 다르다면(연결되어있지 않다면)
			if(parentS != parentE) { 
				union(parentS, parentE); 	// 둘을 연결
				answer += node.weight;	// 가중치 계산
			}
			System.out.println("parent= " + Arrays.toString(parent));
		}
        
        return answer;
    }
}