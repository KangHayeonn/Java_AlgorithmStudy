package week2_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 순회강연
 * https://www.acmicpc.net/problem/2109
 * 
 * 접근법)
 * 그리디
 * 'd일 안'에 강연하면 강의료 p 받음 --> boolean check배열
 */
public class BOJ_2109 {

	static class Lecture implements Comparable<Lecture>{
		int pay;
		int day;
		
		Lecture(int pay, int day){
			this.pay = pay;
			this.day = day;
		}
		
		@Override
		 public String toString() {
	        return "pay : " + pay + ", day : " + day;
	    }
		
		@Override
		public int compareTo(Lecture o) {
			// pay 높은게 우선순위 높음. 같은 pay면 day 낮은게 우선순위 높음
			if(o.pay == this.pay) {
				return this.day - o.day;
			}
			return o.pay - this.pay;	
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		int maxDay = 0;
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// d일 안에 강연하면 강의료 p 받음
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			maxDay = Math.max(maxDay, d);
			Lecture lec = new Lecture(p,d);	  // Lecture객체로 만들어 우선순위 큐에 넣기
			pq.offer(lec);
		}
		boolean[] check = new boolean[maxDay+1];	
		
		int ans = 0;
		while(!pq.isEmpty()) {
			Lecture lec = pq.poll();
			// 뒤에서부터 check하는 이유는 나중에 나올 더 작은 day들의 pay를 위해. day동안이므로 최대한 day근처에서
			for(int i = lec.day; i >= 1; i--) {
				if(!check[i]) {
					ans += lec.pay;
					check[i] = true;
					break;
				}
			}
			
		}
		System.out.println(ans);
	}

}
