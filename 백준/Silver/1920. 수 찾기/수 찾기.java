import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        private Set<Integer> set = new HashSet<>();

        public void solution() throws IOException {
            // 생성 할 배열의 수를 입력 받는다.
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 배열을 초기화 한다.
            for (int i = 0; i < N; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            // 출력의 성능 향상을 위해 StringBuilder를 사용한다.
            // 여기서는 이분 탐색으로 값이 있는지의 여부를 판단했다. 즉 MlogN 만큼의 시간 복잡도가 소요되어 총 걸리는 시간은 NlogN + MlogN 이다.
            // 둘 다 10만이 최고 값이기 때문에, 200만 + 200만 = 400만으로 볼 수 있다.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                if (set.contains(Integer.parseInt(st.nextToken()))) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }

            System.out.print(sb);
            // 하지만 이 문제는 존재하는지에 대한 여부를 따지는 문제이기 때문에 Set을 사용하는 것이 더 효율적이다. set의 탐색속도는 1이다.
            // 정렬도 수행 할 필요가 없기 때문에 N + M 만큼의 시간 복잡도인 20만으로 충분하다.

        }
    }
}
