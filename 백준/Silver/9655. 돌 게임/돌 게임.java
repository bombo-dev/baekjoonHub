import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }
    
    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        public void solve() throws IOException{
            int N = Integer.parseInt(br.readLine());
            if (N % 2 == 1) {
                System.out.println("SK");
            } else {
                System.out.println("CY");
            }
        }
    }
}