import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int L;
    static int R;
    static int C;
    static int [][][] graph;
    static boolean [][][] visited;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int minute;
    static int[] object = new int[3];
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            minute = 0;
            if(L == 0 && R == 0 && C == 0)
                return;
            if(L > 0) {
                graph = new int[L][R][C];
                visited = new boolean[L][R][C];
                q = new LinkedList<>();
                for(int l = 0; l < L; l++) {
                    for (int r = 0; r < R; r++) {
                        String value = br.readLine();
                        for(int c = 0; c < C; c++) {
                            if(value.charAt(c) == 'S') {
                                q.offer(new int[]{l, r, c});
                            } else if (value.charAt(c) == '#') {
                                graph[l][r][c] = -1;
                            } else if (value.charAt(c) == 'E') {
                                object[0] = l;
                                object[1] = r;
                                object[2] = c;
                            }
                        }
                    }
                    br.readLine();
                }
                // BFS
                bfs();
                int rl = object[0];
                int rx = object[1];
                int ry = object[2];
                minute = graph[rl][rx][ry];
            }
            if(minute == 0) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + minute + " minute(s).");
            }
        }
    }

    public static void bfs() {
        while(!q.isEmpty()) {
            int[] position = q.poll();
            int pl = position[0];
            int px = position[1];
            int py = position[2];

            for(int i = 0; i < 6; i++) {
                int nl = pl + dl[i];
                int nx = px + dx[i];
                int ny = py + dy[i];

                if(nl >= 0 && nl < L && nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nl][nx][ny] && graph[nl][nx][ny] != -1) {
                    q.offer(new int[]{nl, nx, ny});
                    visited[nl][nx][ny] = true;
                    graph[nl][nx][ny] = graph[pl][px][py] + 1;
                    if(nl == object[0] && nx == object[1] && ny == object[2]) {
                        return;
                    }
                }
            }
        }
    }
}