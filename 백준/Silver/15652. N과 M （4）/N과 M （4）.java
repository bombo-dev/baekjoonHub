import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {

        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        boolean[] visited;

        public void solution() throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // N과 M에 대해서 backTracking을 수행한다.
            // 조건이 중복이 발생하는 M개를 고를 수열이다. 순서가 존재 하지 않는다. 하지만 이전에 선택한 걸 다시 선택 할 수 있다.

            // 해당 문제는 visited를 사용하지 않는다. 방문했던 것에 대해서도 다시 방문을 해야되기 때문이다.
            // 각 depth의 값을 가지고 있는 부분을 이용하면 remove를 사용하지 않고도 값을 유지할 수 있다.
            int[] result = new int[M];
            StringBuilder sb = new StringBuilder(); // 외부에서 생성하고 하나의 메모리만 사용한다.
            backTracking(0, result, sb, 1); // depth는 현재 추가 된 값을 의미한다. target 즉, M만큼 찾았을 때 정지한다.
            // 모든 작업이 끝나면 저장한 결과를 출력한다.
            System.out.print(sb);
        }

        // 순열과 달리 조합이기 때문에 다음 시작 값이 중요하다.
        private void backTracking(int depth, int[] result, StringBuilder sb, int start) {

            if (depth == M) {
                // 값을 저장한다.
                for (int value : result) {
                    sb.append(value).append(" ");
                }
                sb.append("\n");
                // 해당 작업 수행 이후에 return을 해주어야 이어서 for문에 진입하지 않고 종료된다.
                return;
            }

            // 이전에 사용했던 시작점에서 출발해야한다.
            for (int i = start; i <= N; i++) {
                // 사용했기 때문에 depth가 하나 추가된다.
                result[depth] = i;
                backTracking(depth + 1, result, sb, i); // 현재 사용한 값을 보내준다.
            }
        }
    }
}
