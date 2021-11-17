
import java.util.*;

public class programmersTravelRoute {

    public static class ticketList{
        String city;
        boolean visited=false;

        public ticketList(String city){
            this.city =city;
        }
    }

    public static String[] solution(String[][] tickets) {

        String[] answer = new String[tickets.length+1];

        ArrayList<ticketList> temp;
        HashMap<String,ArrayList> route = new HashMap<String, ArrayList>();

        for (int i=0;i< tickets.length;i++){
            if (route.containsKey(tickets[i][0])){
                temp = route.get(tickets[i][0]);
                temp.add(new ticketList(tickets[i][1]));
            }else{
                temp = new ArrayList<ticketList>();     //문자열 순서대로 최소힙.
                temp.add(new ticketList(tickets[i][1]));
                route.put(tickets[i][0],temp);
            }
        }

        for (String key :route.keySet()) {
            temp = route.get(key);
            temp.sort(new Comparator<ticketList>() {
                @Override
                public int compare(ticketList o1, ticketList o2) {
                    return o1.city.compareTo(o2.city);
                }
            });
        }

        getRoute(1,route,"ICN",answer ,tickets.length+1);

        return answer;
    }
    public static boolean getRoute(int count, HashMap<String,ArrayList> route , String key ,String []answer,int full){

        answer[count-1] = key;
        ArrayList<ticketList> temp = route.get(key);

        if (count==full)
            return true;
        if (temp==null)
            return false;

        for (int i=0;i<temp.size();i++){
            if (!temp.get(i).visited){
                temp.get(i).visited = true;
                if(getRoute(count+1,route,temp.get(i).city,answer,full)){
                    return true;
                }
                temp.get(i).visited = false;
            }
        }
        return false;
    }
}
