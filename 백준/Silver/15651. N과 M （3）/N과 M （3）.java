import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		StringBuilder sb = new StringBuilder();
		
		int[] seq = new int[N];
		
		// 순열 만들
		for(int i = 0 ; i < N ; i++) {
			seq[i] = i+1;
		}
		
		Print_Seq(seq, new int[M], 0, M, sb);
		System.out.println(sb);
	}
	private static void Print_Seq(int[] seq, int[] out, int depth, int Max, StringBuilder sb) {
		
		
		if(depth == Max) {
			for(int i =0 ; i < out.length ; i++) {
				sb.append(out[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i < seq.length ; i++) {
			out[depth] = seq[i];
			Print_Seq(seq, out, depth+1, Max, sb);
		}
	}
}
