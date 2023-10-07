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
        private int[] arr;
        private int[] dp;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            StringTokenizer st = getStringTokenizer();
            arr = new int[N];
            dp = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = stoi(st.nextToken());
            }

            // dp
            for (int i = 0; i < N; i++) {
                dp[i] = 1;

                // 0 ~ i 이전 원소들 탐색
                for (int j = 0; j < i; j++) {

                    // j번째 원소가 i번째 원소보다 작으면서 i번째 dp가 j번째 dp+1 값보다 작은경우
                    if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;    // j번째 원소의 +1 값이 i번째 dp가 된다.
                    }
                }
            }

            // 최댓값(최대 길이) 탐색 
            int max = -1;
            for (int i = 0; i < N; i++) {
                max = Math.max(dp[i], max);
            }
            System.out.println(max);

        
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
