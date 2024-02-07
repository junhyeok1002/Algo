import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	
	static Deque<Node> queue = new LinkedList<>();
	
	static int[] dh = {-1, +1, 0, 0, 0, 0, };
	static int[] dn = {0, 0, -1, +1, 0, 0};
	static int[] dm = {0, 0, 0, 0, -1, +1,};
	
	static int ret = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] inputs = bf.readLine().split(" ");
		
		int N = Integer.parseInt(inputs[1]);
		int M = Integer.parseInt(inputs[0]);
		int H = Integer.parseInt(inputs[2]);
		
		int[][][] map = new int[H][N][M];
		
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				String[] line = bf.readLine().split(" "); 
				for(int m= 0; m < M; m++) {
					map[h][n][m] = Integer.parseInt(line[m]);
					
					if(map[h][n][m] == 1) {
						queue.offerFirst(new Node(h,n,m,0));
					}
				}
			}
		}
		

		
		Node node = null;
		while(!queue.isEmpty()) {
			node = queue.pollLast();
            
			for(int k = 0; k < 6; k++) {
				int new_h = node.h + dh[k];
				int new_n = node.n + dn[k];
				int new_m = node.m + dm[k];
				
				if(0 <= new_h && new_h < H &&
				   0 <= new_n && new_n < N &&
				   0 <= new_m && new_m < M &&
				   map[new_h][new_n][new_m] == 0) {
					
					map[new_h][new_n][new_m] = 1;
					queue.offerFirst(new Node(new_h, new_n, new_m , node.order + 1));
				}
				
			}
		}
		if ( node != null)
			ret = node.order;
		
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				for(int m= 0; m < M; m++) {
					if(map[h][n][m] == 0) ret = -1;
				}
			}
		}
		
		System.out.println(ret);
		
	}
}
class Node{
	int n;
	int m;
	int h;
	int order;
	
	Node(){};
	Node(int h, int n, int m, int order){
		this.h = h;
		this.n = n;
		this.m = m;
		this.order = order;
	}
	
	void PrintNode(){
		System.out.println(h + " "+n + " "+m + " "+order);
	}
}