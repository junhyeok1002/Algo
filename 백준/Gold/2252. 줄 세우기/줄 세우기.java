import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] table;
	static StringBuilder sb = new StringBuilder();
	static List[] graph;
	
	static class Node<T> implements Comparable<T>{
		int num;
		
		Node(int num){
			this.num = num;
		}
		
		@Override
		public int compareTo(T o){
			Node new_node = (Node) o;
			
			// 만약에 진입 차수가 0이야 그럼 들어오지
			// 진입차수가 0인 들어 온것 중에 최소
		
//			// 위상의 단계 수를 비교?
//			if(table[this.num] > table[new_node.num]) return 1;
//			if(table[this.num] < table[new_node.num]) return -1;
					
//			// 먼저풀 문제가 있냐 없냐
//			if(graph[this.num] == null && graph[new_node.num] != null) return 1;
//			if(graph[this.num] != null && graph[new_node.num] == null) return -1;
			
			if(this.num > new_node.num) return 1;
			if(this.num < new_node.num) return -1;
			
			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 한줄을 입력 받아 split
		String[] line = bf.readLine().split(" ");
		 
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		graph = new ArrayList[N+1];
		table = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			line = bf.readLine().split(" ");
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			
			table[n2]++;
			
			if(graph[n1] == null) 
				graph[n1] = new ArrayList<Integer>();
			
			graph[n1].add(n2);
		}
		
		T_sort();
		System.out.println(sb);
		
	}
	private static void T_sort() {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		
		// 진입 차수 0인 것들을 PQ에 넣기
		for(int num = 1; num <= N; num++) {
			if(table[num] == 0) PQ.add(new Node(num));
		}
		
		// 하나꺼내면서 
		while(!PQ.isEmpty()) {
			// 하나를 꺼내고 출력하기
			Node node = PQ.poll();
			sb.append(node.num +" ");
			
			// 인접한게 더이상 없으면 끝
			if(graph[node.num] == null) continue;
			
			// 아니면 인접한거 진입 차수 내리고 0이면 넣기
			for(Object obj : graph[node.num]) {
				int near = (int) obj;
				
				table[near]--;
				if(table[near] == 0) PQ.add(new Node(near));
			}
		}
	}	
}

