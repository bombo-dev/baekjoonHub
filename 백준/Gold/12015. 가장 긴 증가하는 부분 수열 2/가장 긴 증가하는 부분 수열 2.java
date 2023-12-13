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
        private int lis;
        private int[] lists;

        public void solve() throws IOException {
            N = stoi(br.readLine());

            arr = new int[N];

            StringTokenizer st = getStringTokenizer();

            for (int i = 0; i < N; i++) {
                arr[i] = stoi(st.nextToken());
            }

            lists = new int[N + 1];
            lists[0] = arr[0];
            lis = 1;

            // 10 20 5 7 8 9

            // 10 20
            // target 보다는 큰 인덱스를 찾는다.
            // 그리고 그 위치에 대치한다.
            // 만약 없다면 추가한다.

            // 1. target 보다 큰 인덱스가 없다면 0
            // 2. target 보다 큰 인덱스가 있다면 찾은 인덱스 + 1
            // 3. target 보다 작은 인덱스가 없다면 lis + 1

            for (int i = 1; i < N; i++) {
                int target = arr[i];
                int start = 0;
                int end = lis;

                if (target > lists[lis - 1]) {
                    lists[lis] = target;
                    lis++;
                    continue;
                }

                // 0 ~ lis 사이에
                while (start < end) {
                    int mid = (start + end) / 2;

                    if (target <= lists[mid]) {
                        end = mid;
                    } else if (target > lists[mid]) {
                        start = mid + 1;
                    }
                }

                lists[start] = target;
            }

            System.out.print(lis);
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
