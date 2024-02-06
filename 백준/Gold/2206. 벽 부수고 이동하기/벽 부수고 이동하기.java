import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader((System.in)));
		String[] first_line = bf.readLine().split(" ");
		int row = Integer.parseInt(first_line[0]);
		int col = Integer.parseInt(first_line[1]);
		
		int ret = -1;
		
		int[] dr = {-1, +1, 0, 0};
		int[] dc = {0, 0, -1, +1};
		
		boolean[][] map = new boolean[row][col];
		
		
		for(int i = 0; i < row ; i++) {
			String[] line = bf.readLine().split("");
			for(int j = 0; j < col ; j++) {
				int temp = Integer.parseInt(line[j]);
				if(temp == 1) map[i][j] = true;
			}
		}

		
		boolean[][][] visited = new boolean[row][col][2];
		
		Queue<int[]> queue = new LinkedList<>();
		
		int[] shorts = {0,0,1,0};
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		queue.offer(shorts);
		
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
//			System.out.println(Arrays.toString(node));
			
			if(node[0] == row-1 && node[1] == col-1 ) {
				if (ret == -1) ret = node[2];
				else if(ret > node[2]) ret = node[2];
			}
			
			for(int k=0; k < 4;k++) {
				int new_r = (node[0] + dr[k]);
				int new_c = (node[1] + dc[k]);
				
				// 사방 탐색 조건 안에 있는데
				if( 0 <= new_r && new_r < row &&
					0 <= new_c && new_c < col ) {
					
					// 방문한한적 없는 경우 + 벽이 아닌 경우 => 평범하게 간다
					if(!visited[new_r][new_c][node[3]] && !map[new_r][new_c]) {
						visited[new_r][new_c][node[3]] = true;
						int[] new_shorts = {new_r,new_c,(node[2]+1), node[3]};
						queue.offer(new_shorts);
					}
					
					// 벽 뚫기로 방문한적 없고, 1회 기회가 남아있는 경우=> 사용하기
					else if(!visited[new_r][new_c][node[3]] && node[3] ==  0) {
						visited[new_r][new_c][node[3]] = true;
						int[] new_shorts = {new_r,new_c, (node[2]+1), (node[3]+1)};
						queue.offer(new_shorts);
					}
				}
			}

		}
		System.out.println(ret);
	}
}
