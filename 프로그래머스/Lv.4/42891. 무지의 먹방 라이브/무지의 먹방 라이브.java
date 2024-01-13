import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    private int index;
    private int foodTime;
    
    Node(int index, int foodTime){
        this.index = index;
        this.foodTime = foodTime;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public int getFoodTime(){
        return this.foodTime;
    }
    
    @Override
    public int compareTo(Node node){
        return this.foodTime - node.foodTime;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        
        long summary = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < food_times.length; i++){
            summary += food_times[i];
            pq.offer(new Node(i + 1, food_times[i]));
        }
        
        if(summary <= k){
            return -1;
        }
        
        summary = 0; // 먹기 위해 사용한 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length;
        
        while(summary + ((pq.peek().getFoodTime() - previous) * length) <= k){
            int now = pq.poll().getFoodTime();
            summary += (now - previous) * length;
            length -= 1;
            previous = now;
        }
        
        List<Node> result = new ArrayList<>();
        
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        
        Collections.sort(result, new Comparator<Node>(){
            @Override
            public int compare(Node a, Node b){
                return a.getIndex() - b.getIndex();
            }
        });
        
        return result.get((int)((k - summary) % length)).getIndex();
    }
}