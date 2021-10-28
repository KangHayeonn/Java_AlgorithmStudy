/*
    BinarySearchTree.java에 구현한 라이브러리를 이용합니다.
    두 가지 파일을 참고하여주세요.
 */


public class programmersDoublepriority {

    public int[] solution(String[] operations) {

        int N= operations.length;
        Integer tempNum;
        String []temp = new String [2];
        int [] answer= new int[2];
        BinarySearchTree<Integer> Root = new BinarySearchTree<>();      //이진탐색트리 생성 (제네릭으로 Integer 타입 생성)

        for (int i=0;i<N;i++){
            temp= operations[i].split(" ");
            if (temp[0].equals("I")){
                Root.insertTree(Integer.parseInt(temp[1]));
            }else if (temp[1].equals("1")){
                Root.deleteMax();
            }else {
                Root.deleteMin();
            }
        }

        tempNum=Root.viewMax();
        if(tempNum==null){
            answer[0]=0;
        }
        else {
            answer[0] = tempNum;
        }
        if((tempNum=Root.viewMin())==null)
            answer[1]=0;
        else
            answer[1] =tempNum;

        return answer;
    }

    //["I 16","D 1"]

    public static void main(String[] args) throws Exception {
        String [] test = {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"};
        programmersDoublepriority s = new programmersDoublepriority();
        int answer[] = new int [2];
        answer = s.solution(test);
        System.out.println(answer[0]+","+answer[1]);
    }
}
