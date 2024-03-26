import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++ ) {
			HashSet set = new HashSet<>();
			
			
			
			sb.append("#" + t +" ");
			
			// 이용권 가격 책정하기
			String[] line = bf.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			K = Integer.parseInt(line[1]);
		
			String pat = bf.readLine();
			
			for(int k =0; k < N/4; k++ ) {
				for(int i = k; i < k+ N; i+= N/4 ) {
					String temp = "";
					for(int j = i; j < i+N/4; j++) {
						temp += pat.charAt(j%N);
						
					}
					set.add(Integer.parseInt(temp, 16));
				}
			}

			List list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder());
			
			sb.append(list.get(K-1)+"\n");
		}
		
		System.out.println(sb);
	}	
}
