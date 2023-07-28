import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Delivery> deliveries = new ArrayList<>();
    static int N; // 마을 수
    static int C; // 트럭 용량
    static int M; // 정보 개수

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        int totalBoxCount = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int boxCount = Integer.parseInt(st.nextToken());
            deliveries.add(new Delivery(from, to, boxCount));
        }

        int[] maxBox = new int[N + 1]; // 마을이 가질 수 있는 최대의 박스 크기
        Arrays.fill(maxBox, C);

        deliveries.sort((a, b) -> {
            if (a.to == b.to) {
                return a.from - b.from;
            }
            return a.to - b.to;
        });

        for (int i = 0; i < M; i++) {
            Delivery delivery = deliveries.get(i);
            int from = delivery.from;
            int to = delivery.to;
            int boxCount = delivery.boxCount;

            int maxWeight = Integer.MAX_VALUE;

            for (int j = from; j < to; j++) {
                maxWeight = Math.min(maxWeight, maxBox[j]);
            }

            if (maxWeight >= boxCount) {
                for (int j = from; j < to; j++) {
                    maxBox[j] -= boxCount;
                }
                totalBoxCount += boxCount;
            } else {
                for (int j = from; j < to; j++) {
                    maxBox[j] -= maxWeight;
                }
                totalBoxCount += maxWeight;
            }
        }

        System.out.println(totalBoxCount);
    }

    static class Delivery {
        int from;
        int to;
        int boxCount;

        public Delivery(int from, int to, int boxCount) {
            this.from = from;
            this.to = to;
            this.boxCount = boxCount;
        }

        @Override
        public String toString() {
            return "Delivery{" +
                    "from=" + from +
                    ", to=" + to +
                    ", boxCount=" + boxCount +
                    "}\n";
        }
    }
}
