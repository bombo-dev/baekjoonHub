import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private Deque<Character> bracketStack = new ArrayDeque<>();
        private int result = 0;
        private int value = 1;

        public void solve() throws IOException {
            String brackets = br.readLine();

            for (int i = 0; i < brackets.length(); i++) {
                char bracket = brackets.charAt(i);

                if (bracket == ')' || bracket == ']') {
                    if (bracketStack.isEmpty()) {
                        result = 0;
                        break;
                    }
                }

                if (bracket == '(') {
                    value *= 2;
                    bracketStack.offerLast(bracket);
                    continue;
                } else if (bracket == '[') {
                    value *= 3;
                    bracketStack.offerLast(bracket);
                    continue;
                }

                if (bracket == ')') {
                    if (bracketStack.peekLast() == '[') {
                        result = 0;
                        break;
                    } else {
                        bracketStack.pollLast();
                        if (brackets.charAt(i - 1) == '(') {
                            result += value;
                        }
                        value /= 2;
                        continue;
                    }
                }

                if (bracket == ']') {
                    if (bracketStack.peekLast() == '(') {
                        result = 0;
                        break;
                    } else {
                        bracketStack.pollLast();
                        if (brackets.charAt(i - 1) == '[') {
                            result += value;
                        }
                        value /= 3;
                    }
                }
            }
            
            if (!bracketStack.isEmpty()) {
                result = 0;
            }

            System.out.print(result);
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
