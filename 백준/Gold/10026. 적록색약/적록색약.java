import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int N;
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0, 0};
	
	static char[][] normal_map;
	static char[][] disabled_map;
	
	static boolean[][] visited_normal;
	static boolean[][] visited_disabled;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine());
		
		normal_map = new char[N][N];
		disabled_map = new char[N][N];
		
		visited_normal = new boolean[N][N];
		visited_disabled = new boolean[N][N];
		
		for(int i = 0 ; i < N; i ++) {
			String line = bf.readLine();
			for(int j = 0 ; j < N; j ++) {
				normal_map[i][j] = line.charAt(j);
				
				char disabled = line.charAt(j);
				if(disabled == 'G') disabled = 'R';
				disabled_map[i][j] = disabled;
			}
		}
		
		int normal_cnt = 0;
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				if(!visited_normal[i][j]) {
					normal_BFS(i,j);
					normal_cnt++;
				}
			}
		}
		
		int disabled_cnt = 0;
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				if(!visited_disabled[i][j]) {
					disabled_BFS(i,j);
					disabled_cnt++;
				}
			}
		}
		
		System.out.println(normal_cnt + " " + disabled_cnt);
	}
	private static void normal_BFS(int start_i, int start_j) {
		Deque<Node> queue = new LinkedList<>();
		
		queue.offerFirst(new Node(start_i,start_j, normal_map[start_i][start_j]));
		visited_normal[start_i][start_j] = false;
		
		while(!queue.isEmpty()){
			Node node = queue.pollLast();
			
			for(int k = 0; k < 4; k ++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];	
				
				if( 0 <= new_r && new_r < N && 0 <= new_c && new_c < N &&
					!visited_normal[new_r][new_c] &&
					normal_map[new_r][new_c] == normal_map[node.i][node.j]) {
					visited_normal[new_r][new_c] = true;
					queue.offerFirst(new Node(new_r,new_c,normal_map[new_r][new_c]));
				}
			}
		}
	}
	
	private static void disabled_BFS(int start_i, int start_j) {
		Deque<Node> queue = new LinkedList<>();
		
		queue.offerFirst(new Node(start_i,start_j, disabled_map[start_i][start_j]));
		visited_disabled[start_i][start_j] = false;
		
		while(!queue.isEmpty()){
			Node node = queue.pollLast();
			
			for(int k = 0; k < 4; k ++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];	
				
				if( 0 <= new_r && new_r < N && 0 <= new_c && new_c < N &&
					!visited_disabled[new_r][new_c] &&
					disabled_map[new_r][new_c] == disabled_map[node.i][node.j]) {
					visited_disabled[new_r][new_c] = true;
					queue.offerFirst(new Node(new_r,new_c,disabled_map[new_r][new_c]));
				}
			}
		}
	}
}
class Node{
	int i;
	int j;
	char ch ; 
	
	Node(){}
	Node(int i, int j, char ch){
		this.i = i;
		this.j = j;
		this.ch = ch;
	}
}