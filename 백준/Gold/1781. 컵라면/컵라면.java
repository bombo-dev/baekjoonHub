import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private PriorityQueue<Question> questions = new PriorityQueue<>((a, b) -> {
            if (a.deadLine == b.deadLine) {
                return b.noodle - a.noodle;
            }
            return a.deadLine - b.deadLine;
        });
        private PriorityQueue<Question> results = new PriorityQueue<>(Comparator.comparingInt(a -> a.noodle));

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                var st = new StringTokenizer(br.readLine());
                var deadLine = Integer.parseInt(st.nextToken());
                var noodle = Integer.parseInt(st.nextToken());
                questions.offer(new Question(deadLine, noodle));
            }

            var sum = 0L;
            while (!questions.isEmpty()) {

                var question = questions.poll();

                if (results.size() < question.deadLine) {
                    results.offer(question);
                    sum += question.noodle;
                } else if (results.size() == question.deadLine) {
                    if (results.peek().noodle < question.noodle) {
                        var pollQuestion = results.poll();
                        sum -= pollQuestion.noodle;
                        results.offer(question);
                        sum += question.noodle;
                    }
                }
            }
            System.out.print(sum);
        }

        private static class Question {
            int deadLine;
            int noodle;

            public Question(int deadLine, int noodle) {
                this.deadLine = deadLine;
                this.noodle = noodle;
            }
        }
    }
}
