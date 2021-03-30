// There are N cities numbered from 1 to N.
//
// You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
//
// Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

// MST: Minimum Spanning Tree, a subset of edges that connects every node with minimum cost
// Kruskal Algorithm: Greedy. Sort edges by cost. Pick edge with min cost until all nodes are connected. If cycle, don't use that edge. Stop at N-1 edges

import java.util.Arrays;
import java.util.Comparator;

// Use an array of length N+1, union[i] represents the "gate" number at city i. Initialize union[i] = i
// Imagine each city has a gate. If 2 cities are connected, we update both with the same gate number
// Sort the input matrix by cost from small to large
// Start from index 0 of the sorted matrix, check city1 and city2's gate, if they are different, union them by update both gate number to the same, sum up their cost and reduce the number of gate variable
// Else, we do nothing since that edge will deliver a loop
// At the end, if the number of gate is 1, then all cities are connected (N cities with N-1 edges). Else return -1
// Time: O(MlogN) while M is the number of edges, N is the number of nodes. Space: O(N)
class Solution {
    public static void main(String[] args) {
        int [][] conn = {{1,2,5},{1,3,6},{2,3,1}};
        System.out.print(new Solution().minimumCost(3, conn));
    }
    public int minimumCost(int N, int[][] connections) {
        int [] union = new int[N+1];
        for(int i = 0; i < union.length; i++) {
            union[i] = i;
        }
        int numOfGates = N;
        Arrays.sort(connections, new Comparator<int []>(){
            public int compare(int [] a, int [] b) {
                if(a[2] == b[2]) {
                    return 0;
                }
                return a[2] < b[2] ? -1 : 1;
            }
        });

        int sum = 0;
        for(int [] arr : connections) {
            int city1 = arr[0];
            int city2 = arr[1];
            int cost = arr[2];
            int gate1 = findGate(city1, union);
            int gate2 = findGate(city2, union);
            if(gate1 != gate2) {
                union[gate2] = gate1;
                sum += cost;
                numOfGates--;
                if(numOfGates == 1) {
                    return sum;
                }
            }
        }
        return numOfGates == 1 ? sum : -1;
    }

    public int findGate(int city, int [] union) {
        if(city == union[city]) {
            return city;
        }
        // Use "Path Compressing" compression to make it faster!
        // Ex. Make 3 -> 2 -> 1 become 3 -> 1
        else {
            union[city] = findGate(union[city], union);
            return union[city];
        }
    }
}
