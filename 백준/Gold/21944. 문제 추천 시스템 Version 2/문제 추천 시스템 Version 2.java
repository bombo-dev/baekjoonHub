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
        private int P;
        private StringBuilder sb = new StringBuilder();
        private TreeMap<Integer, TreeSet<Question>> perQuestions = new TreeMap<>();
        private TreeSet<Question> questions = new TreeSet<>();
        private Map<Integer, Question> questionMap = new HashMap<>();

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                var st = new StringTokenizer(br.readLine());
                int questionNumber = Integer.parseInt(st.nextToken());
                int difficulty = Integer.parseInt(st.nextToken());
                int group = Integer.parseInt(st.nextToken());

                var question = new Question(group, questionNumber, difficulty);
                fillQuestion(question);
            }

            P = Integer.parseInt(br.readLine());

            for (int i = 0; i < P; i++) {
                var st = new StringTokenizer(br.readLine());
                var command = st.nextToken();

                executeCommand(command, st);
            }

            System.out.print(sb);
        }

        private void executeCommand(String command, StringTokenizer st) {
            if (command.equals("add")) {
                int questionNumber = Integer.parseInt(st.nextToken());
                int difficulty = Integer.parseInt(st.nextToken());
                int group = Integer.parseInt(st.nextToken());

                var question = new Question(group, questionNumber, difficulty);
                fillQuestion(question);
            } else if (command.equals("solved")) {
                int questionNumber = Integer.parseInt(st.nextToken());
                var question = questionMap.get(questionNumber);
                solveQuestion(question);
            } else if (command.equals("recommend")) {
                int group = Integer.parseInt(st.nextToken());
                int condition = Integer.parseInt(st.nextToken());
                recommendGroupQuestion(group, condition);
            } else if (command.equals("recommend2")) {
                int condition = Integer.parseInt(st.nextToken());
                recommendAnyQuestion(condition);
            } else if (command.equals("recommend3")) {
                int condition = Integer.parseInt(st.nextToken());
                int difficulty = Integer.parseInt(st.nextToken());
                recommendStandardDifficultyQuestion(condition, difficulty);
            }
        }

        private void solveQuestion(Question question) {
            perQuestions.get(question.group).remove(question);
            questions.remove(question);
            questionMap.remove(question.questionNumber);
        }

        private void recommendStandardDifficultyQuestion(int condition, int difficulty) {

            var standardQuestion = new Question(0, 0, difficulty);

            if (condition == 1) {
                var ceilingQuestion = questions.ceiling(standardQuestion);

                if (ceilingQuestion == null) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(ceilingQuestion.questionNumber).append("\n");
                }
            } else {
                var lowerQuestion = questions.lower(standardQuestion);

                if (lowerQuestion == null) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(lowerQuestion.questionNumber).append("\n");
                }
            }
        }

        private void recommendAnyQuestion(int condition) {
            if (condition == 1) {
                sb.append(questions.last().questionNumber).append("\n");
            } else {
                sb.append(questions.first().questionNumber).append("\n");
            }
        }

        private void recommendGroupQuestion(int group, int condition) {
            if (condition == 1) {
                sb.append(perQuestions.get(group).last().questionNumber).append("\n");
            } else {
                sb.append(perQuestions.get(group).first().questionNumber).append("\n");
            }
        }

        private void fillQuestion(Question question) {
            if (!perQuestions.containsKey(question.group)) {
                perQuestions.put(question.group, new TreeSet<>());
                perQuestions.get(question.group).add(question);
                questions.add(question);
                questionMap.put(question.questionNumber, question);
            } else {
                perQuestions.get(question.group).add(question);
                questions.add(question);
                questionMap.put(question.questionNumber, question);
            }
        }
    }

    private static class Question implements Comparable<Question> {
        int group;
        int questionNumber;
        int difficulty;

        public Question(int group, int questionNumber, int difficulty) {
            this.group = group;
            this.questionNumber = questionNumber;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Question q) {
            if (this.difficulty == q.difficulty) {
                return this.questionNumber - q.questionNumber;
            }

            return this.difficulty - q.difficulty;
        }
    }
}
