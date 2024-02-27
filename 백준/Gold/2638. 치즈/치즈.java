import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int[][] map;
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0, 0};
	
	static Deque<Node> cheese = new LinkedList<>();
	static int N,M, bfs_i =0, bfs_j = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new int[N][M];
		for(int i =0; i < N; i++) {
			line = bf.readLine().split(" ");
			for(int j =0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				
				if(map[i][j] == 1) cheese.add(new Node(i,j));
			}
		}
		
		int result_cnt = 0;
		while(!cheese.isEmpty()) {
			BFS();
//			PrintMap();
//			System.out.println();
			
			melting_cheese();
			result_cnt++;
		}
		

		System.out.println(result_cnt);
	}
	private static void melting_cheese() {
		List<Node> cheese_list = new ArrayList<>(cheese);
		for(Node node : cheese_list) {
			int cnt = 0;
			for(int k=0; k < 4; k++) {
				int nr = node.i + dr[k];
				int nc = node.j + dc[k];
				
				if( 0 <= nr && nr < N && 0 <= nc && nc < M) {
					if(map[nr][nc] == 2) cnt++;
				}
			}
			if(cnt >= 2) node.delete = true;
		}
		
		int size = cheese.size();
		for(int i =0; i < size; i++) {
			Node node = cheese.pollLast();
			if(!node.delete) cheese.offerFirst(node);
			else {
				map[node.i][node.j] =0;
			}
		}
	}
	
	
	private static void BFS() {
		Deque<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		visited[bfs_i][bfs_j] = true;
		map[bfs_i][bfs_j] = 2;
		queue.add(new Node(bfs_i,bfs_j));
		
		while(!queue.isEmpty()) {
			Node node = queue.pollLast();
			
			for(int k=0; k < 4; k++) {
				int nr = node.i + dr[k];
				int nc = node.j + dc[k];
				
				if( 0 <= nr && nr < N && 0 <= nc && nc < M &&
				    !visited[nr][nc] && map[nr][nc] != 1) {
					
					visited[nr][nc] = true;
					map[nr][nc] = 2;
					queue.offer(new Node(nr, nc));
				}
			}
		}
	}
	private static void PrintMap() {
		for(int i =0; i < N; i++) {
			for(int j =0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
class Node{
	int i;
	int j;
	boolean delete = false;
	
	Node(){};
	Node(int i, int j){
		this.i = i;
		this.j = j;
	}
}


