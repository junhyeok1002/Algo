import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
		String[] inputs = bf.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		int breakNum = Integer.parseInt(inputs[2]);
		
		int[] dr = {-1, +1, 0 , 0};
		int[] dc = {0, 0, -1, +1};
		
		int shortest = -1;
		
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][breakNum+1];
		
		boolean[][] map = new boolean[N][M];
		
		for(int i=0; i < N; i++) {
			inputs = bf.readLine().split("");
			for(int j=0; j < M; j++) {
				 int temp = Integer.parseInt(inputs[j]);
				 if (temp == 1) map[i][j] = true;
				 
			}
		}

		// 첫번쨰 넣기
		queue.offer(new Node(0, 0, 1, 0));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			// node.PrintNode();
			
			if(node.i == N-1 && node.j == M-1) {
				if (shortest == -1 || shortest > node.order) shortest = node.order;
			}
			
			for(int k =0 ; k < 4 ; k ++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];
				
				// 사방에 인접한 것 중에, 도착하지 않은 녀석 확인
				if( 0 <= new_r && new_r < N &&
					0 <= new_c && new_c < M &&
					!visited[new_r][new_c][node.breakN]) {
					
					// 방문하지 않았고, 벽도 아닌 경우
					if(!map[new_r][new_c]) {
						visited[new_r][new_c][node.breakN] = true;
						queue.offer(new Node(new_r, new_c, node.order+1, node.breakN));
					}
					// 방문하지 않았고 벽이지만, 기회가 남아 있는 경우
					else if(node.breakN < breakNum) {
						visited[new_r][new_c][node.breakN] = true;
						queue.offer(new Node(new_r, new_c, node.order+1, node.breakN+1));
					}
				}	
			}
		}
		System.out.println(shortest);
	}
	
}

class Node{
	int i;
	int j;
	int order;
	int breakN;
	
	Node(){};
	Node(int i, int j, int order, int breakN){
		this.i = i;
		this.j = j;
		this.order = order;
		this.breakN = breakN;
	}
	
	public void PrintNode() {
		System.out.println( "i : " + i +
							", j : " + j +
							", order : " + order +
							", breakN : " + breakN);
	}
}
