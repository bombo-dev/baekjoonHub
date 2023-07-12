import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int m;
    static int startingPoint = 0;
    static int endPoint = 0;
    static StringTokenizer st;
    static List<ArrayList<Edge>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static int[] distance;
    static int[] parent;
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        distance = new int[n + 1];
        Arrays.fill(distance, INF);

        parent = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, distance));
        }

        st = new StringTokenizer(br.readLine());
        startingPoint = Integer.parseInt(st.nextToken());
        endPoint = Integer.parseInt(st.nextToken());

        dijkstra(startingPoint);
        Stack<Integer> path = findPath(startingPoint, endPoint);

        System.out.println(distance[endPoint]);
        System.out.println(path.size());
        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }
        System.out.print(sb);
    }

    static void dijkstra(int startingPoint) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        distance[startingPoint] = 0;

        pq.offer(new Edge(startingPoint, 0));

        while (!pq.isEmpty()) {
            Edge polledEdge = pq.poll();
            int pollIndex = polledEdge.index;
            int pollDistance = polledEdge.distance;

            if (pollDistance > distance[pollIndex]) {
                continue;
            }

            for (Edge edge : graph.get(pollIndex)) {
                int endIndex = edge.index;
                int endDistance = edge.distance;
                int cost = distance[pollIndex] + endDistance;
                if (cost < distance[endIndex]) {
                    parent[endIndex] = pollIndex;
                    distance[endIndex] = cost;
                    pq.offer(new Edge(endIndex, cost));
                }
            }
        }
    }

    static Stack<Integer> findPath(int startingPoint, int endPoint) {
        Stack<Integer> stack = new Stack<>();
        int city = endPoint;

        while (city != startingPoint) {
            stack.push(city);
            city = parent[city];
        }
        stack.push(startingPoint);
        return stack;
    }

    static int findParent(int n) {
        if (n == parent[n]) {
            return n;
        }

        return findParent(parent[n]);
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Edge {
        int index;
        int distance;

        public Edge(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
