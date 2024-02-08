import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int cnt = 0;
	static int[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		visited = new int[N];
		
		DFS(0,0,0);
		System.out.println(cnt);
	}
	
	private static void DFS(int row, int col, int depth) {
		if(depth == N) {
			cnt++;
			return;
		}
		
		// depth행, j열 
		LOOP : 
		for(int j=0; j < N; j++) {	
			// 행에 겹치는 경우는 없으므로, 열에 겹치는 경우와 대각선고려 
			for(int v = 0 ; v < depth ; v++ ) {
				if (visited[v] == j || (Math.abs(visited[v] - j) == Math.abs(v - depth))) {
					continue LOOP;
				}
			}
		
			// 그게 아니면 노드를 방문하고 DFS
			visited[depth] = j;
			DFS(depth, j, depth+1);
		}
	}
}