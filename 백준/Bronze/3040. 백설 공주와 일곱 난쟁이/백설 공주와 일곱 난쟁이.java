import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    // 9명의 난쟁이 중 7개의 난쟁이의 조합을 구한다.
    // 9C7 == 9C2 -> 약 36개의 조합이 나온다.
    // 36개의 조합에서 모든 수를 더해준다.
    // 7 * 36 == 252
    private static class Solution {
        private final int FIND_NUMBER = 7;
        private final int INPUT_NUMBER = 9;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int[] arr = new int[9];
        private StringBuilder sb = new StringBuilder();
        private List<List<Integer>> results = new ArrayList<>();
        private List<Integer> temps = new ArrayList<>();

        public void solve() throws IOException {
            for (int i = 0; i < INPUT_NUMBER; i++) {
                arr[i] = stoi(br.readLine());
            }

            findOak(0, 0);

            for (List<Integer> result : results) {
                int total = 0;
                for (int value : result) {
                    total += value;
                }

                if (total == 100) {
                    for (int value : result) {
                        sb.append(value).append("\n");
                    }
                    break;
                }
            }
            
            System.out.print(sb);
        }

        private void findOak(int start, int depth) {
            if (depth == FIND_NUMBER) {
                ArrayList<Integer> newList = new ArrayList<>(temps);
                results.add(newList);
                return;
            }

            for (int i = start; i < INPUT_NUMBER; i++) {
                temps.add(arr[i]);
                findOak(i + 1, depth + 1);
                temps.remove(temps.size() - 1);
            }
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
