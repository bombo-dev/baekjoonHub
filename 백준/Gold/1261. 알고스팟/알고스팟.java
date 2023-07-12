import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int N;
    static int M;
    static StringTokenizer st;
    static int[][] graph;
    static boolean[][] visited;
    static int wallCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = input.charAt(j) - '0';
            }
        }

        bfs();
        System.out.print(wallCount);
    }

    static void bfs() {
        int[] position = new int[]{0, 0, 0};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        pq.offer(position);

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int px = now[0];
            int py = now[1];
            int wall = now[2];

            if (px == N - 1 && py == M - 1) {
                wallCount = Math.min(wallCount, wall);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if (graph[nx][ny] == 1) {
                    pq.offer(new int[]{nx, ny, wall + 1});
                } else {
                    pq.offer(new int[]{nx, ny, wall});
                }

                visited[nx][ny] = true;
            }
        }
    }
}
