import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
패션왕 신해빈

해빈이는 패션에 매우 민감해서 한번 입었던 옷들의 조합을 절대 다시 입지 않는다.

예를 들어 오늘 해빈이가 안경, 코트, 상의, 신발을 입었다면,
다음날은 바지를 추가로 입거나 안경대신 렌즈를 착용하거나 해야한다.

해빈이가 가진 의상들이 주어졌을때 과연 해빈이는 알몸이 아닌 상태로 며칠동안 밖에 돌아다닐 수 있을까?

=> 알몸이 아니기만 하면 되므로, 경우의 수를 구하면 끝나는 문제이다.
=> 즉 <String, Integer> map을 만들고, 각 입력의 key에 대해 value++ 해놓은 후 iterator로 돌면서 (처음이면 value=1)
   답을 내면 된다. 답= [종류A의 모든 case] * [B...] * [C...] - 1 이다.
   =>
    sum = 1;
    for ( key of map ) sum *= (map[key]+1)
    return sum-1;

---

각 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)이 주어진다.
다음 n개에는 해빈이가 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다.
같은 종류의 의상은 하나만 입을 수 있다.

[input]
2 <--- total # of cases
3 <--- input length of case #1
hat headgear        <--- value, key
sunglasses eyewear  <--- value, key
turban headgear     <--- value, key (이 경우 답은 [headgear=2] * [eyewear=1] 이므로, 3*2-1=5 이다.)
3 <--- input length of case #2
mask face
sunglasses face
makeup face

[output]
5
3

 */
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer tokenizer;

    String key;
    Integer prevValue;
    Map<String, Integer> map;

    int numberOfCases = Integer.parseInt(br.readLine());
    for (int i = 0; i < numberOfCases; i++) {
      map = new HashMap<>();
      int numberOfCategories = Integer.parseInt(br.readLine());
      for (int j = 0; j < numberOfCategories; j++) {
        tokenizer = new StringTokenizer(br.readLine()); // 1라인 단위로 읽기 때문에 굿.
        tokenizer.nextToken(); // 그냥 제품명
        key = tokenizer.nextToken(); // 카테고리
        prevValue = map.get(key);
        map.put(key, prevValue == null ? 1 : prevValue + 1);
      }
      int sum = map.values().stream().reduce(1, (acc, elem) -> acc * (elem + 1));
      bw.write(String.valueOf(sum - 1) + "\n");
    }
    bw.close();
  }
}
