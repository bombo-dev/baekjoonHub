import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }
    
    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int K;
        private int[] peer;
        private int[] cost;
        public void solve() throws IOException {
            int result = 0;
            StringTokenizer st = getStringTokenizer();
            N = stringToInt(st.nextToken());
            K = stringToInt(st.nextToken());
            
            peer = new int[N];
            cost = new int[N - 1];
            
            st = getStringTokenizer();
            
            for (int i = 0; i < N; i++) {
                peer[i] = stringToInt(st.nextToken());
            }
            
            for (int i = 1; i < N; i++) {
                cost[i - 1] = peer[i] - peer[i - 1];
            }
            
            Arrays.sort(cost);
            
            for (int i = 0; i < N - K; i++) {
                result += cost[i];
            }
            
            System.out.print(result);
        }
        
        private StringTokenizer getStringTokenizer(String delim) throws IOException {
            return new StringTokenizer(br.readLine(), delim);
        }
        
        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine());
        }
        
        private int stringToInt(String number) {
            return Integer.parseInt(number);
        }
    }
}