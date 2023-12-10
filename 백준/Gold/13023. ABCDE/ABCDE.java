import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N, M;
        private boolean[] visited;
        private boolean isEnd;
        private ArrayList<Integer>[] lists;

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());

            lists = new ArrayList[N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                lists[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                int a = stoi(st.nextToken());
                int b = stoi(st.nextToken());
                lists[a].add(b);
                lists[b].add(a);
            }

            for (int i = 0; i < N; i++) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }

            if (!isEnd) {
                System.out.print(0);
            }

        }

        // 0 -> 1 -> 2 -> 3 -> 4 -> 5
        private void dfs(int start, int depth) {
            if (isEnd) {
                return;
            }

            if (depth == 5) {
                System.out.print(1);
                isEnd = true;
                return;
            }

            for (int next : lists[start]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(next, depth + 1);
                    visited[next] = false;
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
