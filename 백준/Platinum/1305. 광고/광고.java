import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int adv_len = Integer.parseInt(bf.readLine());
		String adv_str = bf.readLine();
	
		int[] failure = new int[adv_len];
		failure[0] =0;
		int j =0;
		
		for(int i = 1; i < adv_len;) {
			if(adv_str.charAt(i) == adv_str.charAt(j)) {
				failure[i] = ++j;
				i++;
			}else if(j > 0) {
				j = failure[j-1];
			}else {
				failure[i] = 0;
				i++;
			}
		}
		System.out.println(adv_len - j);
	}
}
