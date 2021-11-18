public class programmersNetwork {

    static int visitedCount=0;
    static boolean visited[];

    public static int solution(int n, int[][] computers) {
        int answer = 0 ;
        visited = new boolean[n];

        for(int i=0;i<n;i++){

            if(!visited[i]) {
                link(n, computers, i);
                answer++;
            }
            if(visitedCount==n)
                break;
            System.out.println(visitedCount);
        }


        return answer;
    }
    public static boolean link(int n,int computers[][],int start){
        visited[start] = true;
        visitedCount++;
        if (visitedCount==n)
            return true;
        for(int i=0;i<n;i++){
            if(i!=start&&computers[start][i]==1&&visited[i]==false){
                if(link(n,computers,i))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int array[][] = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}	;
        System.out.print(solution(3,array));
    }
}
