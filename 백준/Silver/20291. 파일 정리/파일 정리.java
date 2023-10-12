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
        private int N;
        private TreeMap<String, Integer> map = new TreeMap<>();

        public void solve() throws IOException {
            N = stoi(br.readLine());
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                int pointIndex = input.indexOf('.');
                String ext = input.substring(pointIndex + 1);

                putExt(ext);
            }

            StringBuilder sb = new StringBuilder();
            for (String key : map.keySet()) {
                sb.append(key).append(" ").append(map.get(key));
                sb.append("\n");
            }

            System.out.print(sb);
        }

        private void putExt(String ext) {
            if (!map.containsKey(ext)) {
                map.put(ext, 1);
            } else {
                map.put(ext, map.get(ext) + 1);
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
