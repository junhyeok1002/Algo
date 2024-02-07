import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// DFS 와 BFS 공통, 인접 행렬과 시작노드
	static boolean[][] near_array;
	static int start_node ;
	
	// DFS용
	static boolean[] visited_dfs;
	
	// BFS용
	static boolean[] visited_bfs;
	static Deque<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] line = bf.readLine().split(" ");
		
		int node_num = Integer.parseInt(line[0]);
		int edge_num = Integer.parseInt(line[1]);
		start_node = Integer.parseInt(line[2]);
		
		near_array = new boolean[node_num+1][node_num+1];
		
		visited_dfs = new boolean[node_num+1];
		visited_bfs = new boolean[node_num+1];
		
		for(int e = 0; e < edge_num ; e++) {
			line = bf.readLine().split(" ");
			int node1 = Integer.parseInt(line[0]);
			int node2 = Integer.parseInt(line[1]);
			
			near_array[node1][node2] = true;
			near_array[node2][node1] = true;
		}
		
		//DFS
		DFS(start_node); sb.append("\n");
		
		//BFS
		visited_bfs[start_node] = true;
		queue.offerFirst(start_node);
		BFS();
		
		System.out.println(sb);
	}
	private static void DFS(int node) {
		sb.append(node+ " ");
		visited_dfs[node] = true;
		
		for(int i = 1; i < near_array[node].length ; i++) {
			if(near_array[node][i] && !visited_dfs[i]) {
				DFS(i);
			}
		}
	}
	
	private static void BFS() {
		while(!queue.isEmpty()) {
			int node = queue.pollLast();
			sb.append(node+ " ");
			
			for(int i = 1; i < near_array[node].length ; i++) {
				if(near_array[node][i] && !visited_bfs[i]) {
					visited_bfs[i] = true;
					queue.offerFirst(i);
				}
			}
		}
	}
}
