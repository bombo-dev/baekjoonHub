import java.io.*;
import java.util.*;

class Wall {
    int x;
    int y;
    int preWall;
    int time;

    public Wall(int x, int y, int preWall, int time) {
        this.x = x;
        this.y = y;
        this.preWall = preWall;
        this.time = time;
    }
}

public class Main {
    static int N;
    static int M;
    static int K;
    static int[][] graph;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[K + 1][N][M]; // 0은 벽을 안 부심

        for(int i = 0; i < N; i++) {
            String value = br.readLine();
            for(int j = 0; j < M; j++) {
                graph[i][j] = Character.getNumericValue(value.charAt(j));
            }
        }
        BFS();

        if(result == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(result);
        }
    }

    public static void BFS() {
        Queue<Wall> q = new LinkedList<>();
        q.offer(new Wall(0, 0, 0, 1));
        visited[0][0][0] = true;
        while(!q.isEmpty()) {
            Wall w = q.poll();
            int px = w.x;
            int py = w.y;
            int preWall = w.preWall;
            int time = w.time;

            if(px == N - 1 && py == M - 1) {
                result = Math.min(result, time);
            }

            for(int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if(preWall < K) {
                        if(graph[nx][ny] == 1) {
                            if(!visited[preWall + 1][nx][ny]) {
                                q.offer(new Wall(nx, ny, preWall + 1, time + 1));
                                visited[preWall + 1][nx][ny] = true;
                            }
                        } else {
                            if(!visited[preWall][nx][ny]) {
                                q.offer(new Wall(nx, ny, preWall, time + 1));
                                visited[preWall][nx][ny] = true;
                            }
                        }
                    } else {
                        if(graph[nx][ny] == 0 && !visited[preWall][nx][ny]) {
                            q.offer(new Wall(nx, ny, preWall, time + 1));
                            visited[preWall][nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
}
