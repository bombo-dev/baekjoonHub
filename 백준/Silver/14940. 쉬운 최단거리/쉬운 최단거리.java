import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static int[][] distance;
    static boolean[][] visited;
    static int[] startPoint = new int[2];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        distance = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 2) {
                    startPoint[0] = i;
                    startPoint[1] = j;
                }
                graph[i][j] = value;
            }
        }

        bfs();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    distance[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(distance[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static private void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startPoint[0], startPoint[1]});
        visited[startPoint[0]][startPoint[1]] = true;

        while (!q.isEmpty()) {
            int[] position = q.poll();
            int px = position[0];
            int py = position[1];

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if (graph[nx][ny] == 0) {
                    continue;
                }

                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                distance[nx][ny] = distance[px][py] + 1;
            }
        }
    }
}
