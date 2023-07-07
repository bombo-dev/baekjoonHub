import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int N;
    static int K;
    static Queue<Integer> q;
    static StringTokenizer st;
    static List<ArrayList<Integer>> graph;
    static int[] inDegree;
    static int[] money;
    static int[] dp;
    static int end;
    static StringBuilder sb = new StringBuilder();
    static int total;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            total = 0;
            graph = new ArrayList<>();
            q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            inDegree = new int[N + 1];
            money = new int[N + 1];
            dp = new int[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                money[i + 1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                inDegree[v]++;
            }

            end = Integer.parseInt(br.readLine());

            for (int i = 1; i < N + 1; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                    dp[i] = money[i];
                }
            }
            bfs();
            sb.append(dp[end]).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int cur = q.poll();

            if (end == cur) {
                break;
            }

            for (int v : graph.get(cur)) {
                dp[v] = Math.max(dp[v], dp[cur] + money[v]);
                inDegree[v]--;

                if (inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
    }
}
