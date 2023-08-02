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
            visited = new boolean[N + 1];

            // N과 M에 대해서 backTracking을 수행한다.
            // 조건이 중복 없이 M개를 고를 수열이다. 순서가 존재하지 않기 때문에 해당 문제는 조합을 사용해야 한다.
            // 시간 복잡도는 조합의 시간 복잡도인 nPm 이 시간복잡도이고 해당 문제의 조건은 M <= N <= 8 이기 때문에 최악의 경우의 수는 8C(N / 2) 이다.

            // 해당 문제는 조합을 사용하여 재 방문을 하지 않는다. 따라서 visited가 필요가 없다.

            // 각 depth의 값을 가지고 있는 부분을 이용하면 remove를 사용하지 않고도 값을 유지할 수 있다.
            int[] result = new int[M];
            StringBuilder sb = new StringBuilder(); // 외부에서 생성하고 하나의 메모리만 사용한다.
            backTracking(visited, 0, result, sb, 1); // depth는 현재 추가 된 값을 의미한다. target 즉, M만큼 찾았을 때 정지한다.
            // 모든 작업이 끝나면 저장한 결과를 출력한다.
            System.out.print(sb);
        }

        // 순열과 달리 조합이기 때문에 다음 시작 값이 중요하다.
        private void backTracking(boolean[] visited, int depth, int[] result, StringBuilder sb, int start) {

            if (depth == M) {
                // 이 때, 출력을 해주어야 한다.
                // 하지만 visited에 있는 순서대로가 아닌 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
                // 이러한 값들을 순서대로 저장 할 필요가 있다.
                // 저장 된 값을 출력한다.

                for (int value : result) {
                    sb.append(value).append(" ");
                }
                sb.append("\n");
                // 해당 작업 수행 이후에 return을 해주어야 이어서 for문에 진입하지 않고 종료된다.
                return;
            }

            for (int i = start; i <= N; i++) {
                // 사용했기 때문에 depth가 하나 추가된다.
                result[depth] = i;
                backTracking(visited, depth + 1, result, sb, i + 1); // 현재 선택된 값의 다음 값부터 수행해야 되기 때문에 i + 1 부터 수행한다.
                // 재귀가 종료되게 됐을 때 가장 마지막에 수행되는 것이 제거되는 형태이다.
                // 재귀의 방식은 먼저 진행한 것에 대한 전파이다. 위에서 backTracking을 따라서 마지막 값이 수행되면 해당 값을 사용하지 않은것으로 변경해야 한다.
            }
        }
    }
}
