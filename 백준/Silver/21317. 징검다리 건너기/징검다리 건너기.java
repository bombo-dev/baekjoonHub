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
        private int N;
        private int K;
        private Rock[] rocks;
        private StringTokenizer st;
        private final int MAX = 100000;
        private int result = MAX;

        public void solve() throws IOException {
            // N이 20
            // dp 를 돌리면서
            // 이전 값의 최대 값으로 확인

            N = stoi(br.readLine()); // N == 5

            rocks = new Rock[N];

            for (int i = 1; i < N; i++) {
                st = getStringTokenizer();
                int smallJump = stoi(st.nextToken());
                int largeJump = stoi(st.nextToken());

                rocks[i] = new Rock(smallJump, largeJump);
            }

            K = stoi(br.readLine());

            jump(1, 0, false);
            System.out.print(result);
        }

        private void jump(int start, int energy, boolean isJump) {
            if (start == N) {
                result = Math.min(result, energy);
            }

            if (start >= N) {
                return;
            }

            jump(start + 1, energy + rocks[start].smallJump, isJump);
            jump(start + 2, energy + rocks[start].largeJump, isJump);
            if (!isJump) {
                jump(start + 3, energy + K, true);
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

    private static class Rock {
        int smallJump;
        int largeJump;

        public Rock(int smallJump, int largeJump) {
            this.smallJump = smallJump;
            this.largeJump = largeJump;
        }
    }
}
