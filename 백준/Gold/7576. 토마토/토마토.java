import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first_line = br.readLine().split(" ");
		int col = Integer.parseInt(first_line[0]);
		int row = Integer.parseInt(first_line[1]);
		
		int[] standard_node = null;
		boolean is_open = true;
		
				
		int days = 0;
		
		// i, j , value
		Queue<int[]> queue = new LinkedList<>();
		
		// 사방 탐색용
		int[] dx = {0, 0, -1, +1};
		int[] dy = {-1, +1, 0, 0};
		
		int[][] tomato = new int[row][col];
		
		// 초기화
		for(int i = 0 ; i < row ; i++) {
			String[] input_row = br.readLine().split(" ");
			for(int j = 0 ; j < col ; j++) {
				tomato[i][j] = Integer.parseInt(input_row[j]);
			} 
		}
		
		
		
		// 첫 큐에 초기화
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				if(tomato[i][j] == 1) {
					int[] node = {i,j,tomato[i][j]};
					queue.offer(node);
					
					if(is_open) {
						standard_node = node;
						is_open = false;
					}
					
				}
			}
		}
		
	
		
		
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			
			// 기준 노드가 오면 장자권 수정 가능여부를 열어둠
			if ( node[0] == standard_node[0] && node[1] == standard_node[1]) {
				is_open = true;
			}
			
			for(int k = 0; k < 4 ; k++) {
				// 사방 범위 안에 있다는 가정 하에, 토마토가 익지 않았으면(0) queue에 넣기
				if(0 <= node[0]+dx[k] && node[0]+dx[k] < row &&
				   0 <= node[1]+dy[k] && node[1]+dy[k] < col &&
				   tomato[node[0]+dx[k]][node[1]+dy[k]] == 0) {
					
					// 토마토 배열을 방방문횟수를 확인하는 용으로!
					tomato[node[0]+dx[k]][node[1]+dy[k]] = 1;
					
					int[] new_node = {node[0]+dx[k], node[1]+dy[k], 1};
					
					queue.offer(new_node);
					
					if(is_open) {
						standard_node = new_node;
						is_open = false;
						days += 1;
					}
					
				}
			}
		}
		
		boolean isFill = true;
		
		breakOut :
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				if (tomato[i][j] == 0) {
					isFill = false; 
					break breakOut;
				}
			}
		}
		
		if(isFill) System.out.println(days);
		else System.out.println(-1);
	}
}
