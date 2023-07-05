import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
    static int N;

    // 중간 값을 어떻게 할까

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (maxPQ.size() == minPQ.size()) {
                maxPQ.offer(num);
            } else {
                minPQ.offer(num);
            }

            if (!maxPQ.isEmpty() && !minPQ.isEmpty()) {
                if (maxPQ.peek() > minPQ.peek()) {
                    int temp = minPQ.poll();
                    minPQ.offer(maxPQ.poll());
                    maxPQ.offer(temp);
                }
            }

            sb.append(maxPQ.peek() + "\n");
        }

        System.out.println(sb);
    }
}
