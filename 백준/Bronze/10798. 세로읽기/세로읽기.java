import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public void solve() throws IOException {
            char[][] chars = new char[5][];
            int maxCol = 0;

            for (int i = 0; i < 5; i++) {
                String input = br.readLine();
                chars[i] = input.toCharArray();
                maxCol = Math.max(maxCol, input.length());
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < maxCol; i++) {
                for (int j = 0; j < 5; j++) {
                    try {
                        sb.append(chars[j][i]);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            
            System.out.print(sb);
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