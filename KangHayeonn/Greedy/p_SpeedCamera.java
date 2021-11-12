// 단속카메라 (프로그래머스 LEVEL3)

/* [ 알고리즘 ]
 * 
 * 1. start, end 타입을 만들어서 ArrayList에 저장
 * 2. start를 기준으로 오름차순으로 정렬
 * 3. 현재 end와 다음 start를 비교해 현재 end가 크거나 같으면 카메라 위치 유지 (카메라 위치 : 현재 end)
 * 4. 현재 end와 다음 start를 비교했을 때 현재 end가 더 작으면 다음 end 값으로 카메라 위치 변경 후 count++ (카메라 위치 : 다음 end)
 * 5. 마지막 count에서 +1 한 뒤 출력
 *  
 */

// ver1 : 단순 반복문 실패
package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SpeedCamera {
	public static class type {
		private int start;
		private int end;
		public type(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String args[]) {
		int[][] routes = {{-20,-15},{-14,-5},{-18,-13},{-5,-3}};
		System.out.print(solution(routes));
	}
	public static int solution(int[][] routes) {
		int answer =0;
		ArrayList<type> list = new ArrayList<>();
		for(int i=0; i<routes.length; i++) {
			list.add(new type(routes[i][0], routes[i][1]));
		}
		Collections.sort(list, new Comparator<type>() {
			@Override
			public int compare(type s1, type s2) {
				if(s1.start == s2.start) return s1.end - s2.end;
				else return s1.start - s2.start; // start 값을 기준으로 오름차순
			}
		});
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).start + " : " + list.get(i).end);
		}
		
		int Camera = list.get(0).end; // Camera 위치
		
		for(int i=0; i<list.size(); i++) {
			if(Camera >= list.get(i).start) continue;
			else {
				Camera = list.get(i).end;
				answer++;
			}
		}
		return answer+1;
	}
}
