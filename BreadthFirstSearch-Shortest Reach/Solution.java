import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        for(int index = 1; index <= N; index++)
        {
            String[] nm = in.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            
            int[][] adjMatrix = new int[n][n];
            final int[] dist = new int[n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    adjMatrix[i][j] = -1;
            
            for (int a = 0; a < n; a++) {
                dist[a] = Integer.MAX_VALUE/2;
            }

            for (int a = 0; a < m; a++) {
                String[] vwc = in.readLine().split(" ");
                int v = Integer.parseInt(vwc[0])-1;
                int w = Integer.parseInt(vwc[1])-1;
            
                adjMatrix[v][w] = 6;
                adjMatrix[w][v] = 6;
            }

            
            String[] sN = in.readLine().split(" ");
            int startNode = Integer.parseInt(sN[0])-1;

            PriorityQueue<Integer> queue = new PriorityQueue<Integer>(n, new Comparator<Integer>() {
                @Override
                public int compare(Integer p, Integer q) {
                    return (dist[p] - dist[q]);
                }
            });
            
            dist[startNode] = 0;

            for (int a = 0; a < n; a++) {
                if (adjMatrix[startNode][a] != -1) {
                    dist[a] = adjMatrix[startNode][a];
                    queue.offer(a);
                }
            }

            while (queue.size() > 0) {
                int vertex = queue.poll();

                if (vertex == n-1) {
                    break;
                }

                for (int a = 0; a < n; a++) {
                    if (adjMatrix[vertex][a] != -1) {
                        // decrease key
                        if (dist[a] > dist[vertex] + adjMatrix[vertex][a]) {
                            queue.remove(a);
                            dist[a] = dist[vertex] + adjMatrix[vertex][a];
                            queue.offer(a);
                        }
                    }
                }
            }
            
            for (int a = 0; a < n; a++ ) {
                if (a == startNode)
                    continue;
                if (dist[a] == Integer.MAX_VALUE/2)
                    sb.append("-1 ");
                else 
                    sb.append(dist[a]+" ");
            }
                
            sb.append("\n");
        }
        System.out.println(sb);
    }
}