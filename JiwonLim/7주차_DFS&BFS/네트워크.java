/*
computers n*n 2차원 배열
자기자신은 네트워크 연결 (1)

접근법)
DFS로 노드 탐색 끝나면 그게 하나의 네트워크

*/

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;// 연결된 네트워크의 개수
        boolean[] visited = new boolean[computers.length];
        
        for(int i = 0; i < computers.length; i++) {
            if(visited[i] == false){
                dfs(i, visited, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int x, boolean[] visited, int[][] computers){
        visited[x] = true;	// 방문함
		
		for(int i = 0; i < computers.length; i++) {
            // 정점 i에 연결되어 있고, 아직 방문하지 않은 정점이면
			if(computers[x][i] == 1 && visited[i] == false)
				dfs(i,visited, computers);
		}
    }
}