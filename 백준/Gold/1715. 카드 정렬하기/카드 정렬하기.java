import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int N;
    static long total = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while (pq.size() >= 2) {
            int value1 = pq.poll();
            int value2 = pq.poll();

            pq.offer(value1 + value2);
            total += (value1 + value2);
        }

        System.out.print(total);
    }
}
