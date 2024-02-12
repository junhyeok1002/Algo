import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int N, M, Product;
	static char[][] map;
	static boolean[][][] visited;
	static Deque<Node> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		M = Integer.parseInt(inputs[0]);
		N = Integer.parseInt(inputs[1]);
		map = new char[N][M];
		visited = new boolean[N][M][32];
		
		Product = 0;
		
		for(int i=0;i < N;i++) {
			String line = bf.readLine();
			for(int j=0; j<M;j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'S') {
					queue.offerFirst(new Node(i,j,0,0));
					visited[i][j][0] = true;
				}
				else if(map[i][j] == 'X') {
					map[i][j] = Integer.toString(Product++).charAt(0);
				}
			}
		}
		
		BFS();
	}
	private static void BFS() {
		int[] dr = {0,0,-1,+1};
		int[] dc = {-1,+1,0,0};
		
		while(!queue.isEmpty()) {
			Node node = queue.pollLast();
//			node.PrintNode();
			
			if(node.bag == (1<<Product)-1 && map[node.i][node.j] == 'E') {
				System.out.println(node.order);
				return;
			}
			
			for(int k =0; k < 4; k++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];
				
				if(0 <= new_r && new_r < N && 0<= new_c && new_c < M &&
				 !visited[new_r][new_c][node.bag] && map[new_r][new_c] != '#') {
					
					// 열쇠에 도착했으면 
					if(map[new_r][new_c] != '.' && map[new_r][new_c] != 'S' && map[new_r][new_c] != 'E') {
						
						int product = Integer.parseInt(map[new_r][new_c] + "");
						
						// 안가지고 있으면 
						if( (node.bag & (1<<product)) != (1<<product)){
							visited[new_r][new_c][(node.bag | (1<<product))] = true;
							queue.offerFirst(new Node(new_r, new_c, node.order+1, (node.bag | (1<<product))));
						}
						// 물건을 이미 가지고 있으면 그대로 간다.
						else {
							visited[new_r][new_c][node.bag] = true;
							queue.offerFirst(new Node(new_r, new_c, node.order+1, node.bag));
						}
					}
					
					// 열쇠도 벽도 아닌 곳에 도착시 그대로 간다 
					else {
						visited[new_r][new_c][node.bag] = true;
						queue.offerFirst(new Node(new_r, new_c, node.order+1, node.bag));
					}
				}	
			}
		}
	}
}


class Node{
	int i;
	int j;
	int order;
	int bag;
	
	Node(){};
	Node(int i, int j ,int order, int bag){
		this.i = i;
		this.j = j;
		this.order = order;
		this.bag = bag;
	}
	
	void PrintNode() {
		System.out.println("i : " + i +", j : " + j +", order : " + order +", bag : " + Integer.toBinaryString(bag));
	}
}