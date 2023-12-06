import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private static final int NUMBER_SIZE = 10;
        private int N;
        private boolean[] visited = new boolean[NUMBER_SIZE];
        private char[] signs;
        private StringBuilder sb = new StringBuilder();
        private List<String> combinations = new ArrayList<>();

        public void solve() throws IOException {
            N = stoi(br.readLine());

            signs = new char[N];
            StringTokenizer st = getStringTokenizer();

            for (int i = 0; i < N; i++) {
                signs[i] = st.nextToken().charAt(0);
            }

            for (int i = 0; i < NUMBER_SIZE; i++) {
                visited[i] = true;
                sb.append(i);
                perm(0);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }

            Collections.sort(combinations);
            String max = combinations.get(combinations.size() - 1);
            String min = combinations.get(0);

            StringBuilder result = new StringBuilder();

            result.append(max)
                    .append("\n")
                    .append(min);

            System.out.print(result);
        }

        private void perm(int depth) {

            if (depth == N) {
                combinations.add(sb.toString());
                return;
            }

            for (int i = 0; i < NUMBER_SIZE; i++) {
                if (!visited[i] && isCheck(depth, i)) {
                    visited[i] = true;
                    sb.append(i);
                    perm(depth + 1);
                    sb.deleteCharAt(sb.length() - 1);
                    visited[i] = false;
                }
            }
        }

        private boolean isCheck(int depth, int number) {
            int lastNumber = Character.getNumericValue(sb.charAt(sb.length() - 1));

            if (signs[depth] == '>') {
                return lastNumber > number;
            }

            return lastNumber < number;
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
