import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int R;
    static int Q;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] subtreeSize;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        subtreeSize = new int[N + 1];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(R, -1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[u]).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int node, int parent) {
        subtreeSize[node] = 1; // 자신을 포함하기 때문에 1로 초기화
        for (int child : graph.get(node)) {
            if (child == parent) continue;
            dfs(child, node);
            subtreeSize[node] += subtreeSize[child];
        }
    }
}