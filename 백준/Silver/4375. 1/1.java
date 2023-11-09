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
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            var value = "";

            while ((value = br.readLine()) != null) {
                var n = stoi(value);
                var base = 1;
                var count = 1;

                while ((base = base % n) != 0) {
                    count++;
                    base = base * 10 + 1;
                }
                sb.append(count).append("\n");
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
