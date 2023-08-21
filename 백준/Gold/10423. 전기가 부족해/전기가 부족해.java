import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N; // V
        private int M; // E
        private int K; // Electronics
        private int[] parents;
        private long total = 0;
        private List<Edge> edges = new ArrayList<>();
        private Map<Integer, Character> alphaMap = new HashMap<>();

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= 'z' - 'a'; i++) {
                alphaMap.put(i, (char) ('a' + i - 1));
            }

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            parents = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            st = new StringTokenizer(br.readLine());

            var first = Integer.parseInt(st.nextToken());
            for (int i = 1; i < K; i++) {
                int electronics = Integer.parseInt(st.nextToken());
                isUnion(first, electronics);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                var from = Integer.parseInt(st.nextToken());
                var to = Integer.parseInt(st.nextToken());
                var cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, cost));
            }

            edges.sort(Comparator.comparingInt((a) -> a.cost));

            for (Edge edge : edges) {
                if (isPossible(edge)) {
                    total += edge.cost;
                }
            }

            System.out.print(total);
        }

        private boolean isPossible(Edge edge) {
            return isUnion(edge.from, edge.to);
        }

        private boolean isUnion(int from, int to) {
            var newFrom = findParent(from);
            var newTo = findParent(to);

            if (newFrom == newTo) {
                return false;
            }
            parents[newTo] = newFrom;
            return true;
        }

        private int findParent(int node) {
            if (parents[node] != node) {
                parents[node] = findParent(parents[node]);
            }

            return parents[node];
        }
    }

    private static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
