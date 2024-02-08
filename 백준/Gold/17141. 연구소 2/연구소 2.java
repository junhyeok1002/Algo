import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class Main {
	static int N, M;
	
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0, 0};
	
	static int safe_area = 0;
	static int mininum;
	
	static List<Node> virus = new ArrayList<>();
	static HashSet<Node[]> combis = new HashSet<>();
	static Deque<Node> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		int[][] map = new int[N][N];
		
		for(int i =0; i < N; i++) {
			inputs = bf.readLine().split(" ");
			for(int j =0; j < N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
				
				if(map[i][j] == 2) virus.add(new Node(i, j, 0));
				else if(map[i][j] == 0) safe_area++;
			}
		}
		
		mininum = N*N;
		Combination(new Node[M] , 0, new boolean[virus.size()]);
		
		
		for(Node[] out : combis) {
			// 큐에 처음에 다 넣기
			boolean[][] visited = new boolean[N][N];
			for(Node first : out) {
				queue.offerFirst(first);
				visited[first.i][first.j] = true;
			}
			
			int min = N*N;
			int safe = safe_area + virus.size() ;
			
			while(!queue.isEmpty()) {
				Node node = queue.pollLast();
				safe--; 
				
//				System.out.println(safe);
				
				if(safe == 0) {
//					System.out.println(node.order);
					min = Math.min(min, node.order);
					break;
				}
				
				
				
//				System.out.println(node.toString());
				
//				System.out.println(safe);
				
				for(int k = 0 ; k <4; k ++) {
					int new_r = node.i + dr[k];
					int new_c = node.j + dc[k];
					
					if(0 <= new_r && new_r < N && 
					   0 <= new_c && new_c < N &&
					   !visited[new_r][new_c] && 
					   map[new_r][new_c] != 1) {
						
						visited[new_r][new_c] = true;
						queue.offerFirst(new Node(new_r, new_c, node.order + 1));
					}
				}
			}
			
//			for(int i =0; i < N; i++) {
//				for(int j =0; j < N; j++) {
//					if(visited[i][j]) System.out.print(1 + " " );
//					else System.out.print(0 + " " );
//				}
//				System.out.println();
//			}
			mininum = Math.min(mininum, min);
		}
		if(mininum == N*N) System.out.println(-1);
		else System.out.println(mininum);
		
		
		
	}
	private static void Combination(Node[] out, int depth, boolean[] visited) { 
		
		if(M == depth) {
			combis.add(out.clone());
			return ;
		}
		
		for(int i = 0 ; i < virus.size(); i++) {
			if(depth > 0 && !out[depth-1].isPrior(virus.get(i)) ) continue;
			
			if(!visited[i]) {
				visited[i] = true;
				
				out[depth] = virus.get(i);
				Combination(out, depth + 1, visited);
				
				visited[i] = false;
			}
		}
		
	}
}
class Node{
	int i;
	int j;
	int order;
	
	Node(){}
	Node(int i, int j, int order){
		this.i = i;
		this.j = j;
		this.order = order;
	}
	
	boolean isPrior(Node n) {
		if(this.i < n.i) return true;
		else if(this.i > n.i) return false;
		
		// 같으면 j비교
		if(this.j < n.j) return true;
		else if(this.j > n.j) return false;
		
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return i + " " + j;
	}
}