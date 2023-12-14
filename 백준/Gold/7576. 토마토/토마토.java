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

        private static final int EMPTY = 0;
        private static final int X = -1;
        private static final int TOMATO = 1;
        private int max = 0;

        private int[] dx = new int[]{-1, 0, 1, 0};
        private int[] dy = new int[]{0, 1, 0, -1};

        private int[][] baskets;
        private boolean[][] visited;
        private Deque<int[]> dq = new ArrayDeque<>();
        private int N, M;

        // 간과하고 시작 한 부분 BFS의 시간복잡도
        // BFS의 시간복잡도는 O(V^2) or O(V + E), 토마토가 담긴 바구니를 V, 인접 4방향을 E 라고 하자.
        // 토마토가 담긴 바구니는 총 MN개가 존재할 수 있고, E는 MN개당 4개씩 존재한다.
        // 따라서, O(MN + 4MN) = O(5MN)이 되고, 여기서 상수는 큰 영향을 끼치지 않으므로 O(MN)이라고 볼 수 있다.
        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            M = stoi(st.nextToken());
            N = stoi(st.nextToken());
            baskets = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = getStringTokenizer();
                for (int j = 0; j < M; j++) {
                    int value = stoi(st.nextToken());
                    if (value == TOMATO) {
                        dq.offerLast(new int[]{i, j});
                        visited[i][j] = true;
                    }
                    baskets[i][j] = value;
                }
            }

            while (!dq.isEmpty()) {
                int[] now = dq.pollFirst();
                int x = now[0];
                int y = now[1];

                max = Math.max(max, baskets[x][y]);

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                        continue;
                    }

                    if (baskets[nx][ny] == X) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    baskets[nx][ny] = baskets[x][y] + 1;
                    dq.offerLast(new int[]{nx, ny});
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (baskets[i][j] == 0) {
                        System.out.print(-1);
                        return;
                    }
                }
            }

            System.out.print(max - 1);
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
