package week1_hash;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
[문제 조건]
1. 온라인 스트리밍으로 개강총회를 한다.

Problems ====>
- 누가 개강총회에 왔는지 알 수 없다.
    - 시작하기 전에, 학회원의 입장 확인 여부를 확인한다. (입장 여부는 시작한 시간 이전에 대화를 한 닉네임 체크)
    - 시작하자마자 채팅 제 시간에 입장. (ex: 08:00 등)

- 누가 개강총회 자리에 끝까지 남아있었는지 알 수 없다.
    - 개강총회를 끝내고 나서, 스트리밍을 끝낼 때까지 학회원의 퇴장 확인 여부를 확인한다. (퇴장 여부는 개강총회가 끝나고 대화를 한 닉네임 체크)
    - 끝나자마자 채팅, 스트리밍이 끝나자마자 채팅해도 제 시간에 퇴장.

Q. 이 때, 입장부터 퇴장까지 모두 확인된 학회원은 전부 몇 명인가?

(ex)
22:00 23:00 23:30

21:30 malkoring (시작 전 채팅) (malkoring은 끝나고 채팅을 치지 않음)
21:33 tolelom
21:34 minjae705
21:35 hhan14
21:36 dicohy27
21:40 906bc

23:00 906bc (끝나자 마자 채팅)
23:01 tolelom
23:10 minjae705
23:11 hhan14
23:20 dicohy27

(ex2)
06:00 12:00 18:00

06:00 shinyo17 (시작 전 채팅)
06:00 kimchist
06:00 swoon
06:00 kheee512
06:00 Green55

09:00 kimchist (얘내는 뭘까?)
11:59 shinyo17

12:00 kimchist (끝나자 마자 채팅)
17:59 swoon
17:59 swoon (한 사용자가 중복될 수도 있음)
18:00 kheee512 (얘까지 SAFE)

---> 입장,퇴장 모두 된 사람은 kimchist, khee512, swoon 총 3명

18:01 swoon (이미 끝나버림.)
18:01 Green55
18:01 kheee512
18:01 swoon
18:21 jinius36
18:40 jeongyun1206
---

문제 해결 전략

1. 시각을 정수로 변환하고, 속하는 구간 Set (peopleJoined, peopleAttended)에 이름을 넣어준다.
    - 첫 줄 입력 처리
        - readLine() 하고 space로 세 개를 구분한다.
        - 각 구분된 문자열에서 :로 두 개를 구분한 후,
            각 int time = Integer.parseInt(first * 100) + Integer.parseInt(second); 이다.
        - 위 시각 변수를 각각 beganAt, endedAt, sessionDeleted 로 명명한다.
        - sessionCreated = 0 도 하나 정의한다.

    - 각 채팅 입력 처리
        - readLine() 하고 space로 시각과 이름을 구분한다.
        - 시각은 : 로 2개를 구분한 후, int chatTime = (위 식과 동일)
        - 이 때, chatTime >= sessionCreated && chatTime <= beganAt 이면 peopleJoined Set에 넣어준다.
          추가적으로, chatTime >= endedAt && chatTime <= sessionDeleted 이면 peopleAttended Set에 넣어준다.

2. peopleJoined &&  동시에 들어있는 사람의 수를 출력한다.
    - 입장 Set을 순회하면서, 출력 Set에서의 존재 여부를 확인하면 될 것이다.

 */
public class week1_boj_19583 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer tokenizer;

    String[] times = br.readLine().split(" ");
    // 변수명을 줘서 가독성을 높이기 위해 굳이 중복되는 라인을 만들었다.
    // 이 코드는 JS가 객체 비구조화 할당이 있으니 훨씬 좋을 것 같다.
    int sessionCreated = 0;
    int beganAt =
        Integer.parseInt(times[0].split(":")[0]) * 100 + Integer.parseInt(times[0].split(":")[1]);
    int endedAt =
        Integer.parseInt(times[1].split(":")[0]) * 100 + Integer.parseInt(times[1].split(":")[1]);
    int sessionDeleted =
        Integer.parseInt(times[2].split(":")[0]) * 100 + Integer.parseInt(times[2].split(":")[1]);

    String line;
    String chatTimeStr;
    String username;
    int chatTime;

    Set<String> peopleJoined = new HashSet<>();
    Set<String> peopleAttended = new HashSet<>();

    while (br.ready()) { // null 체크로도 가능.
      line = br.readLine();
      chatTimeStr = line.split(" ")[0];
      username = line.split(" ")[1];
      chatTime =
          Integer.parseInt(chatTimeStr.split(":")[0]) * 100
              + Integer.parseInt(chatTimeStr.split(":")[1]);

      if (chatTime >= sessionCreated && chatTime <= beganAt) peopleJoined.add(username);

      if (chatTime >= endedAt && chatTime <= sessionDeleted) peopleAttended.add(username);
    }

    // 원래는 stream.toList()를 써서 비교했는데 BOJ에서 컴파일이 안 돼서 removeIf로 변경함
    peopleJoined.removeIf(u -> !peopleAttended.contains(u));
    bw.write(String.valueOf(peopleJoined.size()) + "\n");
    bw.close();
  }
}
