import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            var value = "";

            while ((value = br.readLine()) != null) {
                var inputBigInteger = new BigInteger(value);
                var base = BigInteger.ZERO;

                while (true) {
                    base = base.multiply(BigInteger.TEN)
                            .add(BigInteger.ONE);

                    if (base.mod(inputBigInteger).equals(BigInteger.ZERO)) {
                        sb.append(base.toString().length()).append("\n");
                        break;
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
