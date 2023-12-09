import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int[] numbers;
        private int[] dp;

        public void solve() throws IOException {
            N = stoi(br.readLine());

            numbers = new int[N + 1];
            dp = new int[N + 1];

            StringTokenizer st = getStringTokenizer();

            for (int i = 1; i <= N; i++) {
                numbers[i] = stoi(st.nextToken());
                dp[i] = numbers[i];
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
                }
            }

            System.out.print(dp[N]);
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
