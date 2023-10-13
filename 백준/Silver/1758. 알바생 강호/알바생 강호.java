import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private Integer[] lines;

        public void solve() throws IOException {
            // 10 9 8 7 6
            // 10 (10 - (1 - 1) 8 ( 9 - (2 - 1) 6 ( 8 - (3 - 1) 4 2
            // 30

            N = stoi(br.readLine());
            lines = new Integer[N];

            for (int i = 0; i < N; i++) {
                lines[i] = stoi(br.readLine());
            }

            Arrays.sort(lines, Comparator.reverseOrder());

            long total = 0;

            for (int i = 0; i < N; i++) {
                int calc = lines[i] - (i + 1 - 1);

                if (calc > 0) {
                    total += calc;
                }
            }

            System.out.print(total);
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
