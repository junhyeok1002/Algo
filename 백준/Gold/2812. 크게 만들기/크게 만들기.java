import java.io.*;
import java.util.*;

public class Main {
	static int N, K, startP;
	static int[] nums;
	static MaxSegTree maxseg; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] line = bf.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		startP = 1;
		nums = new int[N+1];
		
		line = bf.readLine().split("");
		for(int i = 0; i < N ;i++) {
			nums[i+1] = Integer.parseInt(line[i]);
		}
		
		maxseg = new MaxSegTree(N);
		maxseg.init(1, 1, N, nums);
		
		int length = N-K;
		for(int k = 0; k < length; k++) {
			sb.append(OneStep());
		}
		System.out.println(sb);
	}
	
	private static int OneStep() {
		int periodMax = maxseg.max(1, 1, N, startP, (startP+K));

		// 가장 앞 수가 최대 값이 아니면 계속 뺸다.
		while(nums[startP] != periodMax) {
			startP++;
			K--; 
		}
		
		// 최대인 값이라면 뺀다
		int max = nums[startP++];
//		N = nums.length-startP;
		return max;
	}
}
class MaxSegTree{
	int treeNum;
	int[] tree;
	
	void PrintTree() {
		System.out.println(Arrays.toString(tree));
	}
	
	public MaxSegTree(int N) {
		int h = (int) Math.ceil(Math.log(N)/Math.log(2));
		int treeNum = (int) Math.pow(2, h+1);
		tree = new int[treeNum];
	}
	
	public int init(int node, int start, int end, int[] arr) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		return tree[node] = Math.max(
				init(node*2, start, (start+end)/2, arr), 
				init(node*2+1, (start+end)/2+1, end, arr));
	}
	
	
	public int max(int node, int start, int end, int left, int right) {
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
