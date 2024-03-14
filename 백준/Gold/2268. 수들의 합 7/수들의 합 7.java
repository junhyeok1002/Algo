import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static long[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] line;
		
		line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		arr = new long[N+1];
		SegTree segTree = new SegTree(N);
		segTree.init(1, 1, N, arr);
		
		for(int i = 1; i <=M; i++) {
			line = bf.readLine().split(" ");
			
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			int n3 = Integer.parseInt(line[2]);
			
			if(n1 == 1) {
				segTree.update(1, 1, N, n2, n3- arr[n2]);
				arr[n2] = n3;
				
			}else {
				if(n2 > n3) {
					int temp = n2;
					n2 = n3;
					n3 = temp;
				}
				
				sb.append( segTree.sum(1, 1, N, n2, n3) + "\n");
			}
		}
		System.out.println(sb);
	}
}
class SegTree{
	long tree[];  // 각 원소가 담길 트리
	int treeSize; // 트리의 크기
	
	// 생성자 : 높이를 구하고, 높이 -> 트리 사이즈 연산 -> 배열 생성
	public SegTree(int arrSize){
	    int h = (int) Math.ceil(Math.log(arrSize)/ Math.log(2)); // 트리 높이
	    this.treeSize = (int) Math.pow(2,h+1); // 트리 배열 사이즈
	    tree = new long[treeSize]; // 트리 배열 만들기
	}
	
	// 트리 배열의 원소들을 채우는 함수
	// arr = 원소배열, node = 현재노드, start = 현재구간 배열 시작, start = 현재구간 배열 끝
	public long init( int node, int start, int end, long[] arr){
	    
	    // 배열의 시작과 끝이 같다면 leaf 노드이므로
    // 원소 배열 값 그대로 담기
	    if(start == end){
	        return tree[node] = arr[start];
	    }
				
			// leaf 노드가 아니면, 자식노드 합 담기
	    return tree[node] =
	    init(node*2,start,(start+ end)/2, arr)
	    + init(node*2+1,(start+end)/2+1,end, arr);
	}
	
	// 인덱스 번호로 바꾸기
	// node: 현재노드 idx, start: 배열의 시작, end:배열의 끝
	// idx: 변경된 데이터의 idx, diff: 원래 데이터 값과 변경 데이터값의 차이
	public void update(int node, int start, int end, int idx, long diff){
	    // 만약 변경할 index 값이 범위 바깥이면 확인 불필요
	    if(idx < start || end < idx) return;
	
	    // 차를 저장
	    tree[node] += diff;
	
	    // 리프노드가 아니면 아래 자식들도 확인
	    if(start != end){
	        update(node*2, start, (start+end)/2, idx, diff);
	        update(node*2+1, (start+end)/2+1, end, idx, diff);
	    }
	}
	
	// 구간의 누적합 구하기
	// node: 현재 노드, start : 배열의 시작, end : 배열의 끝
	// left: 원하는 누적합의 시작, right: 원하는 누적합의 끝
	public long sum(int node, int start, int end, int left, int right){
	    if(left > end || right < start){
	        return 0;
	    }
	
	    if(left <= start && end <= right){
	        return tree[node];
	    }
	
	    return sum(node*2, start, (start+end)/2, left, right)+
	            sum(node*2+1, (start+end)/2+1, end, left, right);
	}

}