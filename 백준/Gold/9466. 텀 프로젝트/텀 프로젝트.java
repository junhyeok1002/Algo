import java.io.*;
import java.util.*;


public class Main {
	
	static int N, ans;
	static int[] table;
	static boolean[] hasCycle;
	static boolean cycle, black;
	static boolean[] blackList;
	
	static boolean[] visited;

	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
			
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(bf.readLine());
			String[] line = bf.readLine().split(" ");
			
			table = new int[N+1];
			hasCycle = new boolean[N+1];
			blackList = new boolean[N+1];
			visited = new boolean[N+1];
			
			for(int i = 1; i <= N; i++) {
				table[i] = Integer.parseInt(line[i-1]);
				
				if(i == table[i]) hasCycle[i] = true;
			}
			
			// 하나씩 돌면서 
			for(int i = 1; i <= N; i++) {
				if(hasCycle[i] || hasCycle[table[i]] ||
				   blackList[i] || blackList[table[i]]) continue;

				cycle = false;
				black = false;
				Arrays.fill(visited, false);
				DFS(i);
				
//				System.out.println(i);
//				System.out.println(Arrays.toString(hasCycle));
//				System.out.println(Arrays.toString(blackList));
//				System.out.println();
			}
			
			ans = 0;
			for(int i = 1; i <= N; i++) {
				if(!hasCycle[i]) ans++;
			}
			sb.append(ans +"\n");
			
		}
		System.out.println(sb);
	}
	private static void DFS(int num) {

		// 방문한 적 있다는 것은 사이클이 발생했다는 것
		if(visited[num]) {
			cycle = true;
			hasCycle[num] = true;
			return;
		}
		
		
		// 지금과 다음가려는 곳이 블랙리스트에 있으면 지금 것도 블랙리스트로 만들고 back
		if(hasCycle[num] || blackList[table[num]] || blackList[num]) {
			black = true;
			return;
		}
		
		// 방문하고 DFS
		visited[num] = true;
		DFS(table[num]);
		
		
		// 만약에 사이클이 있다고 하면 ㄱㄱ 
		if(hasCycle[num]) {
			if(!cycle) hasCycle[num] = false;
			else hasCycle[num] = true;
			
			if(black) blackList[num] = true;
			cycle = false;
			
			return;
		}
		
		if(!cycle) hasCycle[num] = false;
		else hasCycle[num] = true;
		
		if(black) blackList[num] = true;
	}
	
	
	
}
