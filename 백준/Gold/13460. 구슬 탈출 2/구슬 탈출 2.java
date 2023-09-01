import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private static final int NORTH = 0;
        private static final int SOUTH = 1;
        private static final int WEST = 2;
        private static final int EAST = 3;

        private int N;
        private int M;
        private int[] dx = {-1, 1, 0, 0};
        private int[] dy = {0, 0, -1, 1};
        private int[] redPosition;
        private int[] bluePosition;
        private int[] exitPosition;
        private int[][] counts;
        private char[][] board;
        private int exitCount = Integer.MAX_VALUE;
        private boolean[][][][] visited = new boolean[11][11][11][11];

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            counts = new int[N][M];

            for (int i = 0; i < N; i++) {
                var value = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = value.charAt(j);
                    if (board[i][j] == 'R') {
                        redPosition = new int[]{i, j};
                    } else if (board[i][j] == 'B') {
                        bluePosition = new int[]{i, j};
                    } else if (board[i][j] == 'O') {
                        exitPosition = new int[]{i, j};
                    }
                }
            }

            // 이동한 배열에 대해 매번 clone
            // 이동하고자 하는 방향에 서로 다른 구슬이 있는지 확인, 이걸 빠르게 확인하는 방법은 이동 된 구슬에 대해 전달.
            // count, direction, redPosition, bluePosition
            int result = moveBalls(new int[]{redPosition[0], redPosition[1]}, new int[]{bluePosition[0], bluePosition[1]});
            System.out.print(result);
        }

        private int moveBalls(int[] redPos, int[] bluePos) {

            Queue<Balls> q = new LinkedList<>();
            q.offer(new Balls(new Position(redPos[0], redPos[1]), new Position(bluePos[0], bluePos[1]), 0));
            visited[redPos[0]][redPos[1]][bluePos[0]][bluePos[1]] = true;
            // 각 방향에 대한 선행 구슬 구하기.
            // 만약 선행 구슬이 없다면 red가 우선.
            while (!q.isEmpty()) {
                Balls now = q.poll();

                if (now.count > 10) {
                    return -1;
                }

                if (board[now.red.x][now.red.y] == 'O') {
                    return now.count;
                }

                for (int i = 0; i < 4; i++) {
                    Position movedRedBall = moveBall(now.red, i);
                    Position movedBlueBall = moveBall(now.blue, i);

                    if (board[movedBlueBall.x][movedBlueBall.y] == 'O') {
                        continue;
                    }

                    if (movedRedBall.samePosition(movedBlueBall)) {
                        adjustPosition(now, movedRedBall, movedBlueBall, i);
                    }

                    if (!visited[movedRedBall.x][movedRedBall.y][movedBlueBall.x][movedBlueBall.y]) {
                        q.offer(new Balls(movedRedBall, movedBlueBall, now.count + 1));
                        visited[movedRedBall.x][movedRedBall.y][movedBlueBall.x][movedBlueBall.y] = true;
                    }
                }
            }
            return -1;
        }

        private void adjustPosition(Balls pre, Position movedRedBall, Position movedBlueBall, int direction) {
            switch (direction) {
                case NORTH:
                    if (pre.red.x < pre.blue.x) {
                        movedBlueBall.x++;
                    } else {
                        movedRedBall.x++;
                    }
                    break;

                case SOUTH:
                    if (pre.red.x > pre.blue.x) {
                        movedBlueBall.x--;
                    } else {
                        movedRedBall.x--;
                    }
                    break;

                case EAST:
                    if (pre.red.y > pre.blue.y) {
                        movedBlueBall.y--;
                    } else {
                        movedRedBall.y--;
                    }
                    break;

                case WEST:
                    if (pre.red.y < pre.blue.y) {
                        movedBlueBall.y++;
                    } else {
                        movedRedBall.y++;
                    }
                    break;
            }
        }

        private Position moveBall(Position ball, int direction) {
            int px = ball.x;
            int py = ball.y;

            while (true) {
                int nx = px + dx[direction];
                int ny = py + dy[direction];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') {
                    break;
                }

                px = nx;
                py = ny;

                if (board[px][py] == 'O') {
                    break;
                }
            }

            return new Position(px, py);
        }
    }

    private static class Balls {
        Position red;
        Position blue;
        int count;

        public Balls(Position red, Position blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean samePosition(Position p) {
            return x == p.x && y == p.y;
        }
    }
}
