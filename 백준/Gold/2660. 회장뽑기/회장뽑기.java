import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int chairman;
        private List<List<Integer>> graph = new ArrayList<>();
        private int chairmanCount = Integer.MAX_VALUE;
        private int[] friends;

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            chairman = Integer.parseInt(br.readLine());
            initGraph();
            chainNode(br);
            friends = new int[chairman + 1];

            for (int i = 1; i <= chairman; i++) {
                boolean[] visited = new boolean[chairman + 1];
                int count = bfs(i, visited);
                chairmanCount = Math.min(chairmanCount, count);
                friends[i] = count;
            }

            findChairmanCandidate();
        }

        private int bfs(int start, boolean[] visited) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{start, 0});
            visited[start] = true;
            int depth = 0;

            while (!q.isEmpty()) {
                int[] info = q.poll();
                int node = info[0];
                depth = info[1];

                for (int next : graph.get(node)) {

                    if (visited[next]) {
                        continue;
                    }

                    visited[next] = true;
                    q.offer(new int[]{next, depth + 1});
                }
            }

            return depth;
        }

        private void findChairmanCandidate() {
            int count = 0;
            StringBuilder candidateSb = new StringBuilder();
            for (int i = 1; i <= chairman; i++) {
                if (friends[i] == chairmanCount) {
                    count++;
                    candidateSb.append(i).append(" ");
                }
            }

            StringBuilder resultSb = new StringBuilder();
            resultSb.append(chairmanCount).append(" ").append(count);
            System.out.println(resultSb);
            System.out.print(candidateSb);
        }

        private void initGraph() {
            for (int i = 0; i < chairman + 1; i++) {
                graph.add(new ArrayList<>());
            }
        }

        private void chainNode(BufferedReader br) throws IOException {
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (u == -1 && v == -1) {
                    break;
                }

                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }
    }
}
