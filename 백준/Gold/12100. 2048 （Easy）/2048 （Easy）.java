import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

// 한번 합쳐진 블록은 다시 합쳐지지 않는다.
public class Main {
	static int N;
	static int[][] map;
	
	static int maximum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		map = new int[N][N];
				
			
		for(int i = 0; i < N; i ++) {
			String[] line = bf.readLine().split(" ");
			for(int j = 0; j < N; j ++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		DFS(0, mapCopy(map));
		System.out.println(maximum);
	}
	
	// 숫자 1, 2, 3, 4를 5번에 거쳐서 중복하용하는 순열로
	private static void DFS(int depth, int[][] map) {
		
		if(depth == 5) {
			maximum = Math.max(maximum, maxMap(map));
//			printMap(map);
			return;
		}

		for(int i = 1; i <= 4; i++) {
			int[][] copy_map = Move(i, map);
//			System.out.print(i +" ");
			DFS(depth+1, copy_map);
		}
	}
	
	private static int[][] Move(int n, int[][] map) {
		int[][] copy_map = mapCopy(map);
		
		if(n == 1) UpMap(copy_map);
		else if(n == 2) DownMap(copy_map);
		else if(n == 3) LeftMap(copy_map);
		else if(n == 4) RightMap(copy_map);

		return copy_map;
	}
	
	private static void UpMap(int[][] map) {
		for(int j = 0; j < N; j ++) {
			List<Integer> line = new LinkedList<>();
			for(int i = 0; i < N; i ++) {
				if(map[i][j] == 0) continue;
				else line.add(map[i][j]);
			}
			
			int size = line.size();

			try {
				for(int s = 0; s < size;s++) {
					if(line.get(s).equals(line.get(s+1))) {
						line.add(s, line.get(s)*2);
						line.remove(s+1);
						line.remove(s+1);
					}
				}
			}catch(IndexOutOfBoundsException e) {
				
			}
			
			for(int i = 0; i < N; i ++) {
				if(i < line.size())
					map[i][j] = line.get(i);
				else {
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void DownMap(int[][] map) {
		for(int j = 0; j < N; j ++) {
			List<Integer> line = new LinkedList<>();
			for(int i =  N-1; i >= 0; i --) {
				if(map[i][j] == 0) continue;
				else line.add(map[i][j]);
			}
			
			int size = line.size();
			
			
			try {
				for(int s = 0; s < size;s++) {
					if(line.get(s).equals(line.get(s+1))) {
						line.add(s, line.get(s)*2);
						line.remove(s+1);
						line.remove(s+1);
					}
				}
			}catch(IndexOutOfBoundsException e) {
				
			}
			
			int idx = 0;
			for(int i = N-1; i >= 0; i --) {
				if(i >= N-line.size())
					map[i][j] = line.get(idx++);
				else {
					map[i][j] = 0;
				}
			}
		}
	}
	
	
	// 테스트 끝
	private static void RightMap(int[][] map) {
		for(int i = 0; i < N; i ++) {
			List<Integer> line = new LinkedList<>();
			for(int j = N-1; j >= 0; j --) {
				if(map[i][j] == 0) continue;
				else line.add(map[i][j]);
			}
			
			int size = line.size();

			try {
				for(int s = 0; s < size;s++) {
					if(line.get(s).equals(line.get(s+1))) {
						line.add(s, line.get(s)*2);
						line.remove(s+1);
						line.remove(s+1);
					}
				}
			}catch(IndexOutOfBoundsException e) {
				
			}
			
			int idx = 0;
			for(int j = N-1; j >= 0; j --) {
				if(j >= N-line.size())
					map[i][j] = line.get(idx++);
				else {
					map[i][j] = 0;
				}
			}
		}
	}
	
	
	// 테스트 끝
	private static void LeftMap(int[][] map) {
		for(int i = 0; i < N; i ++) {
			List<Integer> line = new LinkedList<>();
			for(int j = 0; j < N; j ++) {
				if(map[i][j] == 0) continue;
				else line.add(map[i][j]);
			}
			
			int size = line.size();
			
			
			try {
				for(int s = 0; s < size;s++) {
					if(line.get(s).equals(line.get(s+1))) {
						line.add(s, line.get(s)*2);
						line.remove(s+1);
						line.remove(s+1);
					}
				}
			}catch(IndexOutOfBoundsException e) {
				
			}
			
			
			for(int j = 0; j < N; j ++) {
				if(j < line.size())
					map[i][j] = line.get(j);
				else {
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void printMap(int map[][]) {
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < N; j ++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===================================");
	}
	
	private static int maxMap(int[][] map) {
		int max = 0;
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < N; j ++) {
				max = Math.max(map[i][j], max);
			}
		}
		return max;
	}
	
	private static int[][] mapCopy(int[][] original){
		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < N; j ++) {
				copy[i][j] = original[i][j];
			}
		}
		return copy;
	}
}