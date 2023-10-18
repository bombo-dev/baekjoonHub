import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int T;
        private int[][] graph = new int[26][26];

        public void solve() throws IOException {
            N = stoi(br.readLine());

            for (int i = 0; i < 26; i++) {
                Arrays.fill(graph[i], (int) 1e9);
            }

            for (int i = 0; i < 26; i++) {
                graph[i][i] = 0;
            }

            for (int i = 0; i < N; i++) {
                String node = convertNode(br.readLine());
                int start = alphaToInt(node.charAt(0));
                int end = alphaToInt(node.charAt(1));
                graph[start][end] = 1;
            }

            for (int k = 0; k < 26; k++) {
                for (int i = 0; i < 26; i++) {
                    for (int j = 0; j < 26; j++) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }

            T = stoi(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < T; i++) {
                String node = convertNode(br.readLine());
                int start = alphaToInt(node.charAt(0));
                int end = alphaToInt(node.charAt(1));

                if (graph[start][end] != (int) 1e9) {
                    sb.append("T").append("\n");
                } else {
                    sb.append("F").append("\n");
                }
            }

            System.out.print(sb);
        }

        private String convertNode(String input) {
            input = input.replaceAll(" ", "");
            input = input.replaceAll("is", "");
            return input;
        }

        private int alphaToInt(char c) {
            return c - 'a';
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
