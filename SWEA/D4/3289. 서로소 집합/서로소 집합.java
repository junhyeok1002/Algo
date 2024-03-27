import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	static int[] parent, rank;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(bf.readLine());
		String[] line;
		
		for(int t = 1; t <= T; t++) {
			sb.append("#"+t+" " );
			
			line = bf.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			
			parent = new int[N+1];
			rank = new int[N+1];
			
			// 처음 make
			for(int num = 1; num <= N; num++) {
				makeSet(num);
			}
			
			for(int i = 0; i < M; i++) {
				line = bf.readLine().split(" ");
				int n1 = Integer.parseInt(line[0]);
				int n2 = Integer.parseInt(line[1]);
				int n3 = 0;
				
				try {
					n3 = Integer.parseInt(line[2]);
				}catch(Exception e) {
					n3 = n2;
				}
				
				// 합집합
				if(n1 == 0) {
					union(n2, n3);
				}
				
				// 출력
				else {
					if(findSet(n2) == findSet(n3)) {
						sb.append("1");
					}
					else {
						sb.append("0");
					}
					
				}
				
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
//	1. Make_Set(x)
	public static void makeSet(int x){
	    parent[x] = x; // 부모 : 자신의 index로 표시
	}

//	2. Find_Set(x)
	public static int findSet(int x){
	    if(x == parent[x])
	        return x;
	    else {
	        parent[x] = findSet(parent[x]);
	        
	        return parent[x];
	    }
	}

//	1. Union(x,y)
	public static void union(int x, int y) {
	    int px = findSet(x);
	    int py = findSet(y);
	    
	    if (px != py){
	        parent[py] = px;
	    }
	}
}