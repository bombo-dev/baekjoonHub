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
            var st = getStringTokenizer();
            var total = 0L;
            while (st.hasMoreTokens()) {
                total += stol(st.nextToken());
            }

            System.out.print(total);
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
        
        private long stol(String number) {
            return Long.parseLong(number);
        }
    }
}


