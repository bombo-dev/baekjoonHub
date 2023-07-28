import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer> orders = new ArrayList<>();
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int count = 0;

        for (int i = 0; i < K; i++) {
            orders.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> multiTap = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            if (multiTap.contains(orders.get(i))) {
                continue;
            }

            if (multiTap.size() < N) {
                multiTap.add(orders.get(i));
            } else {
                int lastUsed = -1;
                int idx = 0; // 뽑아야 하는 플러그 위치

                for (int j = 0; j < N; j++) {
                    int nextIdx = -1;
                    for (int k = i + 1; k < K; k++) {
                        if (multiTap.get(j) == orders.get(k)) {
                            nextIdx = k;
                            break;
                        }
                    }

                    if (nextIdx == -1) {
                        idx = j;
                        break;
                    } else if (nextIdx > lastUsed) {
                        lastUsed = nextIdx;
                        idx = j;
                    }
                }

                multiTap.set(idx, orders.get(i));
                count++;
            }
        }
        System.out.print(count);
    }
}
