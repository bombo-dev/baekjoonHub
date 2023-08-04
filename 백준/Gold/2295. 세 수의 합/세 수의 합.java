import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int N;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);
            findMaxTarget(arr);
        }

        // x + y + z = k;
        private void findMaxTarget(int[] arr) {

            for (int k = arr.length - 1; k >= 0; k--) {
                int target = arr[k];
                for (int x = 0; x < N; x++) {
                    int first = arr[x];
                    for (int y = x; y < N; y++) {
                        int z = target - (first + arr[y]);

                        if (z <= 0) {
                            break;
                        }

                        int find = Arrays.binarySearch(arr, z);

                        if (find >= 0) {
                            System.out.print(arr[k]);
                            return;
                        }
                    }
                }
            }
        }
    }
}
