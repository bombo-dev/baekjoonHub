import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        
        List<Resume> resumes = storeResume(info);
        List<Query> queries = storeQuery(query);
        System.out.println(queries);
        
        return answer;
    }
    
    private List<Resume> storeResume(String[] info) {
        List<Resume> resumes = new ArrayList<>();
        
        for (String infoValue : info) {
            StringTokenizer st = new StringTokenizer(infoValue, " ");
            String language = st.nextToken();
            String type = st.nextToken();
            String year = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            resumes.add(new Resume(language, type, year, food, score));
        }
        
        return resumes;
    }
    
    private List<Query> storeQuery(String[] query) {
        List<Query> queries = new ArrayList<>();
        
        for (String queryValue : query) {
            String value = queryValue.replaceAll("and", "");
            StringTokenizer st = new StringTokenizer(value, " +");
            String language = st.nextToken();
            String type = st.nextToken();
            String year = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            queries.add(new Query(language, type, year, food, score));
        }
        
        return queries;
    }
    
    private static class Resume {
        private String language;
        private String type;
        private String year;
        private String food;
        private int score;
        
        public Resume(String language, String type, String year, String food, int score) {
            this.language = language;
            this.type = type;
            this.year = year;
            this.food = food;
            this.score = score;
        }
        
        @Override
        public String toString() {
            return "language : " + language
                + " , type : " + type 
                + " , year : " + year
                + " , food : " + food
                + " , score : " + score
                + "\n";
        }
    }
    
    private static class Query {
        private String language;
        private String type;
        private String year;
        private String food;
        private int score;
        
        public Query(String language, String type, String year, String food, int score) {
            this.language = language;
            this.type = type;
            this.year = year;
            this.food = food;
            this.score = score;
        }
        
        @Override
        public String toString() {
            return "language : " + language
                + " , type : " + type 
                + " , year : " + year
                + " , food : " + food
                + " , score : " + score
                + "\n";
        }
    }
}