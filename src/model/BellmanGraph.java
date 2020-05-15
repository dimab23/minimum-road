package model;

/**
 * @author Dima B
 * @version problema3
 * @apiNote 15.05.2020
 */


public class BellmanGraph {

    private final int start;
    private final int end;
    private final int length;

    public BellmanGraph(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }
}