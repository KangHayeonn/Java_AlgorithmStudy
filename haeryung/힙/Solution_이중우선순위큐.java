import java.util.Collections;
import java.util.PriorityQueue;

/*
 * 이중우선위큐
 * I 라면 숫자를 삽입하고 D라면 숫자 삭제
 * D의 경우 띄어쓰기를 기준으로 1이면 최대값을 -1이면 최소값을 삭제
 * 최대값 최소값을 삭제하는 연산에서 최대값 최소값이 둘 이상인 경우 하나만 삭제
 * 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우 해당 연산 무시
 * 
 * 최대힙과 최소힙을 우선순위큐로 구현. 최소힙은 오름차순으로 정렬, 최대힙은 내림차순으로 정렬
 * 명령어를 띄어쓰기 기준으로 split을 사용해서 자름
 * 조건에서 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우 무시하는 조건을 우선처리
 * 최대힙과 최소힙에서 데이터를 삭제할 때 두개의 이진트리에서 모두 삭제
 * 답을 툴력할 떄, 최대값은 최대힙에서 최소값은 최소힙의 루트노드를 반환 
 */

public class Solution_이중우선순위큐 {

	static PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 최소힙
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙
	
	public static void main(String[] args) {
		String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        solution(operations);

	}
	
	public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        for(String op : operations) {
        	String[] input = op.split(" ");
        	String command = input[0]; // 명령어 입력
        	int number = Integer.parseInt(input[1]); // 뒤에 숫자 입력 
        	
        	// 조건 : 우선순위큐의 사이즈가 0이고 D연산자가 들어오면 해당 연산 무시 
        	if(minHeap.size() == 0 && maxHeap.size() == 0 && command.equals("D")) {
        		continue;
        	}
        	
        	// 삽입 연산자가 들어오면
        	if(command.equals("I")) {
        		minHeap.add(number);
        		maxHeap.add(number);
        	}
        	// 삭제 연산자가 들어오면 
        	else if (command.equals("D")) {
        		// 최대값을 삭제 : 최대 힙에서 루트 노드 삭제 
        		if(number == 1) {
        			int max = maxHeap.poll();
        			minHeap.remove(max);
        		}
        		// 최소값을 삭제 : 최소힙에서 루트 노드 삭제 
        		else {
        			int min = minHeap.poll();
        			maxHeap.remove(min);
        		}
        	}
        	
        }
        
        if(minHeap.size() == 0 && maxHeap.size() == 0) {
        	answer[0] = 0;
        	answer[1] = 0;
        }
        else {
        	answer[0] = maxHeap.peek(); // 최대값
        	answer[1] = minHeap.peek(); // 최소값
        }
        
        return answer;
    }

}
