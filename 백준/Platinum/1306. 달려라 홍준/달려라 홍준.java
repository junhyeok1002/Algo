import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		

		String[] line;
		
		line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		arr = new int[N+1];
		
		line = bf.readLine().split(" ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(line[i-1]);
		}
		
		SegTree segTree = new SegTree(N);
		segTree.init(1, 1, N, arr);

		for(int m = M; m <= N-M+1 ; m++) {
//			System.out.println("m : "+m + ", 구간 " + (m-(M-1)) +" " + (m+(M-1)));
			sb.append( segTree.max(1, 1, N, m-(M-1) , m+(M-1)) + " ");
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
			return trees[node] = arr[start];
		}
		
		return trees[node]=  Math.max(init(node*2, start, (start+end)/2, arr), 
									  init(node*2+1, (start+end)/2+1, end, arr));
	}
	
	public long max(int node, int start, int end, int left, int right) {
		if(end < left || right < start ) return 0;
		
		if(left <= start && end <= right) {
			return trees[node] ;
		}
		
		return Math.max(max(node*2, start, (start+end)/2, left, right), 
				max(node*2+1, (start+end)/2+1, end, left, right));
	}

}