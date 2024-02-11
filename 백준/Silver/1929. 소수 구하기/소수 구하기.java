import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	
	static int N, M;
	static boolean[] eratos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] inputs = bf.readLine().split(" "); 
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		eratos = new boolean[M+1];
		eratos[0] = true;
		eratos[1] = true;
		
		// 2부터 M+1전까지 (M까지) 
		for(int i = 2; i < eratos.length ; i ++) {
			// 만약에 안지워져 있으면
			if(!eratos[i]) {
				// 첫 i는 건너 뛰고 2*i부터 i씩 더해가면서  
				for(int j = 2*i; j < eratos.length; j+=i) {
					eratos[j] = true;
				}	
			}	
		}
		
		for(int i = N; i <= M ; i ++) {
			if(!eratos[i]) sb.append( i + "\n");
			
		}
		
		System.out.println(sb);
	}

}