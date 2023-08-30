import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private int M;
        private int[][] graph;
        private int[] dice;
        private int commandCount;
        private int[] dx = {0, 0, -1, 1};
        private int[] dy = {1, -1, 0, 0};
        private int x;
        private int y;

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new int[N][M];
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            commandCount = Integer.parseInt(st.nextToken());

            dice = new int[7];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            while (st.hasMoreTokens()) {
                int command = Integer.parseInt(st.nextToken());
                if (!isMove(command)) {
                    continue;
                }
                moveDice(command);
                coloring();
                sb.append(dice[1]).append("\n");
            }
            System.out.print(sb);
        }

        private void coloring() {
            if (graph[x][y] == 0) {
                graph[x][y] = dice[6];
            } else {
                dice[6] = graph[x][y];
                graph[x][y] = 0;
            }
        }

        private boolean isMove(int command) {
            int px = x;
            int py = y;

            int nx = px + dx[command - 1];
            int ny = py + dy[command - 1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                return false;
            }

            x = nx;
            y = ny;
            return true;
        }

        private void moveDice(int command) {
            int temp = dice[1];
            switch (command) {
                case 1: // 동쪽으로 이동
                    dice[1] = dice[4];
                    dice[4] = dice[6];
                    dice[6] = dice[3];
                    dice[3] = temp;
                    break;
                case 2: // 서쪽으로 이동
                    dice[1] = dice[3];
                    dice[3] = dice[6];
                    dice[6] = dice[4];
                    dice[4] = temp;
                    break;
                case 3: // 북쪽으로 이동
                    dice[1] = dice[5];
                    dice[5] = dice[6];
                    dice[6] = dice[2];
                    dice[2] = temp;
                    break;
                case 4: // 남쪽으로 이동
                    dice[1] = dice[2];
                    dice[2] = dice[6];
                    dice[6] = dice[5];
                    dice[5] = temp;
                    break;
            }
        }
    }
}
