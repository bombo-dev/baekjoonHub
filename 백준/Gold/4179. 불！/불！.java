import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int R;
    private static int C;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Queue<int[]> fireQ = new LinkedList<>();
        Queue<int[]> jihoonQ = new LinkedList<>();

        boolean[][] visited = new boolean[R][C];
        boolean[][] fireVisited = new boolean[R][C];
        int[][] board = new int[R][C];

        for (int i = 0; i < R; i++) {
            String chars = br.readLine();
            for (int j = 0; j < C; j++) {
                if (chars.charAt(j) == 'J') {
                    jihoonQ.offer(new int[]{i, j, 1});
                    visited[i][j] = true;
                    board[i][j] = 1;
                }

                // 불은 한 개만 주어지지 않을 수 있다.
                if (chars.charAt(j) == 'F') {
                    fireQ.offer(new int[]{i, j});
                    fireVisited[i][j] = true;
                    board[i][j] = 2;
                }

                if (chars.charAt(j) == '.') {
                    board[i][j] = 0;
                }

                if (chars.charAt(j) == '#') {
                    board[i][j] = -1;
                }
            }
        }

        // 먼저 불을 이동시켜놓고 불이 이동 할 수 있는 공간에 대해서 명시한다.
        // 불은 벽에 옮겨 붙을 수 없다.

        // 지훈이는 불맵과 기존의 미로 경로를 보고 판단하여 이동한다.
        // 지훈이는 이미 지나온 길은 지나가면 안된다.

        int result = move(fireQ, jihoonQ, fireVisited, visited, board);
        if (result == -1) {
            System.out.print("IMPOSSIBLE");
        } else {
            System.out.print(result);
        }
    }

    private static int move(Queue<int[]> fq, Queue<int[]> jq, boolean[][] fv, boolean[][] jv, int[][] board) {

        while (!jq.isEmpty()) {
            int fireEnd = fq.size();
            for (int i = 0; i < fireEnd; i++) {
                int[] fPosition = fq.poll();
                int py = fPosition[0];
                int px = fPosition[1];

                for (int j = 0; j < 4; j++) {
                    int nx = px + dx[j];
                    int ny = py + dy[j];

                    if (nx == -1 || nx == C || ny == -1 || ny == R || fv[ny][nx]) {
                        continue;
                    }

                    if (board[ny][nx] == -1 || board[ny][nx] == 1) {
                        continue;
                    }

                    fv[ny][nx] = true;
                    board[ny][nx] = 2;
                    fq.offer(new int[]{ny, nx});
                }
            }

            int jEnd = jq.size();
            for (int i = 0; i < jEnd; i++) {
                int[] jPosition = jq.poll();
                int py = jPosition[0];
                int px = jPosition[1];
                int pc = jPosition[2];

                if (px == 0 || px == C - 1 || py == 0 || py == R - 1) {
                    return pc;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = px + dx[j];
                    int ny = py + dy[j];

                    if (jv[ny][nx] || board[ny][nx] == 2 || board[ny][nx] == -1) {
                        continue;
                    }

                    board[ny][nx] = 1;
                    jv[ny][nx] = true;
                    jq.offer(new int[]{ny, nx, pc + 1});
                }
            }
        }

        return -1;
    }
}
