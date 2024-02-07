import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dr1 = {0, -1, 0, +1};
	static int[] dc1 = {+1, 0, -1, 0};

	static int[] dr2 = {0, +1, 0, -1};
	static int[] dc2 = {+1, 0, -1, 0};
	
	static Node[] air_cleaner = new Node[2];
	static int air_idx =0;
	
	static int N, M;
	static int[][] map, temp_map;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		int T = Integer.parseInt(inputs[2]);
		
		map = new int[N][M];
		temp_map  = new int[N][M];
		
		for(int i = 0; i < N ; i++) {
			inputs = bf.readLine().split(" ");
			for (int j =0 ; j < M; j++) {
				map[i][j]= Integer.parseInt(inputs[j]);
				
				if(map[i][j] == -1) air_cleaner[air_idx++] = new Node(i,j);
			}
		}
		
		// 여기까지 변수 선언  
		for( int i = 0; i < T ; i++ ) {
			AfterOneSecond();
		}
		
		int sum = 0;
		for(int i = 0; i < N ; i++) {
			for (int j =0 ; j < M; j++) {		
				if( map[i][j] != -1 ) {
					sum += map[i][j];
				}
			}
		}
		System.out.println(sum);
		
	}
	private static void AfterOneSecond() {
		temp_map  = new int[N][M];
		// 1초가 지나면 벌어지는 일, 첫번째 확산  
		for(int i = 0; i < N ; i++) {
			for (int j =0 ; j < M; j++) {		
				int value = map[i][j];
				int cnt = 0;
				for(int k = 0; k < 4 ; k++) {
					int new_r = i + dr1[k];
					int new_c = j + dc1[k];
					
					if(0 <= new_r && new_r < N && 0 <= new_c && new_c < M && map[new_r][new_c] != -1) {
						temp_map[new_r][new_c] += (int)(value / 5);
						cnt ++;
					}
				}
				temp_map[i][j] -= cnt * (int)(value / 5);
			}
		}
		for(int i = 0; i < N ; i++) {
			for (int j =0 ; j < M; j++) {
				map[i][j] += temp_map[i][j];
			}
		}
		
		// 2번째 벌어지는 일 -> 공기 청정기1 상단부 
		Node node1 = air_cleaner[0];
		int to_put = 0; 
		
		int new_r = node1.i;
		int new_c = node1.j;
		
		for(int k = 0; k < 4; k++) {
			new_r += dr1[k];
			new_c += dc1[k];
			
			while(0 <= new_r && new_r < N && 0 <= new_c && new_c < M) {
				if(map[new_r][new_c] == -1) {
					break;
				}
				
				int temp = map[new_r][new_c];
				map[new_r][new_c] = to_put;
				to_put = temp;
				
				new_r += dr1[k];
				new_c += dc1[k];
			}
			new_r -= dr1[k];
			new_c -= dc1[k];
		}
		
		// 2번째 벌어지는 일 -> 공기 청정기 하단부  
		Node node2 = air_cleaner[1];
		to_put = 0; 
		
		new_r = node2.i;
		new_c = node2.j;
		
		for(int k = 0; k < 4; k++) {
			new_r += dr2[k];
			new_c += dc2[k];
			
			while(0 <= new_r && new_r < N && 0 <= new_c && new_c < M) {
				if(map[new_r][new_c] == -1) {
					break;
				}
				
				int temp = map[new_r][new_c];
				map[new_r][new_c] = to_put;
				to_put = temp;
				
				new_r += dr2[k];
				new_c += dc2[k];
			}
			new_r -= dr2[k];
			new_c -= dc2[k];
		}
		
		
		
	}
}
class Node{
	int i;
	int j;
	
	Node(){}
	Node(int i, int j){
		this.i = i;
		this.j = j;
	}
}
