import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private Map<Character, Integer> map = new HashMap<>();

        public void solve() throws IOException {
            String input = br.readLine();
            settingPriority();

            System.out.print(convertToPostfix(input));
        }

        private String convertToPostfix(String input) {
            Deque<Character> stack = new ArrayDeque<>();
            StringBuilder postfix = new StringBuilder();

            for (char ch : input.toCharArray()) {
                if (Character.isLetterOrDigit(ch)) {
                    postfix.append(ch);
                } else if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && getPriority(ch) <= getPriority(stack.peek())) {
                        postfix.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }

            while (!stack.isEmpty()) {
                postfix.append(stack.pop());
            }

            return postfix.toString();
        }

        private int getPriority(char operator) {
            return map.getOrDefault(operator, 0);
        }

        private void settingPriority() {
            map.put('+', 1);
            map.put('-', 1);
            map.put('*', 2);
            map.put('/', 2);
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
