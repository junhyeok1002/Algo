import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int safe_area = 0;
	static HashSet<Integer[]> combinations = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M
		String[] inputs = bf.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		// 사방 탐색
		int[] dr = {0, 0, -1, +1};
		int[] dc = {-1, +1, 0, 0};
		
		Deque<Node> queue = new LinkedList<>();
		HashSet<Node> virus = new HashSet<>();
		List<Node> blanks = new ArrayList<>();
	
		int[][] map = new int[N][M];
		
		for( int i = 0 ; i < N; i++ ) {
			String[] line = bf.readLine().split(" ");
			for( int j = 0; j < M ; j++ ) {
				map[i][j] = Integer.parseInt(line[j]);
				
				// safe_area 세기
				if( map[i][j] == 0) {
					safe_area++;
					blanks.add(new Node(i,j));
				}
				
				// 바이러스를 셋에 넣어두기
				if( map[i][j] == 2 ) virus.add(new Node(i,j));
			}
		}
		
//		for( int i = 0 ; i < N; i++ ) {
//			for( int j = 0; j < M ; j++ ) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		

		Combination(new Integer[3], new boolean[safe_area], 0);
		int max_safe_num = -1;
		
		for(Integer[] combi : combinations) {
//			System.out.println(Arrays.toString(combi));
			
			int safe_num = safe_area - 3; // 벽을 3개 세웠으므로 3개 빼
			// 방문 배열 
			boolean[][] visited = new boolean[N][M];
			for(Integer n : combi) {
				visited[blanks.get(n).i][blanks.get(n).j] = true;
			}
			
			// 초기 queue에 바이러스 넣기
			for(Node v : virus) {
				queue.offerFirst(v);
				visited[v.i][v.j] = true;
			}
			
			while(!queue.isEmpty()) {
				Node node = queue.pollLast();
//				node.PrintNode();	
				
				for(int k = 0; k<4;k++) {
					int new_r = node.i + dr[k];
					int new_c = node.j + dc[k];
					
					if(0 <= new_r && new_r < N &&
					   0 <= new_c && new_c < M &&
					   !visited[new_r][new_c] &&
					   map[new_r][new_c] == 0
					) {
	
						visited[new_r][new_c] = true;
						safe_num--;
						queue.offerFirst(new Node(new_r, new_c));
						
					}
				}
			}
			max_safe_num = Math.max(max_safe_num, safe_num);
		}
		System.out.println(max_safe_num);
	}
	// 조합하기
	private static void Combination(Integer[] out, boolean[] visited, int depth) {
		// safe area 개수 중에서 3개(combi num)를 뽑아야 함
		if( depth == 3 ) {
			combinations.add(out.clone());
//			System.out.println(Arrays.toString(out));
			return;
		}
		
		
		for(int i = 0; i < safe_area; i++) {
			if (depth > 0 && out[depth-1] > i) continue;
			
			if( !visited[i] ) {
				visited[i] = true;
				out[depth] = i;
				Combination(out, visited, depth+1);
				visited[i] = false;
			}
		}
		
		
	}
}
class Node{
	int i;
	int j;
	
	Node(){};
	Node(int i, int j){
		this.i = i;
		this.j = j;
	}
	
	void PrintNode() {
		System.out.println("i : "+ i + ", j : "+j);
	}
}