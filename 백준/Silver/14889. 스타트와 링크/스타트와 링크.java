import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    // N은 무조건 짝수이다.
    // Sij, Sji 는 다르고, 팀을 절반으로 나누었을 때 최소값을 구해야 한다.
    // N명을 절반의 팀으로 나눌 수 있는 경우를 생각해보자.
    // N은 20명이고, 스코어는 100이하이다.
    // 10명씩 나누었을 때 최대 스코어는 1000이다. (int)

    // 그렇다면 팀을 절반으로 어떻게 나눌 것인가.
    // 또한 시너지 측면에서 살펴보자
    // N == 6, (1 2 3 / 4 5 6) 이 한팀 12 13 21 23 31 32 에 대한 시너지를 모두 더해주어야 하는가? O

    // 20C10 * 10C2 = 8천만 시간초과 2초 == 2억연산, 따라서 통과
    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int[][] scores;
        private boolean[] visited;
        private int scoreMinDiff = Integer.MAX_VALUE;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            scores = new int[N + 1][N + 1];
            visited = new boolean[N + 1];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = getStringTokenizer();
                for (int j = 0; j < N; j++) {
                    scores[i + 1][j + 1] = stoi(st.nextToken());
                }
            }

            teamCombi(1, 0);
            System.out.print(scoreMinDiff);
        }

        private void teamCombi(int start, int depth) {
            if (depth == N / 2) {
                calc();
                return;
            }

            for (int i = start; i <= N; i++) {
                visited[i] = true;
                teamCombi(i + 1, depth + 1);
                visited[i] = false;
            }
        }

        private void calc() {
            int linkSum = 0;
            int startSum = 0;

            for (int i = 1; i <= N - 1; i++) {
                for (int j = i + 1; j <= N; j++) {
                    if (visited[i] && visited[j]) {
                        linkSum += scores[i][j];
                        linkSum += scores[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        startSum += scores[i][j];
                        startSum += scores[j][i];
                    }
                }
            }

            int diff = Math.abs(linkSum - startSum);
            scoreMinDiff = Math.min(scoreMinDiff, diff);
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
