import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V;
    static int E;
    static int[][] graph;
    static final int INF = (int) 1e9;
    static int min = (int) 1e9;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new int[V + 1][V + 1];

        for (int i = 0; i < V + 1; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < V + 1; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u][v] = d;
        }

        for (int k = 1; k < V + 1; k++) {
            for (int i = 1; i < V + 1; i++) {
                for (int j = 1; j < V + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }


        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                if (i != j && graph[i][j] != INF && graph[j][i] != INF) {
                    min = Math.min(min, graph[i][j] + graph[j][i]);
                }
            }
        }

        if (min != INF) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }
}
