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
        private int[] results;
        private int[] sequences;

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            K = stoi(st.nextToken());

            results = new int[N];
            sequences = new int[N];

            st = getStringTokenizer();

            for (int i = 0; i < N; i++) {
                results[i] = stoi(st.nextToken());
            }

            st = getStringTokenizer();

            for (int i = 0; i < N; i++) {
                sequences[i] = stoi(st.nextToken());
            }

            while (K-- > 0) {
                int[] temps = new int[N];

                for (int i = 0; i < N; i++) {
                    temps[sequences[i] - 1] = results[i];
                }

                results = temps;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(results[i]).append(" ");
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
