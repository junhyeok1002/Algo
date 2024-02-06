import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		StringBuilder sb = new StringBuilder();
		
		List<Integer> list = new ArrayList<>();
		boolean[] visited = new boolean[N];
		
		
		inputs = bf.readLine().split(" ");
		for(int i =0; i< inputs.length ; i++) {
			list.add(Integer.parseInt(inputs[i]));
		}
		
		// 정렬 후 반환하
		Collections.sort(list);
		Integer[] seq = list.stream().toArray(Integer[]::new);
		
		Print_Seq(seq, new int[M], visited, 0, M, sb);
		
		System.out.println(sb); 
	}
	private static void Print_Seq(Integer[] arr, int[] out, boolean[] visited, int depth, int Max, StringBuilder sb ) {
		if(depth == Max) {
			for(int i=0; i < out.length ; i++) {
				sb.append(out[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0 ; i < arr.length ; i++) {
			if(depth > 0 && arr[i] < out[depth-1]) continue;
			
			if(!visited[i]) {
				visited[i] = true;
				out[depth] = arr[i];
				Print_Seq(arr, out, visited, depth+1, Max, sb);
				visited[i] = false;
			}	
		}
	}
}
