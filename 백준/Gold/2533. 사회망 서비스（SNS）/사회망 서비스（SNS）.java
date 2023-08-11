import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private List<List<Integer>> graph = new ArrayList<>();
        private boolean[] visited;
        private int[][] earlyAdapter;

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N + 1];
            earlyAdapter = new int[N + 1][2];

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            visited[1] = true;
            findMinAdapter(1);
            System.out.print(Math.min(earlyAdapter[1][0], earlyAdapter[1][1]));
        }

        private void findMinAdapter(int parent) {
            earlyAdapter[parent][0] = 0;
            earlyAdapter[parent][1] = 1;

            for (int child : graph.get(parent)) {
                if (!visited[child]) {
                    visited[child] = true;
                    findMinAdapter(child);

                    earlyAdapter[parent][0] += earlyAdapter[child][1];
                    earlyAdapter[parent][1] += Math.min(earlyAdapter[child][0], earlyAdapter[child][1]);
                }
            }
        }
    }
}
