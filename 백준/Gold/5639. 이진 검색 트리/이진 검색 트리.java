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

        public void solve() throws IOException {
            int root = stoi(br.readLine());
            Node node = new Node(root);

            while (true) {
                String input = br.readLine();

                if (input == null || input.isEmpty()) {
                    break;
                }

                int value = stoi(input);
                positioning(value, node);
            }

            StringBuilder sb = new StringBuilder();

            postfixTree(node, sb);
            System.out.print(sb);
        }

        private void positioning(int value, Node node) {
            if (value < node.number) {
                if (node.left == null) {
                    node.left = new Node(value);
                } else {
                    positioning(value, node.left);
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(value);
                } else {
                    positioning(value, node.right);
                }
            }
        }

        private void postfixTree(Node node, StringBuilder sb) {
            if (node == null) {
                return;
            }

            postfixTree(node.left, sb);
            postfixTree(node.right, sb);
            sb.append(node.number).append("\n");
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

    private static class Node {
        int number;
        Node parent;
        Node left;
        Node right;

        public Node(int number) {
            this.number = number;
        }
    }
}
