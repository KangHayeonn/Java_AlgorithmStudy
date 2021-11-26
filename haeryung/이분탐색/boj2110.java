package BOJ;

import java.io.*;
import java.util.Arrays;

public class boj2110 {
    static int N, C; // 집의 개수, 공유기의 개수
    static int[] homeList; // 집의 좌표

    public static int binarySearch() {
        // 최소거리와 최대거리를 구한 후 거리들을 이분탐색함
        int minLen = 1;
        int maxLen = homeList[N - 1] - homeList[0];
        int d = 0; // 거리
        int ans = 0;

        while(minLen <= maxLen) {
            int mid = (minLen + maxLen)/2;
            int start = homeList[0];
            int lenCnt = 1; // 거리 카운트 -> 거리의 최소는 1부터 시작

            for(int i=0; i < N; i++) {
                d = homeList[i] - start; // 집마다 거리 체크
                if(d >= mid) { // 공유기 설치 가능한지 체크
                    lenCnt++;
                    start = homeList[i]; // 설치했다면 스타트 집 갱신
                }
            }

            if(lenCnt >= C) {
                ans = mid;
                minLen = mid + 1; // 더 많은 공유기를 설치할 수 있는지 여부 확인
            }
            else {
                maxLen = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        homeList = new int[N];

        for(int i=0; i < N; i++) {
            homeList[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homeList); // 좌표값 정렬

        System.out.println(binarySearch());
    }

}
