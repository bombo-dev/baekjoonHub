import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int[] arr;
        private int[] dp;
        private int lis = 1;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            arr = new int[N];
            dp = new int[N];

            StringTokenizer st = getStringTokenizer();
            for (int i = 0; i < N; i++) {
                arr[i] = stoi(st.nextToken());
            }

            dp[0] = 1;
            for (int i = 1; i < N; i++) {
                // dp의 모든 첫 시작은 1을 가질 수 있다.
                dp[i] = 1;
                // 시작 index를 초기화 해준다.
                for (int j = 0; j < N; j++) {
                    if (arr[j] < arr[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        lis = Math.max(lis, dp[i]);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(lis).append("\n");

            Deque<Integer> dq = new ArrayDeque<>();

            for (int i = N - 1; i >= 0; i--) {
                if (dp[i] == lis) {
                    dq.offerLast(arr[i]);
                    lis--;
                }
            }

            while (!dq.isEmpty()) {
                sb.append(dq.pollLast()).append(" ");
            }

            System.out.print(sb);

        }

        private StringTokenizer getStringTokenizer(String delim) throws IOException {
            return new StringTokenizer(br.readLine(), delim);
        }

        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine());
        }

        private int stoi(String number) {
            return Integer.parseInt(number);
        }
    }
}
