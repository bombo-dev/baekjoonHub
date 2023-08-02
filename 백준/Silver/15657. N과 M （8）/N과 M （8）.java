import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        private int[] arr;
        private int[] result;

        public void solution() throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N];
            result = new int[M];

            st = new StringTokenizer(br.readLine());
            // 값을 받지만 현재 값이 정렬이 되어 있지 않은 상태이다.
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 최대 입력 값은 8 이하이다.
            // 정렬 + 중복이 가능한 조합을 사용하여야 한다. 합하면 시간복잡도는 다음과 같다.
            // Arrays.sort()를 하기 때문에 8 ^ 2 (정렬) + N^M 보다 작다.(<= 8 ^ 8) 따라서 4170 이다
            Arrays.sort(arr);

            // 중복이 가능한 조합이다. 따라서 방문 배열이 필요없다.
            // 시작점은 이전과 동일해야 한다.
            StringBuilder sb = new StringBuilder();
            backTracking(0, sb, 0);

            System.out.print(sb);
        }

        private void backTracking(int depth, StringBuilder sb, int start) {

            if (depth == M) {
                for (int value : result) {
                    sb.append(value).append(" ");
                }
                sb.append("\n");
                return;
            }

            for (int i = start; i < N; i++) {
                result[depth] = arr[i];
                backTracking(depth + 1, sb, i);
            }
        }
    }
}
