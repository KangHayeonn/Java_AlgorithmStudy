import java.util.*;

class Solution_단속카메라 {
    static ArrayList<Camera> list = new ArrayList<>();
    
    static class Camera implements Comparable<Camera>{
        int start; int end;
        
        Camera(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        @Override
		public int compareTo(Camera c) {
			if(this.start == c.start) { // 만약 출발점이 같다면 나가는 점을 내림차순으로 정렬 
				return c.end - this.end;
			}
			return this.start - c.start; // 출발점 순으로 정렬 
		  }
    }
    public int solution(int[][] routes) {
        
        for(int i = 0; i < routes.length; i++){
            list.add(new Camera(routes[i][0], routes[i][1]));
        }
        
        Collections.sort(list);
        
        // for(Camera c : list){
        //     System.out.println(c.start + " " + c.end);
        // }
        
        int answer = 1; // 카메라는 처음에는 무조건 설치 

        int endTmp = routes[0][1]; // 나가는 지점 
        
        for(int i = 0; i < routes.length; i++){
            int start = list.get(i).start; // 진입 시점
            int end = list.get(i).end; // 나가는 시점 
            
            // 나가는 시점이 진입하는 시점보다 크다면 즉 두차량이 나가는 시점이랑 들어오는 시점이랑 포함되거나 겹치면 
            if(endTmp > end){
                endTmp = end;
            }
            // 다음 차량의 시작 시점이 이전 차량의 나가는 시점보다 크다면
            else if(start > endTmp) {
                answer++; // 카메라 대수 늘리기 
                endTmp = end; // 이전 차량의 시점을 다음 차량이 나가는 시점으로 갱신 
            }
            
        }
        
        return answer;
    }
}
