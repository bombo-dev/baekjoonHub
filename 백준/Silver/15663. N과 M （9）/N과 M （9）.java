import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static boolean[] visited;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        backTracking(0, M, 0);
        System.out.print(sb.toString());
    }

    static void backTracking(int depth, int findDepth, int target) {

        if(depth == findDepth) {
            for(int i = 0; i < M; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int temp = 0;
        
        for(int i = 0; i < N; i++){
            if(!visited[i] && temp != arr[i]) {
                visited[i] = true;
                temp = arr[i];
                result[target] = arr[i];
                backTracking(depth + 1, findDepth, target + 1);
                visited[i] = false;
            }
        }
    }
}
