import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int N;
        private int max = 0;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
                max = Math.max(max, arr[i]);
            }

            Set<Integer> sumSet = sumOne(arr);
            Arrays.sort(arr);

            findMaxPortionSum(arr, sumSet);
        }

        private Set<Integer> sumOne(int[] arr) {
            Set<Integer> sumList = new HashSet<>();

            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    sumList.add(arr[i] + arr[j]);
                }
            }

            return sumList;
        }

        private void findMaxPortionSum(int[] arr, Set<Integer> sumSet) {

            // x + y
            for (int i = arr.length - 1; i >= 0; i--) {
                for (int j = i; j >= 0; j--) {
                    int value = arr[i] - arr[j];

                    if (sumSet.contains(value)) {
                        System.out.print(arr[i]);
                        return;
                    }
                }
            }
        }
    }
}
