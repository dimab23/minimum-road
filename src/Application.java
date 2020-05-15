import model.BellmanGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dima B
 * @version problema3
 * @apiNote 15.05.2020
 */


public class Application {

    public static void main(String[] args) {
        int nrPeak = 0;
        BellmanFord.WeightableDiGraph weightableDiGraph = null;
        List<BellmanGraph> graph = new ArrayList<>();
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n\n\nAlegeti comanda");
            System.out.println("1 - Adauga un graph");
            System.out.println("2 - Afiseaza graph dupa algoitmul Bellman-Kalaba");
            System.out.println("3 - Afiseaza graph dupa algoitmul Bellman-Ford");
            int command = scanner.nextInt();
            switch (command) {

                case 1:
                    System.out.print("Introduceti numarul de varfuri : ");
                    nrPeak = scanner.nextInt();

                    System.out.print("Introduceti numarul de arce : ");
                    int nrArcs = scanner.nextInt();
                    for (int i = 0; i < nrArcs; i++) {
                        System.out.print("Introduceti start: ");
                        int start = scanner.nextInt();
                        System.out.print("Introduceti destinatia: ");
                        int end = scanner.nextInt();
                        System.out.print("Introduceti capacitatea: ");
                        int weight = scanner.nextInt();

                        graph.add(new BellmanGraph(start, end, weight));
                    }
                    break;

                case 2:
                    new BellmanKalabaGraph().getBellmanKalaba(nrPeak, graph, true);
                    break;

                case 3:
                    new BellmanFord().bellmanFord(weightableDiGraph, graph, nrPeak);
                    break;

                default:
                    System.exit(0);
            }
        }
    }
}
