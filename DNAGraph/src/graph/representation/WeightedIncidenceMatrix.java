package graph.representation;

public class WeightedIncidenceMatrix<T> {
	private T[] vertices;
	private float[][] matrix;
	
	public WeightedIncidenceMatrix(T[] vertices) {
		this.vertices = vertices.clone();
		matrix = new float[vertices.length][vertices.length];
		clearConnections();
	}
	
	public void clearConnections() {
		for(int i = 0; i< vertices.length; i++) {
			for(int j=0; j<vertices.length; ++j) {
				matrix[i][j]=1;
			}
		}
	}
	
	public void setConnection(int fromIndex, int toIndex, int weight) {
		if(fromIndex > 0 && toIndex > 0 && fromIndex < vertices.length && toIndex < vertices.length) {
			matrix[fromIndex][toIndex] = weight;
		}
	}
	
	public int size() {
		return vertices.length;
	}
	
	public T[] getVertices() {
		return vertices;
	}
	
	public float[][] getMatrix(){
		return matrix;
	}
	
	public T getVertex(int i) {
		return vertices[i];
	}
}
