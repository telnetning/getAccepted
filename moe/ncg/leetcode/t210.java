package moe.ncg.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class t210
{
    public int[] findOrder(int numCourses, int[][] prerequisites)
    {
        // init data to a graph
        int[] degree = new int[numCourses];
        Map<Integer, List> hm = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            if (!hm.containsKey(prerequisites[i][1])) {
                hm.put(prerequisites[i][1], new ArrayList());
            }
            hm.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int count = 0; // current traveled courses num

        LinkedList<Integer> queue = new LinkedList<>();

        // add init queque num
        for(int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) queue.add(i);
        }

        int[] result = new int[numCourses];

        while(queue.size() != 0) {
            Integer cur = queue.poll();
            result[count] = cur;
            count++;
            List<Integer> nexts = hm.get(cur);
            if (nexts == null) continue;
            for (Integer next : nexts) {
                degree[next]--;
                if(degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int[] emptyResult = {}; // init a empty array
        return count == numCourses ? result : emptyResult;
    }
}

