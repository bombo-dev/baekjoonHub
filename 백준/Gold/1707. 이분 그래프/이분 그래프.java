import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<ArrayList<Integer>> graph;
    static int[] color;
    static final int RED = -1;
    static final int BLACK = 1;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-->0) {
            result = true;
            StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList<>();

            color = new int[V + 1];

            for(int i = 0; i < V + 1; i++) {
                graph.add(new ArrayList<>());
                color[i] = 0;
            }



            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            for(int i = 1; i < V + 1; i++) {
                if(color[i] == 0) {
                    dfs(i, RED);
                }
            }

            if(!result) {
                sb.append("NO").append("\n");
            } else {
                sb.append("YES").append("\n");
            }

        }
        System.out.print(sb);
    }

    static void dfs(int start, int colorValue){

        color[start] = colorValue;

        for(int end : graph.get(start)) {
            if(color[end] == colorValue) {
                result = false;
            }

            if(color[end] == 0) {
                dfs(end, colorValue * -1);
            }
        }
    }
}
