import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] first_line = bf.readLine().split(" ");
		int N = Integer.parseInt(first_line[0]);
		int M = Integer.parseInt(first_line[1]);
		int breakNum = Integer.parseInt(first_line[2]);
		
		boolean[][] map = new boolean[N][M];
		boolean[][][] visited = new boolean[N][M][breakNum+1];
		
		for(int i=0; i < N ; i++) {
			String[] line = bf.readLine().split("");
			for(int j=0 ; j < M; j++) {
				if(Integer.parseInt(line[j]) == 1) map[i][j] = true;
			}
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0,0,true,1,0));
		
		int[] dr = {-1, +1, 0, 0};
		int[] dc = {0, 0, -1, +1};
		
		int ret_num = -1;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.i == N-1 && node.j == M-1 && (ret_num == -1 || ret_num > node.order)) {
				ret_num = node.order ;
			}
			
			for(int k =0; k < 4; k ++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];
				
				if(0 <= new_r && new_r < N && 
				   0 <= new_c && new_c < M &&
				   !visited[new_r][new_c][node.breakN]) {
					
					
					// 벽이 아닌 경우 
					if(!map[new_r][new_c]) {
						visited[new_r][new_c][node.breakN] = true;
						queue.offer(new Node(new_r, new_c, !node.day, node.order +1, node.breakN));
					}
					// 벽이 아니고, 브레이크 기회가 있고 낮인 경우
					else if(node.breakN < breakNum && node.day) {
						visited[new_r][new_c][node.breakN] = true;
						queue.offer(new Node(new_r, new_c, !node.day, node.order +1, node.breakN+1));
					}
					
					// 벽이 아니고, 브레이크 기회가 있고 밤인 경우 => 하루 기다리기
					else if(node.breakN < breakNum && !node.day) {
						queue.offer(new Node(node.i, node.j, !node.day, node.order +1, node.breakN));
					}
				}
			}
		}
		System.out.println(ret_num);
	}
}
class Node{
	int i;
	int j;
	boolean day;
	int order;
	int breakN;
	
	Node(){};
	Node(int i, int j, boolean day, int order, int breakN){
		this.i = i;
		this.j = j;
		this.day = day;
		this.order = order;
		this.breakN = breakN;
	}
	
	void PrintNode() {
		System.out.println(	"i: "+i+
							", j: "+j+
							", day: "+day+
							", order: "+order +
							", breakN: "+breakN);
	}
}