import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int N;
	List<Integer> squares = new ArrayList<>();
	static int[] dp;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int test_num = Integer.parseInt(bf.readLine());
		
		TEST:
		for(int t = 0 ; t < test_num; t++) {
			String command = bf.readLine();
			int N = Integer.parseInt(bf.readLine());
			Deque<Integer> deque = new LinkedList<>();
			List<Integer> list = new ArrayList<>();
			
			boolean FIFO = true;
			
			String temp = bf.readLine();
			String line = temp.substring(1,temp.length()-1);
			String[] lines = line.split(",");
	
			
			for(int k=0; k < lines.length; k++) {
				if(!lines[k].isBlank())
					deque.offer(Integer.parseInt(lines[k]));
			}
					
			for(int i =0 ; i < command.length(); i ++) {
				char ch = command.charAt(i);
				if(ch == 'R') FIFO = !FIFO;
				else {
					if(deque.isEmpty()) {
						sb.append("error\n");
						continue TEST;
					}
					
					if(FIFO) {
						deque.pollFirst();
					}else {
						deque.pollLast();
					}
				}
			}
			sb.append("[");
			while(!deque.isEmpty()) {
				if(FIFO) {
					sb.append(deque.pollFirst());
				}else {
					sb.append(deque.pollLast());
				}
				if(!deque.isEmpty()) {
					sb.append(",");
				}
			}
			sb.append("]\n");
		}
		System.out.println(sb);

	}
}