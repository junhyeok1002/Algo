import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	static int N, M;
	static int[] table;
	static StringBuilder sb = new StringBuilder();
	static List[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int t = 1; t <= 10; t++) {
			sb.append("#"+t+" ");
			
			// 한줄을 입력 받아 split
			String[] line = bf.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			
			graph = new ArrayList[N+1];
			table = new int[N+1];
			
			line = bf.readLine().split(" ");
			for(int i = 0; i < M*2; i+= 2) {

				int n1 = Integer.parseInt(line[i]);
				int n2 = Integer.parseInt(line[i+1]);
				
				table[n2]++;
				
				if(graph[n1] == null) 
					graph[n1] = new ArrayList<Integer>();
				
				graph[n1].add(n2);
			}
			
			T_sort();
			
			sb.append("\n");
		}
		
		
		System.out.println(sb);
		
	}
	private static void T_sort() {
		Queue<Integer> PQ = new LinkedList<>();
		
		// 진입 차수 0인 것들을 PQ에 넣기
		for(int num = 1; num <= N; num++) {
			if(table[num] == 0) PQ.add(num);
		}
		
		// 하나꺼내면서 
		while(!PQ.isEmpty()) {
			// 하나를 꺼내고 출력하기
			Integer num = PQ.poll();
			sb.append(num +" ");
			
			// 인접한게 더이상 없으면 끝
			if(graph[num] == null) continue;
			
			// 아니면 인접한거 진입 차수 내리고 0이면 넣기
			for(Object obj : graph[num]) {
				int near = (int) obj;
				
				table[near]--;
				if(table[near] == 0) PQ.add(near);
			}
		}
	}	
}