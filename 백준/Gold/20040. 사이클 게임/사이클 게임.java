import java.io.*;
import java.util.*;


public class Main {
	
	static int N, M;
	static int[] parent;
	static boolean cycle;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] line = bf.readLine().split(" ");
		
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		
		parent = new int[N];
		cycle = false;
		ans = 0;
		
		// makeSet
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i <= M; i++) {
			line = bf.readLine().split(" ");
			
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			
			
			union(n1, n2);
			if(cycle) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);

	}
	private static void union(int x, int y) {
		int px = find(x);
		int py = find(y);	
		
		if(px != py) {
			parent[py] = px;
		}else {
			cycle = true;
		}
	}
	
	private static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	
	
}
