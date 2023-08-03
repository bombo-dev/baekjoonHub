import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;

        Map<Integer, Integer> map = new HashMap<>();

        public void solution() throws IOException {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 배열안에 포함되어 있는 원소의 개수가 몇 개 인지 찾아야 한다.
            // 숫자 카드의 개수가 50만개고, 찾고자 하는 숫자의 개수도 50만개이다.

            // 이분탐색을 사용하면 아래와 같다.
            // 따라서, 일반적으로 전부 찾을려고 하면 50만 * 50만으로 시간이 초과한다. 따라서 NlogN 이하의 알고리즘을 사용해야한다. (1000만)
            
            // 맵에 값을 저장한다. 동일한 값이 있으면 해당 값에 접근해서 value를 높여준다. 이 과정에서 N만큼의 시간이 소요된다.
            for (int i = 0; i < N; i++) {
                int key = Integer.parseInt(st.nextToken());
                map.put(key, map.getOrDefault(key, 0) + 1);
            }


            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                int target = Integer.parseInt(st.nextToken());
                // 찾고자 하는 값이 존재하는지를 찾는다. 이 과정에서 O(1)이 수행된다.
                if (map.containsKey(target)) {
                    sb.append(map.get(target)).append(" ");
                } else {
                    sb.append(0).append(" ");
                }
            }
            // 맵에 값을 초기화해주는 과정 N, 값의 존재 유무를 판단하고 추가해주는 과정 M
            // 총 N + M 이 발생하게 된다. 이분 탐색을 사용했을 때의 NlogN + MlogN 보다 더 빠르다.
            System.out.print(sb);
        }
    }
}
