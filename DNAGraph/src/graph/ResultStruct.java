package graph;

public class ResultStruct {
	private DNANode[] sq;
    private int[][] conns;
    private int size;
    private int[] vertices;

    public int[] getVertices() {
        return vertices;
    }

    public void setVertices(int[] vertices) {
        this.vertices = vertices;
    }

    public DNANode[] getSq() {
        return sq;
    }

    public ResultStruct setSq(DNANode[] sq) {
        this.sq = sq;
        return this;
    }

    public int[][] getConns() {
        return conns;
    }

    public ResultStruct setConns(int[][] conns) {
        this.conns = conns;
        return this;
    }

    public int getSize() {
        return size;
    }

    public ResultStruct setSize(int size) {
        this.size = size;
        return this;
    }
}
