import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	static int N, M;
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0, 0};
	
	static int[][] map;
	static boolean[][] visited;
	
	static int max;
	
	HashSet<String> tetro = new HashSet<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new int[N][M];
		visited = new boolean[N][M];

		
		for(int i = 0 ; i < N; i ++) {
			line = bf.readLine().split(" ");
			for(int j = 0 ; j < M; j ++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < M; j ++) {
				check_BigTetris(i,j);
			}
		}
	
		System.out.println(max);		
	}
	
	static int[][] a = {{0,1},{0,2},{0,3}}; 
	static int[][] b = {{1,0},{2,0},{3,0}}; 
	static int[][] c = {{0,1},{1,0},{1,1}}; 
	static int[][] d = {{0,-1},{0,1},{-1,0}}; 
	static int[][] e = {{0,-1},{0,1},{1,0}};
	static int[][] f = {{-1,0},{1,0},{0,1}};
	static int[][] g = {{-1,0},{1,0},{0,-1}};
	static int[][] h = {{0,1},{-1,0},{1,1}};
	static int[][] i = {{0,1},{-1,1},{1,0}};
	static int[][] j = {{0,1},{1,0},{1,-1}};
	static int[][] k = {{0,-1},{1,0},{1,1}};
	static int[][] l = {{1,0},{2,0},{2,1}}; 
	static int[][] m = {{1,0},{2,0},{2,-1}}; 
	static int[][] n = {{0,1},{0,2},{1,0}}; 
	static int[][] o = {{0,1},{0,2},{1,2}}; 
	static int[][] p = {{1,0},{2,0},{0,1}}; 
	static int[][] q = {{1,0},{2,0},{0,-1}};
	static int[][] r = {{0,1},{0,2},{-1,0}}; 
	static int[][] s = {{0,1},{0,2},{-1,2}}; 
	
	static int[][][] tetris = {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s};
	
	private static void check_BigTetris (int i, int j) {
		
		for(int[][] t : tetris) {
			// t는 불록들
			int sum = map[i][j];
			for(int[] node : t) {
				int new_r = i + node[0];
				int new_c = j + node[1];
				
				if(0 <= new_r && new_r < N && 0 <= new_c && new_c < M) {
					sum+= map[new_r][new_c];
				}
			}
			max = Math.max(sum, max);
		}
	}
}