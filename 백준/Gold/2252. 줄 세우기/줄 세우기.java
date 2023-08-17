import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int[] inDegree;
        private boolean[] visited;
        private List<List<Integer>> graph = new ArrayList<>();
        private Queue<Integer> topologyQueue = new LinkedList<>();
        private int N;
        private int M;

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
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
                    topologyQueue.offer(i);
                }
            }

            var sb = new StringBuilder();
            topologySort(sb);
            System.out.print(sb);
        }

        private void topologySort(StringBuilder sb) {
            while (!topologyQueue.isEmpty()) {
                var index = topologyQueue.poll();
                sb.append(index).append(" ");

                for (var next : graph.get(index)) {

                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        topologyQueue.offer(next);
                    }
                }
            }
        }
    }
}
