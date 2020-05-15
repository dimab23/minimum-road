import model.BellmanGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dima B
 * @version problema3
 * @apiNote 15.05.2020
 */


public class BellmanFord {
    public static long[] dist;
    public static long[] prev;

    public void bellmanFord(WeightableDiGraph g, List<BellmanGraph> graphs, int nrPeak) {

        if (g != null) {
            Arrays.fill(dist, Long.MAX_VALUE);
            Arrays.fill(prev, -1);
            dist[0] = 0;
            for (int i = 1; i < g.V() - 1; i++) {
                for (DirectedEdge e : g.adj(i)) {

                    if (dist[e.to()] > dist[e.from()] + e.weight()) {
                        dist[e.to()] = dist[e.from()] + e.weight();
                        prev[e.to()] = e.to();
                    }
                }
            }

            for (int i = 0; i < g.V(); i++) {
                for (DirectedEdge e : g.adj(i)) {
                    if (dist[e.to()] > dist[e.from()] + e.weight())
                        return;
                }
            }
        }
        new BellmanKalabaGraph().getBellmanKalaba(nrPeak, graphs, false);
    }

    public static class DirectedEdge {
        protected int v;
        protected int w;
        protected int weight;

        public DirectedEdge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public int weight() {
            return weight;
        }

        public String toString() {
            return v + "->" + w + " " + String.format("%d", weight);
        }
    }

    public static class WeightableDiGraph {
        protected int V;
        protected int E;
        protected ArrayList<DirectedEdge>[] adj;


        public WeightableDiGraph(int V) {
            this.V = V;
            this.E = 0;
            adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<>();
            }
        }

        public int V() {
            return this.V;
        }

        public int E() {
            return this.E;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            String NEWLINE = System.getProperty("line.separator");
            s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
            for (int v = 0; v < V; v++) {
                s.append(String.format("%d: ", v));
                for (DirectedEdge e : adj[v]) {
                    s.append(e).append("  ");
                }
                s.append(NEWLINE);
            }
            return s.toString();
        }

        public Iterable<DirectedEdge> adj(int v) {
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
            return this.adj[v];
        }

    }
}