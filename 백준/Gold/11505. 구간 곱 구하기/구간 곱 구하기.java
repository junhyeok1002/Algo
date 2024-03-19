import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, M, K;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input;
		String[] line;
		
		line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		
		arr = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		
		SegTree segTree = new SegTree(N);
		segTree.init(1, 1, N, arr);
		
		for(int i = 0; i < M + K ; i++) {
			line = bf.readLine().split(" ");
			
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			int n3 = Integer.parseInt(line[2]);
			
			if(n1 == 1) {
				segTree.update(1, 1, N, n2, n3);
				arr[n2] = n3;
//				System.out.println(Arrays.toString(arr));
//				System.out.println(Arrays.toString(segTree.trees));
				
			}else {
				sb.append( segTree.multiple(1, 1, N, n2, n3) + "\n");
				
			}
		}
		
		System.out.println(sb);
	}
}
class SegTree{
	int tree_len;
	long[] trees;
	
	
	public SegTree(int N){
		
		int h = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree_len = (int) Math.pow(2, h+1);
		trees = new long[tree_len];
	}
	
	public long init(int node, int start, int end, int[] arr) {
		if(start == end) {
			trees[node] = div(arr[start]);
			return div(arr[start]);
		}
		
		return trees[node] = div((init(node*2, start, (start+end)/2, arr) * 
				 init(node*2+1, (start+end)/2+1, end, arr)));	
	}
	
	public long multiple(int node, int start, int end, int left, int right) {
		if(end < left || right < start ) return 1;
		
		if(left <= start && end <= right) {
			return trees[node] ;
		}
		
		return div((multiple(node*2, start, (start+end)/2, left, right) * 
					multiple(node*2+1, (start+end)/2+1, end, left, right)));
		
	}
	
	public long update(int node, int start, int end,int idx, int dif) {
		if(end < idx || idx< start) 
			return trees[node];
		
		if(start == end) 
			return trees[node] = dif;
		
		return trees[node] = div(update(node*2, start, (start+end)/2,  idx, dif) * 
								 update(node*2+1, (start+end)/2+1, end,  idx, dif));
	}
	
	private long div(long num) {
		return num % 1000000007;
	}
}