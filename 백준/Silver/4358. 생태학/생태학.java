import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private TreeMap<String, Integer> species = new TreeMap<>();
        private int inputCount = 0;
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            while (true) {
                String input = br.readLine();

                if (input == null || input.isBlank()) {
                    break;
                }
                inputCount++;
                species.put(input, species.getOrDefault(input, 0) + 1);
            }

            for (String key : species.keySet()) {
                sb.append(key).append(" ");
                sb.append(String.format("%.4f", calcPercentage(key))).append("\n");
            }
            System.out.print(sb);
        }

        private double calcPercentage(String key) {
            double count = species.get(key);
            double value = (count / inputCount) * 100; 

            return Math.round(value * 10000) / 10000.0;
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
