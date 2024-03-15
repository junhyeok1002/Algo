import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, M;
	static int[] arr ;
	
	public static void main(String[] args) throws Exception{	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] line = bf.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		arr = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			arr[i+1] = Integer.parseInt(bf.readLine());
		}
		
		MinSegTree minSegTree = new MinSegTree(N);
		MaxSegTree maxSegTree = new MaxSegTree(N);

		minSegTree.init(1, 1, N, arr);
		maxSegTree.init(1, 1, N, arr);
		
		for(int i = 0; i < M; i++) {
			line = bf.readLine().split(" ");
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			
			sb.append(minSegTree.min(1, 1, N, n1, n2) + " "+ maxSegTree.max(1, 1, N, n1, n2)  +"\n");
		}
		

		System.out.println(sb);
	}
}

class MaxSegTree{
	int treeNum;
	long[] tree;
	
	void PrintTree() {
		System.out.println(Arrays.toString(tree));
	}
	
	public MaxSegTree(int N) {
		int h = (int) Math.ceil(Math.log(N)/Math.log(2));
		int treeNum = (int) Math.pow(2, h+1);
		tree = new long[treeNum];
	}
	
	public long init(int node, int start, int end, int[] arr) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		return tree[node] = Math.max(
				init(node*2, start, (start+end)/2, arr), 
				init(node*2+1, (start+end)/2+1, end, arr));
	}
	
	
	public long max(int node, int start, int end, int left, int right) {
		if(end < left || right < start) {
			return Integer.MIN_VALUE;
		}
		
		if(start >= left && right >= end) {
			return tree[node];
		}
		
		return 
		Math.max(
			max(node*2, start, (start+end)/2, left, right), 
			max(node*2+1, (start+end)/2+1, end, left, right));
	}
	
}

class MinSegTree{
	int treeNum;
	long[] tree;
	
	void PrintTree() {
		System.out.println(Arrays.toString(tree));
	}
	
	public MinSegTree(int N) {
		int h = (int) Math.ceil(Math.log(N)/Math.log(2));
		int treeNum = (int) Math.pow(2, h+1);
		tree = new long[treeNum];
	}
	
	public long init(int node, int start, int end, int[] arr) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		return tree[node] = Math.min(
				init(node*2, start, (start+end)/2, arr), 
				init(node*2+1, (start+end)/2+1, end, arr));
	}
	
	
	public long min(int node, int start, int end, int left, int right) {
		if(end < left || right < start) {
			return Integer.MAX_VALUE;
		}
		
		if(start >= left && right >= end) {
			return tree[node];
		}
		
		return 
		Math.min(
			min(node*2, start, (start+end)/2, left, right), 
			min(node*2+1, (start+end)/2+1, end, left, right));
	}
	
}