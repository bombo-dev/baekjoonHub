import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static StringBuilder sb;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        sb = new StringBuilder();

        backTracking(0, 0, M);
        System.out.print(sb.toString());
    }

    static void backTracking(int start, int depth, int findDepth) {

        if(depth == findDepth) {
            for(int val : dq) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i < N; i++) {
            dq.offer(arr[i]);
            backTracking(i + 1, depth + 1, findDepth);
            dq.removeLast();
        }
    }
}