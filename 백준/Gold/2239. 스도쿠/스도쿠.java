import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int [][] map = new int[9][9];
	static List<Blank> list = new ArrayList<>();
	static boolean stop = false;
	
	// 행,열,분면 의 숫자 체크
	static boolean[][] row_visited = new boolean[9][10];
	static boolean[][] col_visited = new boolean[9][10];
	static boolean[][] sep_visited = new boolean[9][10];
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		for(int i =0; i < 9; i++) {
			line = bf.readLine();
			for(int j =0; j < 9; j++) {
				map[i][j] = Integer.parseInt(line.charAt(j)+"");
				
				// 0들을 배열로
				if(map[i][j] == 0) {
					list.add(new Blank(i, j));
				}
				
				// put 여부를 체크
				row_visited[i][map[i][j]] = true;
				col_visited[j][map[i][j]] = true;
				sep_visited[getSep(i,j)][map[i][j]] = true;
			}
		}
		
		
		DFS(0);
		PrintMap();
		
	}
	private static void DFS(int depth) {
		if(depth == list.size()) {
			stop = true;
			return;
		}
		
		Blank blank = list.get(depth);
		
		for(int val = 1; val <= 9; val++) {
			if(CanPut(blank, val)) {
				row_visited[blank.i][val] = true;
				col_visited[blank.j][val] = true;
				sep_visited[getSep(blank.i,blank.j)][val] = true;
				
				map[blank.i][blank.j] = val;
				
				DFS(depth+1);
				if(stop) return;
				
				map[blank.i][blank.j] = 0;
				
				row_visited[blank.i][val] = false;
				col_visited[blank.j][val] = false;
				sep_visited[getSep(blank.i,blank.j)][val] = false;
			}
		}
		
		
		
		
	}
	
	private static boolean CanPut(Blank blank, int put_val) {
		if(row_visited[blank.i][put_val] || 
		   col_visited[blank.j][put_val] || 
		   sep_visited[getSep(blank.i,blank.j)][put_val]) {
			return false;
		}
		return true;
	}
	
	
	
	// 분면 구하기
	private static int getSep(int i, int j) {
		int row = (i / 3) * 3;
		int col = j / 3;
		
		return row + col;
	}
	
	private static void PrintMap() {
		for(int i =0; i < 9; i++) {
			for(int j =0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}

class Blank{
	int i;
	int j;
	
	public Blank(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
}
