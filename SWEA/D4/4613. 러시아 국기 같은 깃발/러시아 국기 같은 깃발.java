import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	
	static fillCount[] fillcount; // 해당 색으로 칠하기 위해 필요한 색칠의 수의 배열
	static int[] out = new int[2];
	static int min = 2500;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(bf.readLine());
		
		for( int t = 0 ; t < test_case ; t++) {
			String[] inputs = bf.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int M = Integer.parseInt(inputs[1]);
			
			// 해당 색으로 칠하기 위해 필요한 색칠의 수의 배열
			fillcount = new fillCount[N];

			for(int i=0; i < N; i ++) {
				// 각 라인에 색이 몇개 있는 지 세기
				int w_cnt = 0, r_cnt =0, b_cnt=0;
				String line = bf.readLine();
				for(int j=0; j < M; j ++) {
					if(line.charAt(j) == 'W') w_cnt++;
					else if(line.charAt(j) == 'R') r_cnt++;
					else if(line.charAt(j) == 'B') b_cnt++;
				}
				
				// 각 숫자초 전부 채우려면 필요한 색칠의 수를 구해서 fillcount넣기
				fillcount[i] = new fillCount(M-w_cnt , M-r_cnt, M-b_cnt);
			}
			visited = new boolean[N];
			DFS(0, N);
			sb.append("#"+(t+1)+" "+ min +"\n");
			min = 2500;
		}
		System.out.println(sb);
	}
	
	// out[0] : blue가 시작되는 행, out[1] : red가 시작되는 행
	private static int color_cnt(int[] out, int N) {
		int i;
		int sum = 0;
		// 흰색 색칠 수
		for(i = 0; i < out[0]; i++) {
			sum += fillcount[i].white;
		}
		
		// blue 색칠 수
		for(; i < out[1]; i++) {
			sum += fillcount[i].blue;
		}
		
		// red 색칠 수
		for(; i < N; i++) {
			sum += fillcount[i].red;
		}
		
		return sum;
	}
	
	// 3개 중에 중복없이 순서롤 고려하지 않고 2개를 뽑는 조합
	// blue가 시작될 라인과 red가 시작될 라인을 고르기 위한 목적 => 1인덱스 행에서~ N-1인덱스 행중 2개뽑기
	private static void DFS(int depth, int N) {
		if(depth == 2) {
//			System.out.println(Arrays.toString(out));
			min = Math.min(min, color_cnt(out, N));
			return;
		}
		
		for(int i = 1; i < N; i++) {
			if(depth > 0 && out[depth-1] > i) continue;
			
			if(!visited[i]){
				visited[i] = true;
				out[depth] = i;
				DFS(depth + 1, N);
				visited[i] = false;
			}
		}
	}
	
}
// 해당 색으로 칠하기 위해 필요한 색칠의 수
class fillCount{
	int white;
	int red;
	int blue;
	
	fillCount(){};
	fillCount(int white, int red, int blue){
		this.white = white;
		this.red = red;
		this.blue = blue;
	}
	
	@Override
	public String toString() {
		return "w : " + white + 
			   ", r : " + red + 
			   ", b : " + blue + "\n";
	}
}
