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
        private int[] answers = new int[11];
        private int total = 0;
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();

            for (int i = 1; i < 11; i++) {
                int value = stoi(st.nextToken());
                answers[i] = value;
            }

            findTotalSubmits(1, 0, 0, 0);
            System.out.print(total);
        }

        // 5 ^ 10 == 1천만
        // 10개의 수에 대해 1천만번 반복 == 1억
        // 하지만 연속된 수가 3번이상 나올 수 없으므로 1천만개는 제외 될 가능성이 높고
        // 미리 종료될 수 있다.
        private void findTotalSubmits(int index, int answerCount, int sameCount, int preSubmit) {

            if (sameCount == 3) {
                return;
            }

            if (index == 11) {
                if (answerCount < 5) {
                    return;
                } else {
                    total++;
                }
                return;
            }


            for (int i = 1; i <= 5; i++) {
                if (answers[index] == i) {
                    if (preSubmit == i) {
                        findTotalSubmits(index + 1, answerCount + 1, sameCount + 1, i);
                    } else {
                        findTotalSubmits(index + 1, answerCount + 1, 1, i);
                    }
                } else {
                    if (preSubmit == i) {
                        findTotalSubmits(index + 1, answerCount, sameCount + 1, i);
                    } else {
                        findTotalSubmits(index + 1, answerCount, 1, i);
                    }
                }
            }
        }

        private boolean isPossible() {
            char[] submits = sb.toString().toCharArray();
            int count = 1;
            for (int i = 0; i < submits.length - 1; i++) {
                if (count == 3) {
                    return false;
                }

                if (submits[i] == submits[i + 1]) {
                    count++;
                } else {
                    count = 1;
                }
            }

            return true;
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
