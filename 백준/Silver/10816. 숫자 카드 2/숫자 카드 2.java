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
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        private int[] arr;

        public void solution() throws IOException {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                int target = Integer.parseInt(st.nextToken());
                int lowerBound = binarySearchLowerBound(target);
                int upperBound = binarySearchUpperBound(target);
                sb.append(upperBound - lowerBound).append(" ");
            }

            System.out.print(sb);
        }

        private int binarySearchLowerBound(int target) {
            int start = 0;
            int end = arr.length;

            while (start < end) {
                int mid = (start + end) / 2;

                if (arr[mid] >= target) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            return end;
        }

        private int binarySearchUpperBound(int target) {
            int start = 0;
            int end = arr.length;

            while (start < end) {
                int mid = (start + end) / 2;

                if (arr[mid] <= target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }
    }
}
