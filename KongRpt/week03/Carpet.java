package week03;

public class Carpet {
    public static void main(String[] args) {
        solution(10,2);
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int width, height;
        int area = brown + yellow;

        for (int i = 3; i < area; i++) {
            width = i;
            height = area / width;

            if (height > width) {
                continue;
            } else if ((width - 2) * (height - 2) == yellow) {
                answer[0] = width;
                answer[1] = height;
            }
        }
        return answer;
    }
}
