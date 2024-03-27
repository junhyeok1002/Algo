import java.io.*;
import java.util.*;

public class Main {
	static int G, P, cnt = 0;
	static boolean[] visited;
	
	static LinkedHashMap<Integer, Integer> hashmap = new LinkedHashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(bf.readLine());
		P = Integer.parseInt(bf.readLine());
		
		// 게이트 만들기
		visited = new boolean[G+1];
		
		for(Integer i = G; i > 0; i--) {
			hashmap.put(i, i);
		}
		
		// 시간 복잡도 P = 100000
		for(int i =0; i < P; i++) {
			int temp = Integer.parseInt(bf.readLine());
			
			// 도킹 되면 넘겨
			if(docking(temp)) {
				cnt++;
				continue;
			}
			// 도킹 안되면 break
			break;
		}
		
		
//		System.out.println(check.toString());
		
		System.out.println(cnt);
	}	
	
	private static boolean docking(int plane) {
		
		Iterator<Integer> iter =hashmap.values().iterator();
		
		if(!iter.hasNext()) return false;
		plane = Math.min(plane, iter.next());
		
		

		while(plane > 0) {
			if(!visited[plane]) {
				visited[plane] = true;
				hashmap.remove((Integer) plane);
				return true;
			}
			// 못넣으면 빼기
			plane--;
		}
		
		return false;
	}
}
