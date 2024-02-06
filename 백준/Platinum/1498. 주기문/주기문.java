import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringBuilder sb = new StringBuilder();
		
		int[] failure = new int[str.length()];
		int j = 0;
		failure[0] = 0;
		
		for(int i = 1; i< str.length();) {
			if(str.charAt(i) == str.charAt(j)) {
				failure[i] = ++j; 
				i++;
			}else if(j > 0) {
				j = failure[j-1];
				continue;
			}else {
				failure[i++] = 0;
			}
			
			if(i % (i-j) == 0 && i / (i-j) > 1)
				sb.append(i).append(" ").append(i / (i-j)).append("\n");
		}
		System.out.println(sb);
	}
}
