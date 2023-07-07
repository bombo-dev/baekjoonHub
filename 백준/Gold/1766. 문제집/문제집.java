import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] inDegree;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static StringTokenizer st;
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            inDegree[v]++;
        }

        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        bfs();
        System.out.print(sb);
    }

    private static void bfs() {
        while (!pq.isEmpty()) {
            int cur = pq.poll();

            sb.append(cur).append(" ");

            for (int end : graph.get(cur)) {
                inDegree[end]--;

                if (inDegree[end] == 0) {
                    pq.offer(end);
                }
            }
        }
    }
}
