/**
 *  K stops 问题很明显可以使用 Bellman-Ford  算法
 *  Bellman-Ford 的外层遍历，表示的就是路径的最长深度
 *
 *  但是需要注意，当 遍历第i次时，只能确定所有的路径的最
 *  大深度至少是 i，但是可能大于 i
 *
 *  因此需要在循环中使用 hold 数组，每一次的循环中，松弛
 *  某个点的距离的时候，不能使用本次循环中的与其连接的那个
 *  点的距离，而必须使用上一次的，从而避免路径深度大于 i
 *
 */
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] distance = new int[n];
        int MAX = 1000000;

        for(int i = 0; i < n; i++) {
                distance[i] = MAX;
        }
        distance[src] = 0;

        int edgeLens = flights.length; // edges num
        for(int i = 0; i < K + 1; i++) {
            // K stop，means K + 1 steps most
            int[] hold = distance.clone();
            for(int j = 0; j < edgeLens; j++) {
                // travel every edges and relax
                if(hold[flights[j][0]] + flights[j][2] < distance[flights[j][1]]) {
                    distance[flights[j][1]] = hold[flights[j][0]] + flights[j][2];
                }
            }
        }

        return distance[dst] != MAX ? distance[dst] : -1;
    }
}