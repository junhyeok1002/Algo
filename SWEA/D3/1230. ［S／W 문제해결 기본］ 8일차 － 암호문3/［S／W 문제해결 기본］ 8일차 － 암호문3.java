import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String temp;
        int test_num = 1;
         
         
         
         
        // while((temp = bf.readLine()) != null) {
        while((temp = bf.readLine()) != null) {
            int N = Integer.parseInt(temp);
            List<Integer> secret = new LinkedList<>();
             
            String[] inputs = bf.readLine().split(" ");
            for(int i=0; i < N; i++) {
                secret.add(Integer.parseInt(inputs[i]));
            }
             
            int M = Integer.parseInt(bf.readLine());
            inputs = bf.readLine().split(" ");
            for(int i=0; i < inputs.length;) {
                if(inputs[i].equals("D")) {
                    int x = Integer.parseInt(inputs[i+1]);
                    int y = Integer.parseInt(inputs[i+2]);
                     
                    // y번까지 삭제
                    for(int k = 0; k < y; k++) secret.remove(x+1);
                     
                    i+= 3;
                }
                else if(inputs[i].equals("A")) {
                    int y = Integer.parseInt(inputs[i+1]);
                     
                    for(int k = 0; k < y; k++) 
                        secret.add(Integer.parseInt(inputs[i+2+k]));
                 
                    i+= 2 + y;
                }
                else if(inputs[i].equals("I")) {
                    int x = Integer.parseInt(inputs[i+1]);
                    int y = Integer.parseInt(inputs[i+2]);
                     
                    for(int k = 0; k < y; k++)
                        secret.add(x++, Integer.parseInt(inputs[i+3+k]));
                     
                    i+= 3 + y;
                }
            }
             
 
             
            sb.append("#"+(test_num++));
            for(int i = 0 ; i < 10; i ++) {
                sb.append(" "+secret.get(i));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}