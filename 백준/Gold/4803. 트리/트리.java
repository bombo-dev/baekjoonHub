import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int count;
    static boolean isCycle;
    static int caseNumber;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            caseNumber++;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            graph.clear();
            visited = new boolean[N + 1];
            count = 0;
            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph.get(start).add(end);
                graph.get(end).add(start);
            }
            // 간선의 개수가 트리의 원소보다 많다는 건 사이클이 존재
            // 1 1 은 경로가 한 개지만 트리는 아니다.
            // visited 를 초기화 하고 bfs 돌리면서 , 똑같은 걸 만나면, count X
            solve();
            print();
        }
        System.out.print(sb.toString());
    }

    private static void solve() {
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                isCycle = false;
                bfs(i);
                if (!isCycle) {
                    count++;
                }
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int node = 0;
        int edge = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            node++;

            for (int end : graph.get(cur)) {
                edge++;

                if (!visited[end]) {
                    q.offer(end);
                    visited[end] = true;
                }
            }
        }

        if (edge / 2 >= node) {
            isCycle = true;
        }
    }

    private static void print() {
        if (count == 0) {
            sb.append("Case ").append(caseNumber).append(": ");
            sb.append("No trees.").append("\n");
        } else if (count == 1) {
            sb.append("Case ").append(caseNumber).append(": ");
            sb.append("There is one tree.").append("\n");
        } else {
            sb.append("Case ").append(caseNumber).append(": ");
            sb.append("A forest of ");
            sb.append(count).append(" trees.").append("\n");
        }
    }
}
