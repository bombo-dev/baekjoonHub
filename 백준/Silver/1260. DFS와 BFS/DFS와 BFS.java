import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N, M, K;
        private boolean[] visited;
        private StringBuilder sbD = new StringBuilder();
        private StringBuilder sbB = new StringBuilder();
        private ArrayList<Integer>[] graphs;

        // NlogN + ElogE + ElogE == 10000 + 150000 + 150000 
        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            K = stoi(st.nextToken());

            graphs = new ArrayList[N + 1];
            visited = new boolean[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graphs[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                int a = stoi(st.nextToken());
                int b = stoi(st.nextToken());
                graphs[a].add(b);
                graphs[b].add(a);
            }
            
            for (int i = 1; i <= N; i++) {
                Collections.sort(graphs[i]);
            }

            dfs(K);
            Arrays.fill(visited, false);
            bfs(K);

            System.out.println(sbD);
            System.out.print(sbB);
        }

        private void dfs(int start) {
            sbD.append(start).append(" ");
            visited[start] = true;

            for (int next : graphs[start]) {
                if (!visited[next]) {
                    dfs(next);
                }
            }
        }

        private void bfs(int start) {
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            visited[start] = true;
            dq.offer(start);

            while (!dq.isEmpty()) {
                int now = dq.pollFirst();
                sbB.append(now).append(" ");

                for (int next : graphs[now]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        dq.offerLast(next);
                    }
                }
            }
        }

        private StringTokenizer getStringTokenizer(String delim) throws IOException {
            return new StringTokenizer(br.readLine(), delim);
        }

        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine());
        }

        private int stoi(String number) {
            return Integer.parseInt(number);
        }
    }
}
