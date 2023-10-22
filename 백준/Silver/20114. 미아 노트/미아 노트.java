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
        private int W;


        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            StringBuilder sb = new StringBuilder();
            N = stoi(st.nextToken());
            H = stoi(st.nextToken());
            W = stoi(st.nextToken());

            char[][] inputCharacters = new char[H][W];

            for (int i = 0; i < H; i++) {
                inputCharacters[i] = br.readLine().toCharArray();
            }

            for (int i = 0; i < N * W; i += W) {
                boolean isFind = false;
                loop:
                for (int j = i; j < i + W; j++) {
                    for (int k = 0; k < H; k++) {
                        if (inputCharacters[k][j] != '?') {
                            sb.append(inputCharacters[k][j]);
                            isFind = true;
                            break loop;
                        }
                    }
                }

                if (!isFind) {
                    sb.append('?');
                }
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
