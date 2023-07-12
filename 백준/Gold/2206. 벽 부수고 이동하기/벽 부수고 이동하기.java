import java.io.*;
import java.util.*;

class Status {
    int x;
    int y;
    boolean crash;

    public Status(int x, int y, boolean crash) {
        this.x = x;
        this.y = y;
        this.crash = crash;
    }
}

public class Main {
    static StringTokenizer st;
    static int r;
    static int c;
    static int[][] graph;
    static int[][] time;
    static boolean[][] visitedCrash; // 벽을 부수고 방문
    static boolean[][] visitedNoCrash; // 벽을 안부수고 방문
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    static Queue<Status> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new int[r][c];
        time = new int[r][c];
        visitedCrash = new boolean[r][c];
        visitedNoCrash = new boolean[r][c];

        time[0][0] = 1;
        visitedCrash[0][0] = true;
        visitedNoCrash[0][0] = true;


        for (int i = 0; i < r; i++) {
            String value = br.readLine();
            for (int j = 0; j < c; j++) {
                graph[i][j] = value.charAt(j) - '0';
            }
        }

        q.offer(new Status(0, 0, false));

        BFS();

        if (time[r - 1][c - 1] == 0) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    public static void BFS() {
        while (!q.isEmpty()) {
            Status position = q.poll();
            int px = position.x;
            int py = position.y;
            boolean crash = position.crash;
            
            if(px == r - 1 && py == c - 1) {
                min = Math.min(min, time[px][py]);
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    if(!crash && !visitedNoCrash[nx][ny]) {
                        if(graph[nx][ny] == 1) {
                            q.offer(new Status(nx, ny, true));
                        } else {
                            q.offer(new Status(nx, ny, false));
                        }
                        time[nx][ny] = time[px][py] + 1;
                        visitedNoCrash[nx][ny] = true;
                    } else if(crash && !visitedCrash[nx][ny]){
                        if(graph[nx][ny] == 0) {
                            q.offer(new Status(nx, ny, true));
                            time[nx][ny] = time[px][py] + 1;
                            visitedCrash[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
}