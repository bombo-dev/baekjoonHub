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

        // 위치에 가로등을 두어서 가로등에 닿는 구간을 밝게 비추는 행위
        // 높이는 제한이 없음.

        // 굴다리의 길이가 10만
        // 가로등의 개수가 10만
        // M * 주어진 높이 + N
        // 완탐으로 가능
        // 여기서 주어진 높이를 이분 탐색으로 찾는다면

        // 먼저 첫 번째 값은 0 보다 작아야 함.
        // 그리고 그 값의 다음 값이 다음에 오는 값보다 같거나 커야함
        // 마지막 값은 이후의 값이 굴다리의 거리보다 커야 함.

        private int N;
        private int T;
        private int[] positions;

        public void solve() throws IOException {
            N = stoi(br.readLine());

            T = stoi(br.readLine());
            positions = new int[T];
            StringTokenizer st = getStringTokenizer();

            for (int i = 0; i < T; i++) {
                positions[i] = stoi(st.nextToken());
            }

            int result = binarySearch();
            System.out.print(result);
        }

        private int binarySearch() {
            int result = 0;
            int start = 1;
            int end = N;

            while (start <= end) {
                int mid = (start + end) / 2;

                if (!isFull(mid)) {
                    start = mid + 1;
                } else {
                    result = mid;
                    end = mid - 1;
                }
            }

            return result;
        }

        private boolean isFull(int height) {

            int last = 0;

            for (int i = 0; i < T; i++) {
                if (positions[i] - height <= last) {
                    last = positions[i] + height;
                } else {
                    return false;
                }
            }

            return last >= N;
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
