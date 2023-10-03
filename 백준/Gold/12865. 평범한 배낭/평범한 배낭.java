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
        private int K;
        private int[][] backpacks;
        private int[][] dp;

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            K = stoi(st.nextToken());

            backpacks = new int[N + 1][2];
            dp = new int[N + 1][K + 1];


            for (int i = 0; i < N; i++) {
                st = getStringTokenizer();
                int weight = stoi(st.nextToken());
                int value = stoi(st.nextToken());

                backpacks[i + 1][0] = weight;
                backpacks[i + 1][1] = value;
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    int weight = backpacks[i][0];
                    int value = backpacks[i][1];
                    if (j < weight) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - weight] + value, dp[i - 1][j]);
                    }
                }
            }

            System.out.print(dp[N][K]);
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
