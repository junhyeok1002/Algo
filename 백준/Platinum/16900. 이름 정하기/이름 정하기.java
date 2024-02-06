import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bf.readLine().split(" ");
        String str = inputs[0];
        long repeat_num = Integer.parseInt(inputs[1]);

        int[] failure = new int[str.length()];
        int i = 1;
        int j = 0;

        for(i = 1; i < str.length(); ){
            if(str.charAt(i) == str.charAt(j)){
                failure[i] = ++j;
                i++;
            }else if(j > 0){
                j = failure[j-1];
            }else{
                failure[i] = 0;
                i++;
            }
        }

        long last_failure = failure[str.length()-1];
        System.out.println(str.length() * repeat_num - (last_failure * (repeat_num-1)));
    }
}
