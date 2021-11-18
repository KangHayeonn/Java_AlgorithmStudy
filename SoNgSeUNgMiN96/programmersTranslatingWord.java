public class programmersTranslatingWord {

    static int min = Integer.MAX_VALUE;

    public static int solution(String begin, String target, String[] words) {

        String pick[] = new String[words.length];     //어디서부터 무슨 순서로 뽑을지를결정
        boolean visited[] = new boolean[words.length];     //방문여부를 결정한다.
        boolean go = false;
        for (int i=0;i< words.length;i++){
            if (words[i].equals(target))
                go =true;
        }
        if(!go)
            return 0;
        permutation(words,0, words.length,begin,target,visited);
        if(min==Integer.MAX_VALUE)
            return 0;

        return min;
    }

    public static void permutation(String []words,int count,int max, String begin,String target,boolean visited[]){

        String beginTemp = begin;
        if(begin.equals(target)){
            if (min>count)
                min = count;
            return;
        }
        if (count==max)
            return;

        for (int i=0;i<max;i++){
            if(visited[i]==false){
                if (canTrans(begin,words[i])){
                    visited[i] = true;
                    permutation(words,count+1,max,words[i],target,visited);
                    visited[i] = false;
                    begin =beginTemp;
                }
            }
        }
    }
    public static boolean canTrans(String origin, String trans){
        int size = origin.length();
        int count =0;

        for (int i=0;i<size;i++){
            if(origin.charAt(i)==trans.charAt(i))
                count++;
        }
        if (count==size-1)
            return true;
        return false;
    }
}
