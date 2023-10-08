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
        private int[][] dp;
        private String chars1;
        private String chars2;

        public void solve() throws IOException {
            chars1 = br.readLine();
            chars2 = br.readLine();

            dp = new int[chars1.length() + 1][chars2.length() + 1];

            LCS();
            System.out.print(dp[chars1.length()][chars2.length()]);
        }

        private void LCS() {
            for (int i = 1; i <= chars1.length(); i++) {
                for (int j = 1; j <= chars2.length(); j++) {
                    if (chars1.charAt(i - 1) == chars2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
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
