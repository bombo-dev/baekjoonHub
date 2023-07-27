class Solution {
    boolean solution(String s) {

        int sLength = s.length();
        s = s.toLowerCase();
        String removedP = s.replaceAll("p", "");
        int pLength = sLength - removedP.length();
        String removedY = removedP.replaceAll("y", "");
        int yLength = removedP.length() - removedY.length();
        
        return pLength == yLength;
    }
}