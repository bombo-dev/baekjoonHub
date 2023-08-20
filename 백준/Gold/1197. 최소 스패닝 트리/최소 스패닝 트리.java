import java.io.*;
import java.util.*;

class KruskalNode implements Comparable<KruskalNode> {
    int from;
    int to;
    int distance;
    
    public KruskalNode(int from, int to, int distance){
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
    
    @Override
    public int compareTo(KruskalNode node){
        return this.distance - node.distance;
    }
}

public class Main {
    static int V;
    static int E;
    static int[] parent;
    static int weight;
    static ArrayList<KruskalNode> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        weight = 0;
        parent = new int[V + 1];
        for(int i = 1; i < V + 1; i++){
            parent[i] = i;
        }

        graph = new ArrayList<>();
        for(int i = 0; i < E; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine(),  " ");
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int distance = Integer.parseInt(st2.nextToken());
            graph.add(new KruskalNode(a, b, distance));
        }

        Collections.sort(graph);

        for(KruskalNode node : graph){
            if(findParent(node.from) == findParent(node.to))
                continue;
            else {
                unionParent(node.from, node.to);
                weight += node.distance;
            }
        }

        System.out.println(weight);
    }

    public static int findParent(int a){
        if(a != parent[a])
            parent[a] = findParent(parent[a]);

        return parent[a];
    }

    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);

        parent[b] = a;
    }
}