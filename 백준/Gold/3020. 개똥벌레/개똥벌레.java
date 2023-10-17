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
        private int H;

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            H = stoi(st.nextToken());
            int min = N;
            int count = 0;

            int[] tops = new int[H + 2];
            int[] downs = new int[H + 2];

            for (int i = 0; i < N / 2; i++) {
                int down = stoi(br.readLine());
                downs[down]++;
                int top = H - stoi(br.readLine()) + 1;
                tops[top]++;
            }

            for (int i = 1; i <= H; i++) {
                downs[i] += downs[i - 1];
            }

            for (int i = H; i >= 1; i--) {
                tops[i] += tops[i + 1];
            }

            for (int i = 1; i <= H; i++) {
                // 석순의 개수
                int downCount = downs[H] - downs[i - 1];
                // 종유석의 개수
                int topCount = tops[1] - tops[i + 1];

                int conflict = downCount + topCount;

                if (conflict == min) {
                    count++;
                } else if (min > conflict) {
                    min = conflict;
                    count = 1;
                }
            }

            System.out.print(min + " " + count);
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
