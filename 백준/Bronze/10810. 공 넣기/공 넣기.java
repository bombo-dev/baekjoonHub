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
        private int N;
        private int M;

        public void solve() throws IOException {
            st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());

            int[] buckets = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                var start = stoi(st.nextToken());
                var end = stoi(st.nextToken());
                var number = stoi(st.nextToken());
                for (int j = start; j <= end; j++) {
                    buckets[j] = number;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                sb.append(buckets[i]).append(" ");
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


