package graph;

public class LongestPathSearch {
	private int[][] graph;
	public LongestPathSearch(int[][] graph) {
		this.graph = graph;
	}
	
	public int[] getLongestPath() {
		int[] result = new int[graph.length];
		int[] tmp = new int[graph.length];
		int iterator = 0;
		boolean wasNext = true;
		int bestValue = 0;
		int value = 0;
		
		//do we need to check start from each vertex?
		for(int k = 0; k < graph.length; ++k) {
			tmp[iterator++] = k;
			do {
				int v = tmp[iterator - 1];
				int start = 0;
				if(!wasNext) {
					start = tmp[iterator];
					tmp[iterator] = -1;
				}
				wasNext = false;
				for(int i = start; i < graph.length; ++i) {
					if (graph[v][i] > 0) {
                        value += graph[v][i];
                        tmp[iterator++] = i;
                        wasNext = true;
                        break;
                    }
				}
				if(!wasNext){
                    if(value > bestValue){
                        for(int i = 0; i < graph.length; ++i){
                            result[i] = tmp[i];
                            bestValue = value;
                        }
                    }
                    iterator--;
                }
			}while(iterator > 0);
			
		}
		return result;
	}
}
