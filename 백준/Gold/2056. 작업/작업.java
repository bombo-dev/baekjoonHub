import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private List<List<Integer>> graph = new ArrayList<>();
        private int[] inDegree;
        private int[] costs;
        private int[] updatedCost;

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            inDegree = new int[N + 1];
            costs = new int[N + 1];
            updatedCost = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                var st = new StringTokenizer(br.readLine());
                costs[i] = Integer.parseInt(st.nextToken());

                var precedingCount = Integer.parseInt(st.nextToken());

                for (int j = 0; j < precedingCount; j++) {
                    var preceding = Integer.parseInt(st.nextToken());
                    graph.get(preceding).add(i);
                    inDegree[i]++;
                }
            }

            var total = executeWork();
            System.out.print(total);
        }

        private int executeWork() {

            var works = noPrecedingWorks();

            var result = 0;

            while (!works.isEmpty()) {
                var now = works.poll();
                var index = now[0];
                var cost = now[1];

                result = Math.max(cost, result);

                for (var next : graph.get(index)) {
                    inDegree[next]--;
                    updatedCost[next] = Math.max(updatedCost[next], cost + costs[next]);

                    if (inDegree[next] == 0) {
                        works.offer(new int[]{next, updatedCost[next]});
                    }
                }
            }

            return result;
        }

        private Queue<int[]> noPrecedingWorks() {
            var q = new LinkedList<int[]>();

            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    q.offer(new int[]{i, costs[i]});
                }
            }

            return q;
        }
    }
}
