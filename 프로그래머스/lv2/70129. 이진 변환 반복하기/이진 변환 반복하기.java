class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int convertCount = 0;
        int removedZero = 0;
        
        while(!s.equals("1")) {
            int preLength = s.length();
            s = s.replaceAll("0", "");
            int postLength = s.length();
        
            removedZero += preLength - postLength;
            s = Integer.toString(postLength, 2);
            convertCount++;
        }
        
        answer[0] = convertCount;
        answer[1] = removedZero;
        
        return answer;
    }
}