import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static int D1, M1, M3, Y;
	static int[] Months;
	static boolean[] visited;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++ ) {
			sb.append("#" + t +" ");
			
			// 이용권 가격 책정하기
			String[] line = bf.readLine().split(" ");
			D1 = Integer.parseInt(line[0]);
			M1 = Integer.parseInt(line[1]);
			M3 = Integer.parseInt(line[2]);
			Y  = Integer.parseInt(line[3]);
			
			Months = new int[13];
			visited = new boolean[13];
			min = Integer.MAX_VALUE;
			
			// 입장일 수 입력
			line = bf.readLine().split(" ");
			
			for(int i = 0; i < 12; i++) {
				Months[i+1] = Integer.parseInt(line[i]);
			}
			

			DFS(1, 0);
		
			sb.append(min+"\n");
			
		}
		
		System.out.println(sb);
	}
	
	// depth는 1부터 시작하기
	private static void DFS(int depth, int price) {
		
		if(depth == 13) {
			min = Math.min(min, price);
			return;
		}
		
		// 여기서 1월이면 1년치 ㄱㄱ
		if(depth == 1) {
			for(int i = 1; i <= 12; i++ ) visited[i] = true;
			DFS(depth+12, price + Y);
			for(int i = 1; i <= 12; i++ ) visited[i] = false;
		}
		
		// 이제 3달치 ㄱㄱ 
		if(depth <= 10) {
			visited[depth] = true;
			visited[depth+1] = true;
			visited[depth+2] = true;
			
			DFS(depth+3, price + M3);
			
			visited[depth] = false;
			visited[depth+1] = false;
			visited[depth+2] = false;
		}
		
		// 이제 1달치와 개인치 비교 후 ㄱㄱ
		DFS(depth+1, price + Math.min(Months[depth] * D1 , M1));
	}
}
