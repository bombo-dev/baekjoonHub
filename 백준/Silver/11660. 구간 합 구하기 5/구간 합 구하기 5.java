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
        private int M;
        private int[][] board;
        private int[][] sumSet;
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());

            board = new int[N][N];
            sumSet = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = getStringTokenizer();
                for (int j = 0; j < N; j++) {
                    board[i][j] = stoi(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                sumSet[i][0] = board[i][0];
            }

            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    sumSet[i][j] = sumSet[i][j - 1] + board[i][j];
                }
            }

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                calcSliceSum(st);
            }

            System.out.print(sb);
        }

        private void calcSliceSum(StringTokenizer st) {
            int x1 = stoi(st.nextToken()) - 1;
            int y1 = stoi(st.nextToken()) - 1;
            int x2 = stoi(st.nextToken()) - 1;
            int y2 = stoi(st.nextToken()) - 1;

            int sum = 0;

            for (int i = x1; i <= x2; i++) {
                if (y1 == 0) {
                    sum += sumSet[i][y2];
                } else {
                    sum += (sumSet[i][y2] - sumSet[i][y1 - 1]);
                }
            }

            sb.append(sum).append("\n");
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
