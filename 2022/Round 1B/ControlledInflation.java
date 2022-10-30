import java.util.*;
import java.io.*;

public class ControlledInflation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            List<Vertex> vertices = new ArrayList<>();
            vertices.add(new Vertex(0, 0)); // initial starting point
            int min = Integer.MAX_VALUE, max = 0;
            st = new StringTokenizer(br.readLine());
            // First Vertex
            for (int i = 0; i < P; i++) {
                int x = Integer.parseInt(st.nextToken());
                min = Math.min(min, x);
                max = Math.max(max, x);
            }
            vertices.add(new Vertex(min, max));
            // all the other vertices

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                min = Integer.MAX_VALUE;
                max = 0;
                for (int j = 0; j < P; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    min = Math.min(min, x);
                    max = Math.max(max, x);
                }
                vertices.add(new Vertex(min, max));
                vertices.add(new Vertex(max, min));
            }

            // Create adjacency list
            int V = vertices.size();
            List<Map<Integer, Long>> adj = new ArrayList<>();
            // adj[u][v] = weight of path (u, v)

            for (int i = 0; i < V; i++) {
                adj.add(new HashMap<>());
            }
            // special case: first vertex
            adj.get(0).put(1, getDist(vertices.get(0), vertices.get(1)));
            adj.get(1).put(2, getDist(vertices.get(1), vertices.get(2)));
            adj.get(1).put(3, getDist(vertices.get(1), vertices.get(3)));
            // other verticies
            for (int i = 1; i < N - 1; i++) {
                int u1 = 2 * i, u2 = 2 * i + 1;
                int v1 = u1 + 2, v2 = u2 + 2;

                adj.get(u1).put(v1, getDist(vertices.get(u1), vertices.get(v1)));
                adj.get(u1).put(v2, getDist(vertices.get(u1), vertices.get(v2)));
                adj.get(u2).put(v1, getDist(vertices.get(u2), vertices.get(v1)));
                adj.get(u2).put(v2, getDist(vertices.get(u2), vertices.get(v2)));
            }

            long[] dist = dijkstra(adj, 0);
            long ans = Math.min(dist[V - 1], dist[V - 2]);

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static long[] dijkstra(List<Map<Integer, Long>> adj, int s) {
        int n = adj.size();
        long[] dist = new long[n];
        for (int i = 0; i < n; i++) {
            dist[i] = (i == s) ? 0 : Long.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(new Node(i, dist[i]));
        }
        for (int i = 0; i < n; i++) {
            Node node = pq.poll();
            int u = node.node;

            while (dist[u] != Long.MAX_VALUE && u != s) {
                node = pq.poll();
                u = node.node;
            }

            dist[u] = node.dist;

            for (Map.Entry<Integer, Long> e : adj.get(u).entrySet()) {
                int v = e.getKey();

                if (dist[v] == Long.MAX_VALUE) {
                    // v has not been visited yet
                    pq.add(new Node(v, dist[u] + adj.get(u).get(v)));
                }
            }
        }
        return dist;
    }

    static long getDist(Vertex u, Vertex v) {
        return (long) Math.abs(v.a - u.b) + Math.abs(v.b - v.a);
    }
}

class Node implements Comparable<Node> {
    int node;
    long dist;

    public Node(int node, long dist) {
        this.node = node;
        this.dist = dist;
    }

    public int compareTo(Node v) {
        if (this.dist < v.dist) {
            return -1;
        } else if (this.dist > v.dist) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return String.format("Vertex %d, distance %d", node, dist);
    }
}

class Vertex {
    int a, b;

    public Vertex(int a, int b) {
        this.a = a;
        this.b = b;
    }
}