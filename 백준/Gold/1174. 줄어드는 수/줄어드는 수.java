import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        private List<Long> numbers = new ArrayList<>();
        private Set<Long> checks = new HashSet<>();

        // 9^9 == 3억 8천
        public void solve() throws IOException {
            int target = stoi(br.readLine());
            findDecreaseNumber(0, 0);
            Collections.sort(numbers);
            try {
                System.out.print(numbers.get(target - 1));
            } catch (Exception e) {
                System.out.print(-1);
            }
        }

        private void findDecreaseNumber(long number, int index) {

            if (!checks.contains(number)) {
                numbers.add(number);
                checks.add(number);
            }

            if (index > 9) {
                return;
            }

            findDecreaseNumber(number * 10 + arr[index], index + 1);
            findDecreaseNumber(number, index + 1);
        }

        private StringTokenizer getStringTokenizer(String delim) throws IOException {
            return new StringTokenizer(br.readLine(), delim);
        }

        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine());
        }

        private int stoi(String number) {
            return Integer.parseInt(number);
        }
    }
}
