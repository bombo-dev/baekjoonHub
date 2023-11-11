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
        private StringTokenizer st;

        public void solve() throws IOException {
            var totalMoney = stoi(br.readLine());
            var total = 0;
            int N = stoi(br.readLine());

            for (int i = 0; i < N; i++) {
                st = getStringTokenizer();
                var money = stoi(st.nextToken());
                var goodsCount = stoi(st.nextToken());

                total += money * goodsCount;
            }

            if (total == totalMoney) {
                System.out.print("Yes");
            } else {
                System.out.print("No");
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
