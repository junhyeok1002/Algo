import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, Start;
	static List[] nearList;
	static boolean[] visited;
	static int[] dp;
	static int max_order;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = 10;
		String[] line;
		
		for(int t = 1; t <= T; t++) {
			sb.append("#"+t+" " );
					
			line = bf.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			Start = Integer.parseInt(line[1]);
			
			nearList = new ArrayList[101];
			visited = new boolean[101];
			dp = new int[101];
			max_order = -1;
			
			line = bf.readLine().split(" ");
			for(int i = 0; i < N; i+=2) {
				int n1 = Integer.parseInt(line[i]);
				int n2 = Integer.parseInt(line[i+1]);
				
				// 인접리스트 만들기
				if(nearList[n1] == null) nearList[n1] = new ArrayList<Integer>();
				nearList[n1].add(n2);
			}
			
			BFS();
			sb.append(dp[max_order] + "\n");
		}
		System.out.println(sb);
	}
	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(Start, 0));
		
		
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			max_order = Math.max(max_order, node.order);
			dp[node.order] = Math.max(dp[node.order], node.num);
			
			if(nearList[node.num] != null) {
				for(Object obj : nearList[node.num]) {
					int near = (int) obj;
					
					if(!visited[near]) {
						visited[near] = true;
						queue.offer(new Node(near, node.order+1));
					}
				}
			}
		}
		
	}
}
class Node{
	int num;
	int order;
	
	Node(){};
	Node(int num, int order ){
		this.num = num;
		this.order = order;
	}
	@Override
	public String toString() {
		return "Node [num=" + num + ", order=" + order + "]";
	}
}