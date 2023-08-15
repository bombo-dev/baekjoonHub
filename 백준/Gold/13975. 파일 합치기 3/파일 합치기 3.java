import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int T;

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var sb = new StringBuilder();
            T = Integer.parseInt(br.readLine());

            while (T-- > 0) {
                var maxSize = Long.MIN_VALUE;
                PriorityQueue<Long> fileQ = new PriorityQueue<>();
                var k = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());

                for (int i = 0; i < k; i++) {
                    fileQ.offer(Long.parseLong(st.nextToken()));
                }

                var total = 0L;
                while (fileQ.size() >= 2) {

                    var pre = fileQ.poll();
                    var next = fileQ.poll();

                    var sumFileSize = pre + next;
                    total += sumFileSize;

                    if (fileQ.isEmpty()) {
                        sb.append(total).append("\n");
                        break;
                    }

                    fileQ.offer(sumFileSize);
                }
            }
            System.out.print(sb);
        }
    }
}
