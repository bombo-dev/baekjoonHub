import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    // 전위와 중위를 알 때, 후위를 어떻게 알아낼 것인가.
    // 일단 첫 부모는 전위의 첫 번째 원소로 알 수 있다.
    // 이어서, 전위의 좌측 원소가 시작점이 된다.
    // 중위의 입장에서 좌측부터 읽어들인다.
    // 분명하게 알 수 있는 것 부터 찾기

    // 부모로 부터 파생되는 좌측과 우측을 먼저 찾을 수 있다.
    // 그리고 그 부모의 좌측으로 부터 파생되는 좌측과 우측을 찾을 수 있다.

    // 전위의 처음이 루트
    // 전위의 루트 다음이 좌측
    // 후위의 마지막이 루트의 우측이다.
    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int T;
        private StringBuilder sb = new StringBuilder();
        private int[] prefixArr;
        private int[] infixArr;

        public void solve() throws IOException {
            T = stoi(br.readLine());

            while (T-- > 0) {
                // 배열 초기화

                int nodeCount = stoi(br.readLine());
                prefixArr = new int[nodeCount + 1];
                infixArr = new int[nodeCount + 1];

                StringTokenizer st = getStringTokenizer();

                for (int i = 0; i < nodeCount; i++) {
                    prefixArr[i] = stoi(st.nextToken());
                }

                st = getStringTokenizer();

                for (int i = 0; i < nodeCount; i++) {
                    infixArr[i] = stoi(st.nextToken());
                }

                postfix(0, 0, nodeCount);
                sb.append("\n");
            }

            System.out.print(sb);
        }

        private void postfix(int rootIndex, int start, int end) {

            int root = prefixArr[rootIndex];

            for (int i = start; i < end; i++) {

                if (infixArr[i] == root) {
                    // left는 prefix의 다음 rootIndex과 왼쪽 자식 트리의 rootIndex이다.
                    postfix(rootIndex + 1, start, i);

                    // right는 기존의 start와 end까지의 트리에서 왼쪽 자식 노드의 개수만큼 뺀 다음 노드가 부모 노드이다.
                    // 36548712 전위
                    // 56843127 중위
                    // 위의 입력 값에서 오른쪽 노드의 첫 헤드는 7이다.
                    // 이는 중위 노드에서 루트 값의 위치만큼 구하고 + 1 을 진행해주면 나온다.
                    // start가 0 i 가 4이고, rootIndex의 시작점이 0이므로 5번 인덱스가 헤드가 된다.
                    postfix(rootIndex + (i - start + 1), i + 1, end);
                    sb.append(root).append(" ");
                    return;
                }
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
}
