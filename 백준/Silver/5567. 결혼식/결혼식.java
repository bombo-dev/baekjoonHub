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
        private int M;
        private List<List<Integer>> graph = new ArrayList<>();
        private int count;

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            bfs();
            System.out.print(count);
        }

        private void bfs() {
            Queue<int[]> q = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];
            q.offer(new int[]{1, 0});
            visited[1] = true;

            while (!q.isEmpty()) {
                int[] friend = q.poll();
                int node = friend[0];
                int depth = friend[1];

                if (isInviteFriend(depth)) {
                    count++;
                }

                for (int next : graph.get(node)) {

                    if (visited[next]) {
                        continue;
                    }

                    visited[next] = true;
                    q.offer(new int[]{next, depth + 1});
                }
            }
        }

        private boolean isInviteFriend(int depth) {
            return depth >= 1 && depth <= 2;
        }
    }
}
