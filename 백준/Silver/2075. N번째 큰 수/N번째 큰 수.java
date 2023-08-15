import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                var st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    pq.offer(Integer.parseInt(st.nextToken()));
                }
            }

            while (N-- > 1) {
                pq.poll();
            }

            System.out.print(pq.poll());
        }
    }
}
