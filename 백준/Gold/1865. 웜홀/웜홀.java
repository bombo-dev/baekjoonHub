import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int TC;

        public void solve() throws IOException {
            TC = stoi(br.readLine());

            while (TC-- > 0) {
                boolean flag = false;
                StringTokenizer st = getStringTokenizer();
                int N = stoi(st.nextToken()); // 지점
                int M = stoi(st.nextToken()); // 도로
                int W = stoi(st.nextToken()); // 웜홀

                int[][] graph = new int[N + 1][N + 1];

                for (int i = 1; i < N + 1; i++) {
                    Arrays.fill(graph[i], (int) 1e9);
                }

                for (int i = 0; i < M; i++) {
                    st = getStringTokenizer();
                    int start = stoi(st.nextToken());
                    int end = stoi(st.nextToken());
                    int time = stoi(st.nextToken());

                    if (graph[start][end] > time) {
                        graph[start][end] = time;
                        graph[end][start] = time;
                    }
                }

                for (int i = 0; i < W; i++) {
                    st = getStringTokenizer();
                    int start = stoi(st.nextToken());
                    int end = stoi(st.nextToken());
                    int time = stoi(st.nextToken());
                    graph[start][end] = -time;
                }

                for (int k = 1; k < N + 1; k++) {
                    for (int i = 1; i < N + 1; i++) {
                        for (int j = 1; j < N + 1; j++) {
                            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                        }
                    }
                }

                for (int i = 1; i < N + 1; i++) {
                    if (graph[i][i] < 0) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
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
