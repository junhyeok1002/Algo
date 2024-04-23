import java.util.*;
import java.io.*;

class Solution {
    static HashMap<String, Integer> hashmap = new HashMap<>();
    
    public String[] solution(String[] players, String[] callings) {

        for(int i = 0 ; i < players.length; i++){
            hashmap.put(players[i], i);
        }
        
        for(String name : callings){
            int callOrder = hashmap.get(name);
            if(callOrder == 0) continue; 
            
            players[callOrder] = players[callOrder-1];
            players[callOrder-1] = name;
            
            hashmap.put(players[callOrder-1], callOrder-1);
            hashmap.put(players[callOrder], callOrder);
        }
             
        return players;
    }
}