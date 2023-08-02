import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        check = new boolean[N];

        DFS(N, M, 0, sb);
        System.out.print(sb);
    }

    public static void DFS(int N, int M, int depth, StringBuilder sb){
        if(depth == M){
            for(int i = 0; i < M; i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++){
            if(check[i] == false){
                check[i] = true;
                arr[depth] = i+1 ; // ?
                DFS(N, M, depth+1, sb);
                check[i] = false;
            }
        }
    }
}