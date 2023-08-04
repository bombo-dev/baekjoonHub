import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int K;
        private int N;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            long[] arr = new long[K];

            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);

            long result = cutLan(arr);
            System.out.print(result);
        }

        private long cutLan(long[] arr) {
            long start = 0;
            long end = arr[K - 1] + 1;

            while (start < end) {
                long mid = (start + end) / 2;
                long total = 0;

                for (int i = 0; i < K; i++) {
                    total += arr[i] / mid;
                }

                if (total < N) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            return end - 1;
        }
    }
}
