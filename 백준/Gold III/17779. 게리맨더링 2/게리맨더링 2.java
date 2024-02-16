import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int N;
	static int[][] map;
	static int[][] div;
	static boolean[][][] visited;
	static Deque<Node> queue = new LinkedList<>();
	static int min_gap = 2000000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N+1][N+1];
		
		for(int r = 1; r <= N;r++) {
			String[] inputs = bf.readLine().split(" ");
			for(int c = 1; c <= N;c++) {
				map[r][c] = Integer.parseInt(inputs[c-1]);
			}
		}
		
		for(int i = 1; i <= N-2; i ++) {
			
			
			for(int j = 2; j <= N-1; j ++) {
				
				for(int d1 = 1; d1 <= N; d1 ++) {
					for(int d2 = 1; d2 <= N; d2++) {
						
						int result = div_region(i,j,d1,d2);
						if(result != -1) min_gap = Math.min(min_gap, result);
				
					}
				}
			}
		}
		
		System.out.println(min_gap);
		
	}
	
	
	//1번에 N^2 * 3 = 약 10000
	private static int div_region(int x, int y, int d1, int d2) {
		int area1 = 0;
		int area2 = 0;
		int area3 = 0;
		int area4 = 0;
		int area5 = 0;
		
		// 5채우
		div = new int[N+1][N+1];
		// d2 채우기  	
		int k;
		for(k = 0; k <= d2; k++) {
			try {div[x + k][y + k] = 5;} catch(Exception e) {};
		}
		
		k--;
		for(int l = 0; l <= d1; l++) {
			try {div[x + k + l][y + k - l] = 5;} catch(Exception e) {};
		}
		
		// d1 채우기  
		for(k = 0; k <= d1; k++) {
			int col_plus = 1;
			
			if(!( 0 <=  x + k && x + k <= N && 0 <=  y - k && y - k <= N &&
				  0 <= y - k + col_plus && y - k + col_plus <= N)) break;
			

			div[x + k][y - k] = 5;
			
			if(k == 0) continue;
			
			while(div[x + k][y - k + col_plus] != 5) {
				div[x + k][y - k + col_plus++] = 5;
				if(!(0 <= y - k + col_plus && y - k + col_plus <= N)) break;
			}
		}
		
		k--;
		for(int l = 0; l <= d2; l++) {
			int col_plus = 1;
			
			if(!( 0 <= x + k + l && x + k + l <= N &&  0 <= y - k + l && y - k + l <= N &&
				  0 <= y - k + l + col_plus && y - k + l + col_plus <= N)) break;
			
			div[x + k + l][y - k + l] = 5;
			
			if(l == 0 || l == d2) continue;
			
			while(div[x + k + l][y - k + l + col_plus] != 5) {
				div[x + k + l][y - k + l + col_plus++] = 5;
				if(!(0 <= y - k + l + col_plus && y - k + l + col_plus <= N)) break;
			}
		}
		
		// 계산연산 파트  
		for(int r = 0; r <= N;r++) {
			for(int c = 0; c <= N;c++) {
				
				if(r == 0 || c == 0) div[r][c] = 0;
				
				if(div[r][c] == 5) {
					area5 += map[r][c]; 
				}
				
				else{
					if(1 <= r && r < x +d1 && 1 <= c && c <= y) {
						div[r][c] = 1;
						area1 += map[r][c];
					}
					else if(1 <= r && r <= x +d2 && y < c && c <= N) {
						div[r][c] = 2;
						area2 += map[r][c];
					}
					else if(x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2) {
						div[r][c] = 3;
						area3 += map[r][c];
					}
					else if(x + d2 < r && r <= N && y - d1 + d2 <= c && c <= N) {
						div[r][c] = 4;
						area4 += map[r][c];
					}
				}
			}
		}
		int max = Math.max(area1, area2);
		int min = Math.min(area1, area2);
		
		max = Math.max(max, area3);
		min = Math.min(min, area3);
		
		max = Math.max(max, area4);
		min = Math.min(min, area4);
		
		max = Math.max(max, area5);
		min = Math.min(min, area5);
		
		if(min == 0) return -1;
		return max - min;
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