import java.util.*;

class Solution {
    
    private PriorityQueue<Task> tasks = new PriorityQueue<>();
    private Deque<Task> pausedTasks = new ArrayDeque<>();
    private List<String> answers = new ArrayList<>();
    
    public String[] solution(String[][] plans) {
        
        for (String[] plan : plans) {
            tasks.offer(new Task(plan[0], parseTime(plan[1]), Integer.parseInt(plan[2])));
        }
        
        int currentTime = tasks.peek().startTime;
        
        while (!tasks.isEmpty()) {
            Task currentTask = tasks.poll();
            
            while (!pausedTasks.isEmpty() && currentTime < currentTask.startTime) {
                Task pausedTask = pausedTasks.pollLast();
                
                if (isCompleteTask(currentTask, pausedTask, currentTime)) {
                    currentTime = currentTime + pausedTask.duration;
                    answers.add(pausedTask.name);
                } else {
                    int remainDuration = pausedTask.duration - (currentTask.startTime - currentTime);
                    pausedTasks.offerLast(new Task(pausedTask.name, currentTask.startTime, remainDuration));
                    currentTime = currentTask.startTime;
                }
            }
            
            currentTime = currentTask.startTime;
            
            if (!tasks.isEmpty()) {
                Task nextTask = tasks.peek();
                
                if (isCompleteTask(nextTask, currentTask, currentTime)) {
                    currentTime = currentTime + currentTask.duration;
                    answers.add(currentTask.name);
                } else {
                    int remainDuration = currentTask.duration - (nextTask.startTime - currentTask.startTime);
                    pausedTasks.offerLast(new Task(currentTask.name, nextTask.startTime, remainDuration));
                    currentTime = nextTask.startTime;
                }
                
            } else {
                answers.add(currentTask.name);
            }
        }
        
        
        while (!pausedTasks.isEmpty()) {
            answers.add(pausedTasks.pollLast().name);
        }
        
        return answers.stream().toArray(String[]::new);
    }
    
    private boolean isCompleteTask(Task after, Task past, int currentTime) {
        return currentTime + past.duration <= after.startTime;
    }
    
    private int parseTime(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        
        return hour * 60 + minute;
    }
    
    static class Task implements Comparable<Task> {
        String name;
        int startTime;
        int duration;
        
        public Task(String name, int startTime, int duration) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
        }
        
        @Override
        public int compareTo(Task t) {
            return this.startTime - t.startTime;
        }
        
        @Override
        public String toString() {
            return name + " " + duration;
        }
    }
}