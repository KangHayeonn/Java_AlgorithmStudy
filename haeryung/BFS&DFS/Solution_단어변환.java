import java.util.*;

class Solution_단어변환 {
    static Queue<Node> q = new LinkedList<>();
    static boolean[] visited; // 방문배열 
    
    public class Node{
        String word; // 단어
        int cnt; // 최소 변환
        
        Node(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public boolean changeFlag(String first, String end){
        int diffString = 0;
        
        for(int i = 0; i < end.length(); i++){
            if(first.charAt(i) != end.charAt(i)){ // 한 글자씩 비교하는데 같지 않다면
                diffString++;
            }
        }
        
        if(diffString == 1){ // 한글자만 다르다면
            return true;
        }
        else return false;
    }
  
    public int BFS(String begin, String target, String[] words){
        q.add(new Node(begin, 0)); // 첫번째 시작 단어, 카운트는 0부터 시작         
       
        while(!q.isEmpty()){
            Node node = q.poll();
            
            if(node.word.equals(target)){ // 타켓 단어와 같아진다면
                return node.cnt; // 개수 리턴 
            }
            
            for(int i = 0; i < words.length; i++){
                // 방문하지 않았고 한글자만 다르다면
                if(!visited[i] && changeFlag(node.word, words[i])){
                    q.add(new Node(words[i], node.cnt+1)); 
                    visited[i] = true;
                }
            }
        }
        
        return 0;
    }
  
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        return BFS(begin, target, words);
        
    }
}
