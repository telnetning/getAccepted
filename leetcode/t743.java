import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 注意点
 *
 * 1、防止同一个节点多次添加
 * 2、为什么需要优先队列？防止先通过较长路径访问到该节点
 */
public class t743 {
    public static void main(String[] args) {
        int[][] times = {{1,2,1}, {1,3,4}, {2,3,2}};
        int N = 3;
        int K = 1;
        System.out.println(new Solution743().networkDelayTime(times, N, K));
    }
}

class Solution743 {
    public int networkDelayTime(int[][] times, int N, int K) {
        Set<Integer> visited = new HashSet<>(); // 以此节点出发后才为已访问
        int[] dis = new int[N]; // 到每一个节点的最短距离
        for(int i = 0; i < N; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        int result = 0; // 最终结果

        HashMap<Integer, List<Tuple>> hm = new HashMap<>();
        for(int i = 0; i < times.length; i++) {
            if(!hm.containsKey(times[i][0])) {
                hm.put(times[i][0], new ArrayList<>());
            }
            hm.get(times[i][0]).add(new Tuple(times[i][1], times[i][2]));
        }

        // 这里要用优先队列, 用最小堆
        PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) -> o1.delay - o2.delay);

        pq.add(new Tuple(K, 0));
        dis[K - 1] = 0;
        while(!pq.isEmpty()) {
            Tuple cur = pq.poll();
            visited.add(cur.point);
            result = Integer.max(result, dis[cur.point - 1]);
            if (hm.get(cur.point) == null) continue;
            for (Tuple next : hm.get(cur.point)) {
                // 需要防止多次添加，队列中一个节点只有一组最小值
                if(visited.contains(next.point)) continue;
                if (dis[next.point - 1] > dis[cur.point - 1] + next.delay) {
                    dis[next.point - 1] = dis[cur.point - 1] + next.delay; // 更新某个节点最小值
                    pq.add(new Tuple(next.point, dis[next.point - 1])); // 将该节点及其当前计算出最小距离放入到优先队列中
                }
            }
        }

        return visited.size() == N ? result : -1;
    }

    class Tuple {
        public Tuple(int point, int delay) {
            this.point = point;
            this.delay = delay;
        }
        int point;
        int delay;
    }
}
