import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // r과 c는 1부터 시작
        // 양분의 초기화는 5
        // 하나의 칸에 여러개의 나무가 있을 수 있다. 이때, 어린 나무부터 양분을 먹는다.
        // 만약 땅에 양분이 부족하다면 못 먹은 나무는 사망

        // 봄 : 양분 섭취
        // 여름 : 죽은 나무 양분화 -> 죽은 나무 나이 / 2
        // 가을 : 나무 번식, 이 때, 나이가 5의 배수여야만 번식 가능
        // 겨울 : 땅에 양분 추가

        // 살아 있는 나무의 개수 구하기

        private int N; // 땅의 크기
        private int M; // 처음 심는 나무의 개수
        private int K; // 알아봐야 하는 연도

        private int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
        private int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};

        private int[][] earths; // 땅에 존재하는 양분
        private Deque<Tree> temps = new ArrayDeque<>(); // 성장 나무 보관소
        private Deque<Tree> foods = new ArrayDeque<>(); // 양분 보관소
        private int[][] foodInfos; // 주기적 양분 주기 보관소
        private Deque<Tree> trees = new ArrayDeque<>();

        private int result = 0;

        public void solve() throws IOException {
            StringTokenizer st = getStringTokenizer();
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            K = stoi(st.nextToken());

            earths = new int[N + 1][N + 1];
            foodInfos = new int[N + 1][N + 1];

            for (int[] arr : earths) {
                Arrays.fill(arr, -1);
            }

            for (int i = 1; i <= N; i++) {
                st = getStringTokenizer();
                for (int j = 1; j <= N; j++) {
                    foodInfos[i][j] = stoi(st.nextToken());
                    earths[i][j] = 5;
                }
            }

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                int x = stoi(st.nextToken());
                int y = stoi(st.nextToken());
                int age = stoi(st.nextToken());

                trees.offer(new Tree(x, y, age));
                result++;
            }

            for (int i = 0; i < K; i++) {
                play();
            }

            System.out.print(result);
        }

        private void play() {
            spring();
            summer();
            autumn();
            winter();
        }

        private void spring() {
            while (!trees.isEmpty()) {
                Tree tree = trees.pollFirst();

                if (tree.age <= earths[tree.x][tree.y]) {
                    earths[tree.x][tree.y] -= tree.age;
                    tree.age++;
                    temps.offer(tree);
                } else {
                    foods.offer(tree);
                    result--;
                }
            }

            while (!temps.isEmpty()) {
                trees.offer(temps.poll());
            }
        }

        private void summer() {
            while (!foods.isEmpty()) {
                Tree tree = foods.poll();
                earths[tree.x][tree.y] += (tree.age / 2);
            }
        }

        private void autumn() {
            Deque<Tree> newTrees = new ArrayDeque<>();

            while (!trees.isEmpty()) {
                Tree tree = trees.pollFirst();

                if (tree.age % 5 == 0) {
                    for (int i = 0; i < 8; i++) {
                        int nx = tree.x + dx[i];
                        int ny = tree.y + dy[i];

                        if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                            newTrees.add(new Tree(nx, ny, 1));
                            result++;
                        }
                    }
                }

                temps.offerLast(tree);
            }

            while (!newTrees.isEmpty()) {
                trees.offerFirst(newTrees.poll());
            }

            while (!temps.isEmpty()) {
                trees.offerLast(temps.poll());
            }
        }

        private void winter() {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    earths[i][j] += foodInfos[i][j];
                }
            }
        }

        private static class Tree {
            int x;
            int y;
            int age;

            public Tree(int x, int y, int age) {
                this.x = x;
                this.y = y;
                this.age = age;
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
