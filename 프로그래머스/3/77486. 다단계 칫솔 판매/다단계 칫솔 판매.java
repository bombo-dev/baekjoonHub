import java.util.*;

class Solution {
    
    // 칫솔 판매 시 자신의 부모에게 10%를 주고 나머지는 자신이 가진다.
    // 이익에는 받은 이익까지 포함한다.
    // 이때, 10% 금액이 1원 미만인 경우에는 자신이 다 가진다.
    
    // 칫솔 개당 100원
    // 받은 이익은 계속해서 전파된다.

    private Map<String, Seller> enrolls = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Seller center = new Seller("center");
        enrolls.put("center", center);
        
        for (int i = 0; i < enroll.length; i++) {
            String sellerName = enroll[i];
            Seller newSeller = new Seller(sellerName);
            enrolls.put(sellerName, newSeller);
        }
        
        for (int i = 0; i < referral.length; i++) {
            String referralName = referral[i];
            String sellerName = enroll[i];
            
            Seller findSeller = enrolls.get(sellerName);
            
            if (referralName.equals("-")) {
                findSeller.setReferral(center);
            } else {
                Seller referralSeller = enrolls.get(referralName);
                findSeller.setReferral(referralSeller);
            }
        }
        
        for (int i = 0; i < seller.length; i++) {
            String sellerName = seller[i];
            Seller findSeller = enrolls.get(sellerName);
            int salesMoney = 100 * amount[i];
            
            findSeller.sell(salesMoney);
        }
        
        int[] answer = new int[enroll.length];
        
        for (int i = 0; i < enroll.length; i++) {
            Seller findSeller = enrolls.get(enroll[i]);
            answer[i] = findSeller.getAmount();
        }
        
        return answer;
    }
    
    private static class Seller {
        private Seller referral;
        private String name;
        private int amount;
        
        public Seller(String name) {
            this.name = name;
            referral = null;
            amount = 0;
        }
        
        public void setReferral(Seller referral) {
            this.referral = referral;
        }
        
        public void sell(int money) {
            if (referral == null) {
                amount += money;
                return;
            }
            
            int giveMoney = (int)(money * 0.1);
            
            if (giveMoney < 1) {
                amount += money;
                return;
            }
            
            int acquireMoney = money - giveMoney;
            
            amount += acquireMoney;
            referral.sell(giveMoney);
        }
        
        public int getAmount() {
            return this.amount;
        }
        
        public boolean equals(Seller s) {
            if (s != null && s.name.equals(this.name)) {
                return true;
            }
            
            return false;
        }
        
        public int hashCode() {
            return this.name.hashCode();
        }
    }
}