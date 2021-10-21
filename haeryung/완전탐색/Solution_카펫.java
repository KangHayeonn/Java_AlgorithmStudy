class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int carpetSize = brown + yellow;
        int col = 0;
        
        // 가로 세로의 길이는 최소 3이상이기 때문에 초기값을 3부터 돌림 
        for(int row = 3; row <= carpetSize; row++){
            col = carpetSize / row;
            
            // 브라운의 사이즈는 가로세로의 크기에 중복되는 값 4개를 빼주고 옐로우는 브라운의 가로세로 길이의 2개를 빼서 곱하는 것이 사이즈
            if(2 * (col + row) - 4 == brown && (col - 2) * (row - 2) == yellow){
                answer[0] = col;
                answer[1] = row;
                break;
            }
        }
        
        return answer;
    }
}
