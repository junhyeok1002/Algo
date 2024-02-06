import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		StringBuilder sb = new StringBuilder();
		
		List<Integer> list = new ArrayList<>();
		
		inputs = bf.readLine().split(" ");
		for(int i = 0; i< inputs.length ; i++) {
			list.add(Integer.parseInt(inputs[i]));
		}
		Collections.sort(list);
		
		Integer[] seq = list.stream().toArray(Integer[]::new);
		boolean[] visited = new boolean[N];
		Set<String> visited_str = new HashSet<>();
		
		Print_Seq(seq, new int[M], visited, 0, M, sb, visited_str);
		
		System.out.println(sb);
		
		
	}
	private static void Print_Seq(Integer[] arr, int[] out, boolean[] visited, int depth, int Max, StringBuilder sb, Set<String> visited_str) {
		if(depth == Max) {
			StringBuilder temp = new StringBuilder();
			for(int i =0 ; i < out.length ; i++) {
				temp.append(out[i] + " ");
			}
			
			if(visited_str.contains(temp.toString())) return;
			
			sb.append(temp + "\n");
			visited_str.add(temp.toString());
			return;
		}
		
		for(int i =0 ; i < arr.length ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				out[depth] = arr[i];
				Print_Seq(arr, out, visited, depth + 1, Max, sb, visited_str);
				visited[i] = false;
			}
		}
	}
}
