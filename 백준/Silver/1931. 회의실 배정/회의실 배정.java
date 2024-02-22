import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int N;
	static int count = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		List<Meet> meetings = new LinkedList<>();
		List<Meet> out = new LinkedList<>();
		
		N = Integer.parseInt(bf.readLine());
		String[] line;

		for(int i = 0; i < N; i ++) {
			line = bf.readLine().split(" ");
			meetings.add(new Meet(Integer.parseInt(line[0]),
								  Integer.parseInt(line[1])));
		}
		Collections.sort(meetings);
		
		for(Meet meet : meetings) {
			if(out.isEmpty()) {
				out.add(meet);
				continue;
			}
			
			if(meet.start >= out.get(out.size()-1).end) {
				out.add(meet);
			}
		}
		
		System.out.println(out.size());

	}
}
class Meet<T> implements Comparable<T>{
	int start;
	int end;
	
	Meet(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(T o) {
		Meet meet = (Meet) o;
		if(this.end > meet.end) return 1;
		if(this.end < meet.end) return -1;
		
		if(this.start > meet.start) return 1;
		if(this.start < meet.start) return -1;
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "s : " + start + ", e : " + end + "\n";
	}
}


