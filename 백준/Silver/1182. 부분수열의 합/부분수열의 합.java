import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int S;
    static boolean[] visited;
    static int[] value;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        value = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            backTracking(0, i + 1, 0, 0);
        }

        System.out.print(result);
    }

    public static void backTracking(int start, int findDepth, int preDepth, int sum) {

        if(findDepth == preDepth) {
            if(sum == S) {
                result++;
            }
            return;
        }

        for(int i = start; i < N; i++){
            visited[i] = true;
            backTracking(i + 1, findDepth, preDepth + 1, sum + value[i]);
            visited[i] = false;
        }
    }
}
