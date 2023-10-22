import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private long total = 0;
        private int[] trees;
        private int[] adds;
        private PriorityQueue<Tree> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.add));

        public void solve() throws IOException {
            N = stoi(br.readLine());
            trees = new int[N];
            adds = new int[N];

            StringTokenizer st = getStringTokenizer();
            for (int i = 0; i < N; i++) {
                int number = stoi(st.nextToken());
                trees[i] = number;
            }

            st = getStringTokenizer();
            for (int i = 0; i < N; i++) {
                int add = stoi(st.nextToken());
                pq.offer(new Tree(i, trees[i], add));
            }

            for (int i = 0; i < N; i++) {
                Tree tree = pq.poll();
                total += tree.getRemainTree(i);
            }

            System.out.print(total);
        }

        private static class Tree {
            int index;
            int initNumber;
            int add;

            public Tree(int index, int initNumber, int add) {
                this.index = index;
                this.initNumber = initNumber;
                this.add = add;
            }

            public long getRemainTree(int day) {
                return ((long) add * day) + initNumber;
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
