import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader로 입력받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder prt = new StringBuilder();

        String input;
        while(true) {
            input = bf.readLine();
            if(input.equals(".")) break;
            List<Integer> div_num = new ArrayList<>();
            prt.append(longest_expon(input, div_num)+"\n");
        }

        prt.deleteCharAt(prt.length() -1 );

        System.out.println(prt);
        prt.setLength(0);
        bf.close();
    }
    private static int longest_expon(String input, List<Integer> div_num) {
        int[] failure = new int[input.length()];

        int j = 0;
        failure[0] = j;

        for(int i = 1; i < input.length() ;) {

            if(input.charAt(j) == input.charAt(i)) {
                failure[i] = ++j;
                i++;
            }else if(j > 0) {
                j = failure[j-1];
            }else{
                failure[j] = 0;
                i++;
            }
        }

        if (input.length() % (input.length() - (j)) != 0)
            return 1;
        return input.length() / (input.length() - (j));
    }
}

