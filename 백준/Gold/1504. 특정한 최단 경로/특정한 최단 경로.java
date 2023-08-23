import java.io.*;
import java.util.*;

class DijkstraNode implements Comparable<DijkstraNode>{
    int direction;
    int distance;

    public DijkstraNode(int direction, int distance){
        this.direction = direction;
        this.distance = distance;
    }

    @Override
    public int compareTo(DijkstraNode n){
        return this.distance - n.distance;
    }
}

public class Main {
    static int V;
    static int E;
    static int[] d;
    static int INF;
    static ArrayList<ArrayList<DijkstraNode>> graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        INF = V * V * 1000;
        graph = new ArrayList<>();
        for(int i = 0; i < V + 1; i++)
            graph.add(new ArrayList<>());

        for(int i = 0; i < E; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int distance = Integer.parseInt(st2.nextToken());
            graph.get(a).add(new DijkstraNode(b, distance));
            graph.get(b).add(new DijkstraNode(a, distance));
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        int pass1 = Integer.parseInt(st2.nextToken());
        int pass2 = Integer.parseInt(st2.nextToken());

        int case1 = 0;
        case1 += dijkstra(1, pass1);
        case1 += dijkstra(pass1, pass2);
        case1 += dijkstra(pass2, V);
        int case2 = 0;
        case2 += dijkstra(1, pass2);
        case2 += dijkstra(pass2, pass1);
        case2 += dijkstra(pass1, V);

        if(case1 >= INF && case2 >= INF)
            System.out.println(-1);
        else
            System.out.println(Math.min(case1, case2));
    }

    public static int dijkstra(int start, int end){
        d = new int[V + 1];
        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>();
        pq.offer(new DijkstraNode(start, 0));
        Arrays.fill(d, INF);
        d[start] = 0;


        while(!pq.isEmpty()){
            DijkstraNode currentNode = pq.poll();
            int nowIndex = currentNode.direction;
            int nowDistance = currentNode.distance;

            if(d[nowIndex] < nowDistance)
                continue;

            for(DijkstraNode node : graph.get(nowIndex)){
                int cost = node.distance + nowDistance;

                if(d[node.direction] > cost){
                    d[node.direction] = cost;
                    pq.offer(new DijkstraNode(node.direction, cost));
                }
            }
        }
        return d[end];
    }
}