import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int T;
        private int total = 0;
        private StringBuilder sb = new StringBuilder();
        private Set<String> coinSet = new HashSet<>();

        public void solve() throws IOException {
            T = stoi(br.readLine());

            while (T-- > 0) {
                total = 0;
                coinSet.clear();

                int coinCount = stoi(br.readLine());
                int[] coins = new int[coinCount + 1];
                int[][] coinCounts = new int[coinCount + 1][10001];

                StringTokenizer st = getStringTokenizer();

                for (int i = 0; i < coinCount; i++) {
                    int value = stoi(st.nextToken());
                    coins[i + 1] = value;
                    coinCounts[i + 1][value]++;
                }

                int targetMoney = stoi(br.readLine());

                makeMoney(coins, coinCounts, targetMoney);
                sb.append(coinCounts[coinCount][targetMoney]).append("\n");
            }

            System.out.print(sb);
        }

        private void makeMoney(int[] coins, int[][] coinCounts, int targetMoney) {
            for (int i = 1; i < coins.length; i++) {
                for (int j = 1; j < targetMoney + 1; j++) {
                    if (j - coins[i] < 0) {
                        coinCounts[i][j] = coinCounts[i - 1][j];
                        continue;
                    }

                    coinCounts[i][j] += coinCounts[i - 1][j] + coinCounts[i][j - coins[i]];
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
