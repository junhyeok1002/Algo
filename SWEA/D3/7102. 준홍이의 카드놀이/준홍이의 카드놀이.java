import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = Integer.parseInt(bf.readLine());
         
        for(int t = 0; t < test_case ; t++) {
            sb.append("#"+(t+1));
            String[] line = bf.readLine().split(" ");
             
            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);
             
            int[] count = new int[N+M+1];
             
            int max = 0;
            for(int n = 1; n <= N; n++) {
                for(int m = 1; m <= M; m++) {
                    count[n+m]++;
                    max = Math.max(max, count[n+m]);
                }
            }
             
            for(int i = 1; i < count.length ; i++ ) {
                if(count[i] == max) sb.append(" "+i);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}