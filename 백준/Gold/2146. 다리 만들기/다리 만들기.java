import java.io.*;
import java.util.*;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int[][] graph;
    static int[][] earth;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        earth = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            String[] value = br.readLine().split(" ");
            for(int j = 0; j < value.length; j++) {
                graph[i][j] = Integer.parseInt(value[j]);
                if(Integer.parseInt(value[j]) == 1) {
                    earth[i][j] = 1;
                } else {
                    earth[i][j] = 0;
                }
            }
        }

        int group = 2;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && earth[i][j] == 1) {
                    dfs(i, j, group);
                    group++;
                }
            }
        }

        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && earth[i][j] == 1) {
                    bfs(i, j);
                    visited = new boolean[N][N];
                }
            }
        }

        System.out.print(min);
    }

    public static void dfs(int x, int y, int group) {

        visited[x][y] = true;
        graph[x][y] = group;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && graph[nx][ny] == 1) {
                dfs(nx, ny, group);
            }
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        int group = graph[x][y];

        while(!q.isEmpty()) {
            int[] position = q.poll();
            int px = position[0];
            int py = position[1];

            for(int i = 0; i  < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if(earth[px][py] == 0 && earth[nx][ny] == 1 && graph[nx][ny] != group) {
                        min = Math.min(graph[px][py], min);
                        continue;
                    }

                    if(earth[px][py] == 1 && earth[nx][ny] == 0) {
                        graph[nx][ny] = 1;
                    } else if(earth[px][py] == 0 && earth[nx][ny] == 0) {
                        graph[nx][ny] = graph[px][py] + 1;
                    }

                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}