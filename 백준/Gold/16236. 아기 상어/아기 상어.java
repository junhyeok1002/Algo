import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int N;
	static int[][] map;
	
	static int[] dr = {0,0,-1,+1};
	static int[] dc = {-1,+1,0,0};
	
	static Deque<Node> queue = new LinkedList<>();
	
	static boolean[][][] visited;
	
	
	static Node baby_shark;
	static int baby_shark_size = 2;
	static int baby_shark_eat = 0;
	static int baby_shark_order = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		
		N = Integer.parseInt(bf.readLine());

		
		map = new int[N][N];
		
		int fish_cnt = 0;
		for(int i = 0; i < N ; i++) {
			String[] inputs = bf.readLine().split(" ");
			for (int j =0 ; j < N; j++) {
				map[i][j]= Integer.parseInt(inputs[j]);
				
				if(map[i][j] == 9) {
					baby_shark = new Node(i,j,0);
				}
				// 0도 9도 아니면 물고기 카운트 
				else if(map[i][j] != 0) {
					fish_cnt++;
				}
			}
		}
		
		visited = new boolean[N][N][fish_cnt+1];
		
		
		// 가까운 이동가능한 먹이가 있는 경우에만 
		Node node; 
		do {
			node = Near_Food_BFS();
		}
		while(node != null);
		
		System.out.println(baby_shark_order);
	}
	private static Node Near_Food_BFS() {
		Node nearlist_node = null;
		
		queue.offerFirst(baby_shark);
		visited[baby_shark.i][baby_shark.j][baby_shark_eat] = true;
		
		while(!queue.isEmpty()) {
			
			Node node = queue.pollLast();
			
			for(int k =0; k < 4; k++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];
				
				// 범위 안에 있고, 방문한 적이 없고, 크기가 작거나 같은 부분들만 간 다
				if(0 <= new_r && new_r < N && 0 <= new_c && new_c < N &&
						!visited[new_r][new_c][baby_shark_eat] &&
						map[new_r][new_c] <= baby_shark_size) {
					
					visited[new_r][new_c][baby_shark_eat] = true;
					Node new_node = new Node(new_r, new_c, node.order+1);
					queue.offerFirst(new_node);
					
					// 먹을 수 있는 경우는 우선순위 고려하
					if(0 < map[new_r][new_c] && map[new_r][new_c] < baby_shark_size) {
						// 가까운 노드가 비어있으면 지금 노드를 제일 가까운노드, 그게 아니면 비교해서갱신 
						if(nearlist_node == null) nearlist_node = new_node;
						else if(!nearlist_node.eat_prior(new_node)) nearlist_node = new_node;
					}
				}
			}
		}
		
		baby_shark_eat++;
		
		int temp = baby_shark_eat;
		int i = 2, level = 1;
		while(temp >= 0) { // 자기 단계만큼 먹으면 업그레이
			temp -= i;
			i++;
			level++;
		}
		baby_shark_size = level;
		
		// 위치, 사이즈, 먹은양, 초 갱신  
		if(nearlist_node != null) {
			map[baby_shark.i][baby_shark.j] = 0;
			
			baby_shark.i = nearlist_node.i;
			baby_shark.j = nearlist_node.j;
			baby_shark_order += nearlist_node.order;
			
			map[nearlist_node.i][nearlist_node.j] = 9;
		}
		
		return nearlist_node;
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
	
	void PrintNode() {
		System.out.print("i: " + i + ", j: " + j + ", order : " + order);
	}
	
	// 거리 우선순위, 위우선순위, 왼쪽 우선순
	boolean eat_prior(Node n) {
		if(this.order < n.order) return true;
		else if(this.order > n.order) return false;
		
		if(this.i < n.i) return true;
		else if(this.i > n.i) return false;
		
		if(this.j < n.j) return true;
		else if(this.j > n.j) return false;
		
		return false;
	}
}
