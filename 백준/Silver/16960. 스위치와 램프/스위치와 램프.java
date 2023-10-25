import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    // N(N - 1) 입력
    // N(N - 1) 연산

    // N = 2000, 400만 + 400만 = 800만
    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        private int[] lamp;
        private StringTokenizer st;
        private ArrayList<Integer>[] switchs;

        public void solve() throws IOException {
            st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());

            lamp = new int[M + 1];
            switchs = new ArrayList[N + 1];

            for (int i = 1; i < N + 1; i++) {
                switchs[i] = new ArrayList<>();
            }

            // 스위치와 램프 값 초기화
            for (int i = 0; i < N; i++) {
                st = getStringTokenizer();
                int count = stoi(st.nextToken());

                for (int j = 0; j < count; j++) {
                    int value = stoi(st.nextToken());
                    switchs[i + 1].add(value);
                    lamp[value]++;
                }
            }

            // 스위치를 돌면서 해당하는 값들을 빼기
            for (int i = 1; i < N + 1; i++) {
                // 계산하기 위한 lamp는 새로운 배열로 연산
                int[] calcLamp = lamp.clone();
                boolean isPossible = true;

                for (int link : switchs[i]) {
                    calcLamp[link]--;
                    if (calcLamp[link] == 0) {
                        isPossible = false;
                        break;
                    }
                }

                if (isPossible) {
                    System.out.print(1);
                    return;
                }
            }

            System.out.print(0);
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
