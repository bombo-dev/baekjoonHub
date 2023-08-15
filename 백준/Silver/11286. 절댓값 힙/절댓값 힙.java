import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private StringBuilder sb = new StringBuilder();
        private PriorityQueue<Number> pq = new PriorityQueue<>((a, b) -> {
            if (a.absNumber == b.absNumber) {
                return a.realNumber - b.realNumber;
            }

            return a.absNumber - b.absNumber;
        });

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                executeHeap(br);
            }
            System.out.print(sb);
        }

        private void executeHeap(BufferedReader br) throws IOException {
            int value = Integer.parseInt(br.readLine());
            if (value == 0) {
                if (pq.isEmpty()) {
                    sb.append("0").append("\n");
                } else {
                    sb.append(pq.poll().realNumber).append("\n");
                }
            } else {
                pq.offer(new Number(value));
            }
        }
    }

    private static class Number {
        int absNumber;
        int realNumber;

        public Number(int realNumber) {
            this.absNumber = Math.abs(realNumber);
            this.realNumber = realNumber;
        }
    }
}
