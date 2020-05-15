import model.BellmanGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Dima B
 * @version problema3
 * @apiNote 15.05.2020
 */


public class BellmanKalabaGraph {

    public void getBellmanKalaba(int nrPeak, List<BellmanGraph> graphs, boolean status) {
        //      step 1
        List<List<String>> matrix = new ArrayList<>();
        IntStream.range(1, nrPeak + 1).forEach((i) -> matrix.add(createLineMatrixBellman(graphs, i, nrPeak)));

//      step 2
        List<String> v1 = new ArrayList<>();
        matrix.forEach((v) -> v1.add(getLastElementFromList(v)));
        matrix.add(v1);

//      step 3

        while (!matrix.stream().skip(matrix.size() - 1).findFirst().get().equals(matrix.stream().skip(matrix.size() - 2).findFirst().get())) {
            matrix.add(calculateLine(matrix, nrPeak));
        }

        if (status)
            matrix.forEach((v) -> System.out.println(v.toString()));

        System.out.println("Lungimea drumului minim este : " + matrix.stream().skip(matrix.size() - 1).findFirst().get().get(0));
    }

    private List<String> createLineMatrixBellman(List<BellmanGraph> bellmanGraph, int row, int nrPeak) {
        List<BellmanGraph> graphs = bellmanGraph
                .stream()
                .filter(s -> s.getStart() == row)
                .collect(Collectors.toList());

        return IntStream.range(1, nrPeak + 1).mapToObj(String::valueOf).map(i -> {

            if (String.valueOf(row).equals(i))
                return "0";

            if (graphs.stream().anyMatch(s -> String.valueOf(s.getEnd()).equals(i)))
                return getByListBellman(bellmanGraph, row, Integer.parseInt(i));

            return "-ꝏ";
        }).collect(Collectors.toList());
    }

    private String getByListBellman(List<BellmanGraph> bellmanGraph, int row, int end) {

        int length = bellmanGraph.stream()
                .filter(bellman -> bellman.getStart() == row && bellman.getEnd() == end)
                .map(BellmanGraph::getLength)
                .findAny()
                .orElse(0);

        return String.valueOf(length);
    }

    private String getLastElementFromList(List<String> list) {
        return list
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    private String calculateMax(List<String> first, List<String> last, int nrPeak) {

        int value = IntStream.range(1, nrPeak)
                .map(index -> {
                    if (!first.get(index).equals("-ꝏ") && !last.get(index).equals("-ꝏ")) {
                        return Integer.parseInt(last.get(index)) + Integer.parseInt(first.get(index));
                    }
                    return 0;
                })
                .max()
                .orElseThrow(NoSuchElementException::new);

        return String.valueOf(value);
    }

    private List<String> calculateLine(List<List<String>> matrix, int nrPeak) {
        List<String> line = new ArrayList<>();
        List<String> last = matrix.stream().skip(matrix.size() - 1).findFirst().get();
        matrix
                .stream()
                .limit(nrPeak)
                .forEach((s) -> line.add(calculateMax(s, last, nrPeak)));

        return line;
    }
}
