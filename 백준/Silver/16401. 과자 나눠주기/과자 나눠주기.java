import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int M; // 조카의 수 ,백만
        private int N; // 막대 과자의 수, 백만

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            int[] snacks = new int[N];

            // 과자의 길이는 10억 제한, 나누었을 때 21억을 초과하지 않으므로 int 가능
            st = new StringTokenizer(br.readLine());

            int maxSnack = 0;
            for (int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
                maxSnack = Math.max(maxSnack, snacks[i]);
            }

            int maxSnackSize = searchMaxSnackSize(snacks, maxSnack);
            System.out.print(maxSnackSize);
        }

        private int searchMaxSnackSize(int[] snacks, int maxSnack) {
            int start = 1;
            int end = maxSnack + 1;
            boolean isSearch = false;

            while (start < end) {
                int count = 0;

                int mid = (start + end) / 2;

                for (int i = 0; i < N; i++) {
                    count += snacks[i] / mid;
                }

                if (count < M) {
                    end = mid;
                } else {
                    isSearch = true;
                    start = mid + 1;
                }
            }

            if (!isSearch) {
                return 0;
            }

            return end - 1;
        }
    }
}
