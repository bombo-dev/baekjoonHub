class Solution {
    public String solution(String new_id) {
        // 1. 대문자 -> 소문자
        new_id = new_id.toLowerCase();
        // 2. 소문자, 숫자, 빼기, 밑줄, 마침표
        new_id = new_id.replaceAll("[^a-z0-9\\-_.]", "");
        // 3. 마침표가 2번 이상 연속된 부분을 하나의 하나로 치환
        new_id = new_id.replaceAll("\\.+", ".");
        // 4. 첫 번째 또는 끝에 마침표 제거
        new_id = new_id.replaceAll("^\\.|\\.$", "");
        // 5. 빈 문자열이면 a를 대입
        if (new_id.isEmpty()) {
            new_id = "a";
        } else if(new_id.length() >= 16) { // 6. 16자 이상이면 15개 문자를 제외한 나머지 문자 제거
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("\\.$", "");
        }
        // 7. new_id의 길이가 2자 이하라면, 길이가 3이 될때까지 마지막 문자를 반복
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1);
        }
        
        return new_id;
    }
}