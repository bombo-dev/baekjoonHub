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
        private int count = 0;

        public void solve() throws IOException {
            N = stoi(br.readLine());

            int start = 1;
            int end = 1;
            int total = 1;

            while (start != N) {

                if (total == N) {
                    count++;
                    total += ++end;
                } else if (total < N) {
                    total += ++end;
                } else {
                    total -= start++;
                }
            }

            System.out.print(count + 1);
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
