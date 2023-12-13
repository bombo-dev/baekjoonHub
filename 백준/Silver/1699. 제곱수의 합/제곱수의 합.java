import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int[] dp;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            dp = new int[N + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[1] = 1;

            int lastOne = 1;
            for (int i = 2; i <= N; i++) {
                double sqrt = Math.sqrt(i);
                if (i % sqrt == 0) {
                    lastOne = i;
                    dp[i] = 1;
                    continue;
                }

                for (int j = lastOne; j < i; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }

                for (int k = 1; k < Math.sqrt(i); k++) {
                    dp[i] = Math.min(dp[i], dp[k * k] + dp[i - (k * k)]);
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
