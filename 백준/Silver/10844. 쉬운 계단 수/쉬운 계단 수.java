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
        private int[][] dp;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            dp = new int[N + 1][10];

            for (int i = 1; i <= 9; i++) {
                dp[1][i] = 1;
            }

            for (int i = 2; i <= N; i++) {
                dp[i][0] = dp[i - 1][1] % 1000000000;

                for (int j = 1; j <= 8; j++) {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
                }

                dp[i][9] = dp[i - 1][8] % 1000000000;
            }

            int result = 0;

            for (int j = 0; j <= 9; j++) {
                result = (result + dp[N][j]) % 1000000000;
            }
            
            System.out.print(result);
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
