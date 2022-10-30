import java.util.*;
import java.io.*;

public class SecurityUpdate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            List<Vertex> L1 = new ArrayList<>(), L2 = new ArrayList<>();
            // L1 = list of verticies w/ known distance from 1
            // L2 = list of verticies w/ known number of verticies reached before

            L1.add(new Vertex(1, 0, 0));
            st = new StringTokenizer(br.readLine());

            for (int i = 2; i <= C; i++) {
                int x = Integer.parseInt(st.nextToken());

                if (x > 0) {
                    // distance from 1 to i is x
                    L1.add(new Vertex(i, x, -1));
                } else {
                    L2.add(new Vertex(i, -1, -1 * x));
                }
            }
            Collections.sort(L1);
            Collections.sort(L2);

            int idx = 0, i1 = 0, i2 = 0;
            List<Vertex> list = new ArrayList<>();

            while (idx < C) {
                if (i1 == 0) {
                    list.add(L1.get(i1));
                    i1++;
                } else if (i2 < L2.size()) {
                    if (L2.get(i2).order <= idx || i1 >= L1.size()) {
                        list.add(L2.get(i2));
                        i2++;
                    } else {
                        list.add(L1.get(i1));
                        i1++;
                    }
                } else {
                    list.add(L1.get(i1));
                    i1++;
                }
                idx++;
            }

            int dist = 0;

            for (int i = 1; i < C; i++) {
                Vertex v = list.get(i);
                if (v.distance == -1) {
                    if (v.order == list.get(i - 1).order) {
                        v.distance = list.get(i - 1).distance;
                    } else
                        v.distance = ++dist;
                } else {
                    dist = v.distance;
                    if (dist == list.get(i - 1).distance) {
                        v.order = list.get(i - 1).order;
                    } else {
                        v.order = i;
                    }
                }
            }
            Map<Integer, Vertex> vertexMap = new HashMap<>();

            for (Vertex v : list) {
                vertexMap.put(v.vertex, v);
            }

            StringBuilder sb = new StringBuilder(String.format("Case #%d:", t));
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Math.abs(vertexMap.get(u).distance - vertexMap.get(v).distance);
                if (w == 0) {
                    w = 10000;
                }
                sb.append(String.format(" %d", w));
            }
            pw.println(sb.toString());
        }
        pw.flush();
        pw.close();
        br.close();
    }
}

class Vertex implements Comparable<Vertex> {
    int vertex, distance, order;

    public Vertex(int vertex, int distance, int order) {
        this.vertex = vertex;
        this.distance = distance;
        this.order = order;
    }

    public int compareTo(Vertex v) {
        if (this.distance != -1 && v.distance != -1) {
            return this.distance - v.distance;
        } else if (this.order != -1 && v.order != -1) {
            return this.order - v.order;
        } else {
            return 0;
        }
    }

    public String toString() {
        return String.format("(V: %d, D: %d, O: %d)", vertex, distance, order);
    }
}