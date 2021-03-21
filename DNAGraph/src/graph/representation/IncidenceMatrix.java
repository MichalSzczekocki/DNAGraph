package graph.representation;

/**
 * Implementation of incidence matrix graph representation.
 */
public class IncidenceMatrix<T> {
	
	private T[] vertices;
    private boolean[][] matrix;

    public IncidenceMatrix(T[] vertices){
        this.vertices = vertices.clone();
        matrix = new boolean[vertices.length][vertices.length];
        clearConnections();
    }

    public void clearConnections(){
        for(int i=0; i< vertices.length; i++){
            for(int j=0; j < vertices.length; ++j){
                matrix[i][j] = false;
            }
        }
    }

    public void setConnection(int fromIndex, int toIndex){
        if(fromIndex > 0 && toIndex > 0 && fromIndex< vertices.length && toIndex < vertices.length){
            matrix[toIndex][fromIndex] = true;
        }
    }
    public int size(){
        return vertices.length;
    }

    public T[] getVertices() {
        return vertices;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public T getVertex(int i){
        return vertices[i];

    }
}
