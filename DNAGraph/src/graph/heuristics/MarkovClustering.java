package graph.heuristics;

public class MarkovClustering {
	private int power;
	private double inflation;
	private float[][] graph;
	private int cliquesCount;
	float[][] tmp;
	private final float EPSILON = 0.00001f;
	private boolean devel;
	
	public MarkovClustering(boolean[][] graph, int power, double inflation) {
		this.power = power;
		this.inflation = inflation;
		tmp = new float[graph.length][graph.length];
		
		this.graph = new float[graph.length][];
		for(int i =0; i<graph.length; ++i) {
			for(int j = 0; j < graph.length; ++j) {
				this.graph[i] = new float[graph.length];
				if(graph[i][j])
					this.graph[i][j] = 1;
				else
					this.graph[i][j] = 0;
			}
		}
	}
	
	public MarkovClustering(float[][] graph, int power, double inflation) {
		this.power = power;
		this.inflation = inflation;
		tmp = new float[graph.length][graph.length];
		this.graph = new float[graph.length][graph.length];
		for(int i = 0; i < graph.length; ++i) {
			for(int j = 0; j<graph.length; ++j) {
				this.graph[i][j] = graph[i][j];
			}
		}
	}
	
	/*
	 * Runs algorithm
	 * 
	 * @return array with the result sets. Some of the results may be empty.
	 */
	public int[][] execute(){
		if(devel)
			System.out.println("Preparing matrix...");
		addSelfLoop();
		normalizeMatrix();
		float[][] prev = new float[graph.length][graph.length];
		int i = 0;
		if(devel)
			System.out.println(" [OK]");
		do {
			i++;
			for(int j=0; j < graph.length; ++j) {
				for(int k=0; k< graph.length; ++k) {
					prev[j][k] = graph[j][k];
				}
			}
			if(devel)
				System.out.println("Iteration "+i+": expanding matrix...");
			expandMatrix(power);
			if(devel)
				System.out.println(" [OK]");
			if(devel)
				System.out.println("Iteration "+i+": inflating matrix...");
			inflation();
			if(devel)
				System.out.println(" [OK]");
		}
		while(isChanged(prev));
		if(devel) {
			System.out.println("ended after" + i + " iterations.");
		}
		return interpretResult(); /*function private int[][] interpretResult() below*/
	}
	
	/* additional sub-functions */
	
	private void addSelfLoop() {
		for(int i =0; i<graph.length; ++i) {
			graph[i][i] = 1;
		}
	}
	
	private void normalizeMatrix() {
		for(int i =0; i<graph.length; ++i) {
			int count =0;
			for(int j=0; j<graph.length; ++j) {
				if(graph[i][j]>=1)
					count++;
			}
			if(count>0) {
				for(int j=0; j<graph.length; ++j) {
					graph[i][j] /= count;
				}
			}
		}
	}
	
	private void expandMatrix(int e) {
		for(int i = 0; i<graph.length; ++i) {
			for(int j=0; j<graph.length; ++j) {
				tmp[i][j] = graph[i][j];
			}
		}
		if(devel)
			System.out.print("Cloned... ");
		
		for(int i=1;i<e;++i) {
			multiplyMatrices(graph, tmp);
		}
		if(devel)
			System.out.println("multiplied... ");
	}
	
	public float[][] getGraph(){
		return graph;
	}
	
	public void multiplyMatrices(float[][] a, float[][] b) {
		if(a.length != b.length) {
			return; //exception
		}
		for(int i=0; i<a.length; ++i) {
			float[] tab = new float[a.length];
			for(int j =0; j<a.length; j++) {
				float t = 0;
				for(int k =0; k<a.length; k++) {
					t += a[k][i]*b[j][k];
				}
				tab[j] = t;
			}
			for(int k=0; k<b.length; ++k) {
				a[k][i] = tab[k];
			}
		}
	}
	private void inflation() {
		for(int i=0; i<graph.length; ++i) {
			float divider = 0.0f;
			for(int j=0; j<graph.length; ++j) {
				graph[i][j] = (float)Math.pow(graph[i][j], inflation);
				divider += graph[i][j];
			}
			for(int j=0; j<graph.length; ++j) {
				graph[i][j] /= divider;
			}
		}
	}
	private boolean isChanged(float[][] prev) {
		if(devel)
			System.out.println("Checking if matrix changed... ");
		for(int i=0; i < prev.length; ++i) {
			for(int j=0; j < prev.length; ++j) {
				float a = graph[i][j];
				float b = prev[i][j];
				float res = Math.abs(((a>b)? a-b:b-a));
				if(res > EPSILON) {
					if(devel)
						System.out.println(" [OK]");
					return true;
				}
			}
		}
		if(devel)
			System.out.println(" [OK]");
		return false;
	}
	private int[][] interpretResult(){
		if(devel)
			System.out.println("Interpreting result...");
		int[][] result = new int[graph.length][];
		int size = 0;
		for(int i=0; i<graph.length; ++i) {
			int count = 0;
			for(int j=0; j<graph.length; ++j) {
				if(graph[j][i] > EPSILON) {
					count++;
				}
			}
			size += count;
			result[i] = new int[count];
			count = 0;
			for(int j = 0; j<graph.length; ++j) {
				if(graph[j][i]>EPSILON) {
					result[i][count++] = j;
				}
			}
		}
		cliquesCount = size;
		if(devel)
			System.out.println(" [OK]");
		return result;
	}
	
	/** 
	 * Returns amount of cliques found in graph 
	 */
	public int getCliquesSize() {
		return cliquesCount;
	}
	
	public void setDevel(boolean dev) {
		this.devel = dev;
	}
	public static void main(String[] args) {
		
		float[][] tab = new float[][] {{5,7,2},{4,1,2},{2,3,1}};
        float[][] b = new float[][] {{4,1,4},{2,2,1},{5,3,3}};
        MarkovClustering cl = new MarkovClustering(tab, 2, 2);
        cl.execute();
	}
}
