import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {

    int from;
    int to;
    int value;

    Node(int from, int to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    public int compareTo(Node n) {
        return this.value - n.value;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static int[] parent;
    static int[][] graph;
    static int N;
    static long total = 0;

    public static void main(String[] args) throws IOException {
        init();
        kruskal();
        System.out.print(total);
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        graph = new int[N][N];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        fillGraph();
        fillPq();
    }

    static void fillGraph() throws IOException {
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void fillPq() {
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                pq.offer(new Node(i + 1, j + 1, graph[i][j]));
            }
        }
    }

    static void kruskal() {
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int value = node.value;

            if(isUnion(from, to)) {
                total += value;
            }
        }
    }

    static int findParent(int a) {
        if(parent[a] != a) {
            parent[a] = findParent(parent[a]);
        }

        return parent[a];
    }

    static boolean isUnion(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a == b)
            return false;

        parent[b] = a;
        return true;
    }
}
