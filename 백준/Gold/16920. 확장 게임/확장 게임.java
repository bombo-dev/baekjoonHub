import java.io.*;
import java.util.*;

class Game implements Comparable<Game>{
    int number;
    int x;
    int y;
    int counting;

    public Game(int number, int x, int y, int counting){
        this.number = number;
        this.x = x;
        this.y = y;
        this.counting = counting;
    }

    @Override
    public int compareTo(Game o) {
        return this.number - o.number;
    }
}
public class Main {
    static int N,M,P;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] dist;
    static int[][] graph;
    static int[] time;
    static Queue<Game>[] q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dist = new int[P + 1];
        time = new int[P + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= P; i++){
            dist[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[N][M];
        q = new LinkedList[P + 1];

        for(int i = 1; i <= P; i++){
            q[i] = new LinkedList<>();
        }

        for(int i = 0; i < N; i++) {
            String value = br.readLine();
            for (int j = 0; j < M; j++) {
                if (value.charAt(j) == '.') {
                    graph[i][j] = 0;
                } else if (value.charAt(j) == '#') {
                    graph[i][j] = -1;
                } else {
                    int number = Character.getNumericValue(value.charAt(j));
                    graph[i][j] = number;
                    time[number]++;
                    q[number].offer(new Game(number, i, j, 1));
                }
            }
        }

        BFS();
        for(int i = 1; i <= P; i++){
            System.out.print(time[i] + " ");
        }
    }

    static void BFS(){

        int p = 1;
        int round = 0;

        while(true) {
            int cnt = expend(q[p], p);
            time[p] += cnt;
            p++;
            round += cnt;

            if(p == P + 1) {
                if(round == 0) break;
                round = 0;
                p = 1;
            }
        }
    }

    static int expend(Queue<Game> q, int player) {
        int cnt = 0;
        int counting = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Game game = q.poll();
                int px = game.x;
                int py = game.y;

                for(int j = 0; j < 4; j++) {
                    int nx = px + dx[j];
                    int ny = py + dy[j];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < M && graph[nx][ny] == 0) {
                        q.offer(new Game(player, nx, ny, counting + 1));
                        graph[nx][ny] = player;
                        cnt++;
                    }
                }
            }
            counting++;
            if(counting > dist[player]) break;
        }
        return cnt;
    }
}
