import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
 
public class Solution {
     
    static Node[] tree;
     
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int test_case = Integer.parseInt(bf.readLine());
         
        for(int t = 0 ; t < test_case; t++) {
            String[] line = bf.readLine().split(" ");
            int node_N = Integer.parseInt(line[0]);
            int edge_N = Integer.parseInt(line[1]);
             
            int sibling1 = Integer.parseInt(line[2]);
            int sibling2 = Integer.parseInt(line[3]);
             
            tree = new Node[node_N+1]; // 노드의 번호가 곹 인덱스
            for(int i=0; i < node_N+1; i++) {
                tree[i] = new Node();
            }
             
             
            // 둘쨰 줄을 읽고 트리만들기
            line = bf.readLine().split(" ");
            for(int i=0; i<line.length ; i+=2) {
                int parent = Integer.parseInt(line[i]);
                int child = Integer.parseInt(line[i+1]);
                 
                tree[parent].setChilds(child);
                tree[child].setParent(parent);
            }
             
            int common_node = findUnionParent(sibling1, sibling2);
            sb.append("#"+(t+1)+" "+common_node+" "+findTreeNum(common_node)+"\n");
        }
        System.out.println(sb);
    }
    private static int findUnionParent(int node1, int node2) {
        List<Integer> node1_parents = new ArrayList<>();
         
        // 노드 1의 부모들을 전부 찾기
        int now_node = node1;
        int temp = tree[now_node].parent;
        while(temp != -1) {
            node1_parents.add(temp);
            now_node = temp;
            temp = tree[now_node].parent;
        }
         
        // 노드 2의 부모들을 전부 찾다가 공동된것이 나오면 종료
        now_node = node2;
        temp = tree[now_node].parent;
        while(!node1_parents.contains(temp)) {
            node1_parents.add(temp);
            now_node = temp;
            temp = tree[now_node].parent;
        }
         
        return temp;
    }
     
    // BFS로 구현
    private static int findTreeNum(int root_node) {
        int sum = 0;
         
        Deque<Integer> queue = new LinkedList<>();
        queue.add(root_node);
         
        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            sum++;
             
            for(int child : tree[node].childs) {
                queue.add(child);
            }
        }
 
        return sum;
    }
     
}
class Node{
    int parent  = -1;
    List<Integer> childs = new ArrayList<>();
     
    Node(){
        childs = new ArrayList<>();
    }
     
    public void setParent(int parent) {
        if(this.parent == -1) 
            this.parent= parent;
    }
     
    public void setChilds(Integer num) {
         
        if(!childs.contains(num))
            this.childs.add(num);
    }
     
}