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
        private int N;
        private int M;
        private int k;
        private PriorityQueue<Costs> costs = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        private boolean[] visited;
        private List<ArrayList<Integer>> graphs = new ArrayList<>();

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            k = stoi(st.nextToken());
            int firstMoney = k;

            visited = new boolean[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graphs.add(new ArrayList<>());
            }

            st = getStringTokenizer();
            for (int i = 1; i <= N; i++) {
                int cost = stoi(st.nextToken());
                costs.offer(new Costs(i, cost));
            }

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                int start = stoi(st.nextToken());
                int end = stoi(st.nextToken());

                graphs.get(start).add(end);
                graphs.get(end).add(start);
            }

            while (!costs.isEmpty()) {
                Costs cost = costs.poll();

                if (!visited[cost.index]) {
                    k -= cost.cost;
                    bfs(cost.index);
                }
            }

            if (k >= 0) {
                System.out.print(firstMoney - k);
            } else {
                System.out.print("Oh no");
            }
        }

        private void bfs(int start) {
            Deque<Integer> dq = new ArrayDeque<>();
            dq.offerLast(start);
            visited[start] = true;

            while (!dq.isEmpty()) {
                int now = dq.pollFirst();

                for (int next : graphs.get(now)) {
                    if (!visited[next]) {
                        dq.offerLast(next);
                        visited[next] = true;
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

    private static class Costs {
        int index;
        int cost;

        public Costs(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
