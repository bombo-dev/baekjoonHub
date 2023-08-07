import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int N;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            N = Integer.parseInt(br.readLine());
            int[] primeArr = getPrimeNumber();
            int count = getContinuesPrimeNumber(primeArr);
            System.out.print(count);
        }

        private int getContinuesPrimeNumber(int[] primeArr) {
            int count = 0;

            if (primeArr.length == 0) {
                return count;
            }

            int start = 0;
            int end = 0;
            int sum = primeArr[start];
            while (end < primeArr.length) {

                if (sum == N) {
                    count++;
                    sum -= primeArr[start];
                    start++;
                } else if (sum > N) {
                    sum -= primeArr[start];
                    start++;
                } else {
                    end++;

                    if (end != primeArr.length) {
                        sum += primeArr[end];
                    }
                }
            }

            return count;
        }

        private int[] getPrimeNumber() {
            boolean[] isNotPrime = new boolean[N + 1];
            isNotPrime[0] = true;
            isNotPrime[1] = true;
            for (int i = 2; i <= Math.sqrt(N); i++) {
                for (int j = i * 2; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }

            List<Integer> primeList = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                if (!isNotPrime[i]) {
                    primeList.add(i);
                }
            }

            return primeList.stream().mapToInt(a -> a).toArray();
        }
    }
}
