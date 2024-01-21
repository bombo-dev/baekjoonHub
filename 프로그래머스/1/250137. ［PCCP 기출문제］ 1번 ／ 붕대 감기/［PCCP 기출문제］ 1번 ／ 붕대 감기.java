import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        Player player = new Player(health, bandage[0], bandage[1], bandage[2]);
        
        int maxtime = attacks[attacks.length - 1][0];
        
        Deque<int[]> dq = new ArrayDeque<>();
        
        for (int[] attack : attacks) {
            dq.offerLast(attack);
        }
        
        for (int i = 1; i <= maxtime; i++) {
            if (dq.peekFirst()[0] == i) {
                int[] attack = dq.pollFirst();
                int damage = attack[1];
                if (player.dead(damage)) {
                    return -1;
                }
                continue;
            }
            
            player.heal();
        }

        
        return player.health;
    }
    
    private static class Player {
        int maxHealth;
        int health;
        int time;
        int maxTime;
        int perHeal;
        int bonus;
        
        public Player(int maxHealth, int maxTime, int perHeal, int bonus) {
            this.maxHealth = maxHealth;
            this.health = maxHealth;
            this.time = 0;
            this.maxTime = maxTime;
            this.perHeal = perHeal;
            this.bonus = bonus;
        }
        
        public void heal() {
            time++;
            if (health < maxHealth) {
                health += perHeal;    
            }
            
            if (time == maxTime) {
                health += bonus;
                time = 0;
            }
            
            if (health > maxHealth) {
                health = maxHealth;
            }
            
        }
        
        public boolean dead(int damage) {
            health -= damage;
            time = 0;
            
            if (health <= 0) {
                return true;
            }
            
            return false;
        }
    }
}