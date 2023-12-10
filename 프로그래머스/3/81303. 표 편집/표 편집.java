import java.util.*;

class Solution {
    private Deque<Node> dq = new ArrayDeque<>();
    private Node cur;
    private int[] deletes;
    
    public String solution(int n, int k, String[] cmd) {
        Node head = new Node(0);
        
        deletes = new int[n];
        
        cur = head;
        for (int i = 1; i < n; i++) {
            Node node = new Node(i);
            cur.setNextNode(node);
            node.setPreNode(cur);
            
            cur = node;
        }
        
        cur = head;
        for (int i = 0; i < k; i++) {
            cur = cur.nextNode;
        }
        
        for (String commands : cmd) {
            String[] splitedCommand = commands.split(" ");
            char command = splitedCommand[0].charAt(0);
            int level = 0;
            switch(command) {
                case 'U':
                    level = Integer.parseInt(splitedCommand[1]);
                    up(level);
                    break;
                case 'D':
                    level = Integer.parseInt(splitedCommand[1]);
                    down(level);
                    break;
                case 'C':
                    delete();
                    break;
                case 'Z':
                    backUp();
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            if (deletes[i] == 1) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    
    private void up(int level) {
        for (int i = 0; i < level; i++) {
            cur = cur.preNode;
        }
    }
    
    private void down(int level) {
        for (int i = 0; i < level; i++) {
            cur = cur.nextNode;
        }
    }
    
    private void delete() {
        deletes[cur.index] = 1;
        
        if (cur.nextNode == null) {
            dq.offerLast(cur);
            cur = cur.preNode;
            cur.nextNode = null;
            return;
        }
        
        if (cur.preNode != null) {
            cur.preNode.nextNode = cur.nextNode;
            cur.nextNode.preNode = cur.preNode;
        } else {
            cur.nextNode.preNode = null;
        }
        dq.offerLast(cur);
        cur = cur.nextNode;
    }
    
    private void backUp() {
        Node backUpNode = dq.pollLast();
        deletes[backUpNode.index] = 0;
        
        if (backUpNode.preNode != null) {
            backUpNode.preNode.nextNode = backUpNode;
        }
        
        if (backUpNode.nextNode != null) {
            backUpNode.nextNode.preNode = backUpNode;
        }
    }
    
    private static class Node {
        int index;
        Node preNode;
        Node nextNode;
        
        public Node(int index) {
            this.index = index;
        }
        
        public void setNextNode(Node node) {
            this.nextNode = node;
        }
        
        public void setPreNode(Node node) {
            this.preNode = node;
        }
    }
}