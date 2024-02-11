import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int N;
	static int[][] map;
	
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0, 0};
	
	static int whole_min = 201;
	static int whole_max = -1;
	
	static int top;
	static int mid;
	static int bottom;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		// 맵만들고 할
		map = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			String[] line = bf.readLine().split(" ");
			for(int j = 0 ; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				
				whole_min = Math.min(map[i][j], whole_min);
				whole_max = Math.max(map[i][j], whole_max);
			}
		}
		bf.close();
		
		top = whole_max-whole_min;
		bottom = 0;
		mid = (top + bottom) /2;
		
		// 성공하면 미드를 탑쪽으로 확산, 실패하면 미드를 바탐쪽으로 확산
		// 실패했는데 두개가 같아지면 

		
		WHILE : 
		while(true) {
			boolean mid_success = false;
			
			
			// mid의 경우의 수
			for(int min = whole_min; min+mid <= whole_max; min++) {
				boolean bfs = BFS(min, min+mid);
//				System.out.println(min+" "+ (min+mid) + bfs);
				if(bfs) {
					mid_success = true;
					break;
				}
			}
//			System.out.println("-                               -");
//			System.out.println(bottom +" "+ mid + " "+ top + " "+ mid_success);
//			System.out.println("=================================");
			
			// 성공한 경우, 미드를 바텀쪽으로 이분 탐색 
			if(mid_success) {
				top = mid;
				mid = (top + bottom) /2;
				
				if(bottom == mid ) {
					if(bottom == 0) top = 0;
					
					System.out.println(top);
					break WHILE;
				}
				
				
			}
			
			// 실패한 경우, 미드를 탑 쪽으로 이분 탐색 
			else {
				bottom = mid;
				mid = (top + bottom) /2;
				
				// 실패했는데 같아졌다는 것은, +1 더한 것이 값이라는 뜻 
				// => 왜냐? 그 전엔 +1차이 났다는 것인데 +1 차이가 나려면 그전에 2~3 차이에서  성공을 해야
				if(bottom == mid ) {
					System.out.println(mid+1);
					break WHILE;
				}
					
				
			}
			
		}
		
		
		
		
	}
	
	private static boolean BFS(int minimum, int maximum) {
		if(!(minimum <= map[0][0] && map[0][0] <= maximum)) return false;
		
		Deque<Node> queue = new LinkedList<>();
		
		// 큐에 첫번째 원소 넣기 
		queue.offerFirst(new Node(0,0));
		
		// 방문 배열 
		boolean[][] visited = new boolean[N][N];
		
		// 첫번쨰 큐에 삽
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.pollLast();
	//		node.PrintNode();
			
			if(node.i == N-1 && node.j == N-1) {
				return true;
			}
			
			for(int k = 0; k < 4; k++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];
				
				// 사방 탐색 안에 있고, 방문하지 않았고, 범위 안에 있으면 간다~
				if(0 <= new_r && new_r < N && 0 <= new_c && new_c < N && 
						!visited[new_r][new_c] && 
						minimum <= map[new_r][new_c] && map[new_r][new_c] <= maximum
						) {
					
					Node new_node = new Node(new_r, new_c);
					
					visited[new_r][new_c] = true;
					queue.offer(new_node);
				}	
			}
		}
		return false;
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
	
	public void PrintNode() {
		System.out.println("i : " + i + ",j : " + j);
	}

	@Override
	public String toString() {
		return "(" + i + ", " + j + ")";
	}
}