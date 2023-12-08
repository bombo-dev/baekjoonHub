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
        private int[] perms;

        public void solve() throws IOException {
            N = stoi(br.readLine());

            StringTokenizer st = getStringTokenizer();
            perms = new int[N];

            for (int i = 0; i < N; i++) {
                perms[i] = stoi(st.nextToken());
            }

            // 1. A[i-1] < A[i] 가 가장 큰 감소 수열 찾기.
            int index = findDiffIndex();

            if (index == 0) {
                System.out.print(-1);
                return;
            }

            // 2. index 부터 마지막까지 A[index - 1] 보다 크면서 가장 작은 값의 위치 찾기
            int minValueIndex = findMinValueIndex(index);

            // 3. minValueIndex와 index - 1 을 스왑
            int temp = perms[index - 1];
            perms[index - 1] = perms[minValueIndex];
            perms[minValueIndex] = temp;

            // 4. index 부터 마지막까지 복사하고 reverse
            reverse(index);

            // 5. 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(perms[i])
                        .append(" ");
            }

            System.out.print(sb);
        }

        private void reverse(int index) {
            int size = perms.length - index;

            int[] temps = new int[size];

            for (int i = 0; i < size; i++) {
                temps[i] = perms[index + i];
            }

            for (int i = 0; i < size; i++) {
                perms[perms.length - 1 - i] = temps[i];
            }
        }

        private int findMinValueIndex(int index) {
            int standardValue = perms[index - 1];
            int diff = Integer.MAX_VALUE;
            int findIndex = -1;

            for (int i = index; i < perms.length; i++) {
                if (perms[i] > standardValue && perms[i] - standardValue < diff) {
                    diff = perms[i] - standardValue;
                    findIndex = i;
                }
            }

            return findIndex;
        }

        private int findDiffIndex() {
            int i = perms.length - 1;

            while (i > 0 && perms[i - 1] >= perms[i]) {
                i--;
            }

            return i;
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