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
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int max = 0;

        private int N, M, K;

        private int[] dx = new int[]{-1, 0, 1, 0};
        private int[] dy = new int[]{0, 1, 0, -1};
        private int[][] boards;
        private boolean[][][] visited;

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            K = stoi(st.nextToken());

            boards = new int[N + 1][M + 1];
            visited = new boolean[K + 1][N + 1][M + 1];

            for (int i = 1; i <= N; i++) {
                String value = br.readLine();
                for (int j = 0; j < M; j++) {
                    boards[i][j + 1] = Character.getNumericValue(value.charAt(j));
                }
            }

            bfs();

            if (max == 0) {
                System.out.print(-1);
            } else {
                System.out.print(max);
            }
        }

        private void bfs() {
            visited[0][1][1] = true;

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

                    if (boards[nx][ny] == 0) {
                        visited[visit][nx][ny] = true;
                        dq.offerLast(new int[]{nx, ny, visit, count + 1});
                    } else {
                        if (visit < K && !visited[visit + 1][nx][ny]) {
                            visited[visit + 1][nx][ny] = true;
                            dq.offerLast(new int[]{nx, ny, visit + 1, count + 1});
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
