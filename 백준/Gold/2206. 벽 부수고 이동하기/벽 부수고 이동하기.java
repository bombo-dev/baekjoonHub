import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int max = 0;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        private int N;
        private int M;

        private int[] dx = new int[]{-1, 0, 1, 0};
        private int[] dy = new int[]{0, 1, 0, -1};
        private int[][] board;
        private boolean[][][] visited;


        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());

            board = new int[N + 1][M + 1];
            visited = new boolean[2][N + 1][M + 1];

            for (int i = 1; i <= N; i++) {
                String value = br.readLine();
                for (int j = 0; j < M; j++) {
                    int wall = Character.getNumericValue(value.charAt(j));
                    board[i][j + 1] = wall;
                }
            }

            visited[0][1][1] = true;
            bfs();

            if (max == 0) {
                System.out.print(-1);
            } else {
                System.out.print(max);
            }
        }

        private void bfs() {
            Deque<int[]> dq = new ArrayDeque<>();
            dq.offerLast(new int[]{1, 1, 0, 1});

            while (!dq.isEmpty()) {
                int[] now = dq.pollFirst();
                int x = now[0];
                int y = now[1];
                int visit = now[2];
                int count = now[3];

                if (x == N && y == M) {
                    max = count;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 1 || nx > N || ny < 1 || ny > M) {
                        continue;
                    }

                    if (visited[visit][nx][ny]) {
                        continue;
                    }

                    if (board[nx][ny] == 0) {
                        visited[visit][nx][ny] = true;
                        dq.offerLast(new int[]{nx, ny, visit, count + 1});
                    } else {
                        if (visit == 0) {
                            visited[1][nx][ny] = true;
                            dq.offerLast(new int[]{nx, ny, 1, count + 1});
                        }
                    }
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
