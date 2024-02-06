import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		// 1부터 3까지 중복 없이 M개를 고른 수열
		int[] in_sequence = new int[N];
		int[] out_sequence = new int[M];
		boolean[] visited = new boolean[N];
		
		for(int i=0; i < N; i++) {
			in_sequence[i] = i+1;
		}
		print_sequence(in_sequence,out_sequence, visited, 0, M, 0);
	}
	private static void print_sequence(int[] arr, int[] out, boolean[] visited, int depth , int M, int loop) {
		StringBuilder sb = new StringBuilder();
		
		if(depth == M) {
			for(int i = 0; i < out.length ;i++) {
				sb.append(out[i] + " ");
			}
			System.out.println(sb);
			return;
		}
		
		int temp_loop = loop +1;
		for(int i = 0; i < arr.length ;i++) {
			if(!visited[i] && loop++ <= i) {
				visited[i] = true;
				out[depth] = arr[i];
				print_sequence(arr, out, visited, depth+1, M, temp_loop);
				visited[i] = false;
			}
		}
	}
}