import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private int M;
        private TreeSet<Question> questions = new TreeSet<>();
        private Map<Integer, Question> questionMap = new HashMap<>();
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            // 기본적으로는 난이도를 기점으로 보는 이진트리
            // 다만 똑같은 난이도 일 경우 가장 쉬운 건 문제 번호가 작은거, 어려운 건 문제 번호가 높은 것
            // 양방향 우선순위 큐를 써야함. 트리셋을 써야함.

            for (int i = 0; i < N; i++) {
                var st = new StringTokenizer(br.readLine());
                var number = Integer.parseInt(st.nextToken());
                var difficulty = Integer.parseInt(st.nextToken());
                questionMap.put(number, new Question(number, difficulty));
                questions.add(new Question(number, difficulty));
            }
            M = Integer.parseInt(br.readLine());

            recommendQuestion(br);
            System.out.print(sb);
        }

        private void recommendQuestion(BufferedReader br) throws IOException {
            for (int i = 0; i < M; i++) {
                var st = new StringTokenizer(br.readLine());
                var command = st.nextToken();
                executeCommand(command, st);
            }
        }

        private void executeCommand(String command, StringTokenizer st) {
            switch (command) {
                case "add":
                    var number = Integer.parseInt(st.nextToken());
                    var difficulty = Integer.parseInt(st.nextToken());
                    questionMap.put(number, new Question(number, difficulty));
                    questions.add(new Question(number, difficulty));
                    break;
                case "recommend":
                    var recommend = Integer.parseInt(st.nextToken());
                    recommend(recommend);
                    break;
                case "solved":
                    var questionNumber = Integer.parseInt(st.nextToken());
                    questions.remove(questionMap.get(questionNumber));
                    questionMap.remove(questionNumber);
            }
        }

        private void recommend(int recommend) {
            if (recommend == 1) {
                sb.append(questions.last().number).append("\n");
            } else {
                sb.append(questions.first().number).append("\n");
            }
        }

        private void clearAndFill(PriorityQueue<Question> pq) {
            var recommendQuestion = pq.poll();
            sb.append(recommendQuestion.number).append("\n");

            while (!pq.isEmpty()) {
                questions.add(pq.poll());
            }
        }
    }


    private static class Question implements Comparable<Question> {
        int number;
        int difficulty;

        public Question(int number, int difficulty) {
            this.number = number;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Question q) {
            if (this.difficulty == q.difficulty) {
                return this.number - q.number;
            }

            return this.difficulty - q.difficulty;
        }
    }
}
