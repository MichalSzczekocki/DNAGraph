package graph.heuristics;
import graph.Combination;
import java.lang.reflect.Array;

/**
 * Implementation of greedy maximal clique algorithm.
 */

public class MaxClique<T> {
	private boolean[][] incidenceMatrix;
    private T[] graph;
    private Class<T> cls;
    private int[] iclique;
    
    public MaxClique(boolean[][] matrix, T[] graph, Class<T> cls) {
    	this.incidenceMatrix = matrix;
        this.graph = graph.clone();
        this.cls = cls;
    }
    
    public T[] findClique(){
        int m = 0;
        for(int i=0;i<graph.length;++i){
            int deg = 0;
            for(int j =0; j<graph.length;++j){
                if(incidenceMatrix[i][j])
                    deg++;
            }
            if(deg>m)
                m = deg;
        }
        int lb = 0;
        int ub = m;
        int[] s = null;
        int[] clique = null;
        int cliqueSize = 0;
        int mid = (lb + ub)/2;
        do{
            int k = 0;
            s = new int[graph.length];
            for(int i=0;i<graph.length;++i){
                int deg = 0;
                for(int j=0;j<graph.length && deg<mid;++j){
                    if(incidenceMatrix[i][j])
                        deg++;
                }
                if(deg>=mid){
                    s[k] = i;
                    k++;
                }
            }
            int[] tmp = new int[k];
            for(int i=0;i<k;++i)
                tmp[i] = s[i];
            s = tmp;

            if(k > mid){
                if(select(s, k, mid+1)){
                    cliqueSize = mid + 1;
                    clique = iclique;
                    lb = mid + 1;

                }else{
                    ub = mid - 1;
                }
            }else{
                ub = mid - 1;
            }
            if(lb <= ub){
                mid = (lb + ub)/2;
            }
        }while(lb <= ub);
        System.out.println(cliqueSize);
        
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(cls, cliqueSize);
        for(int i=0;i<cliqueSize;++i)
            result[i] = graph[clique[i]];
        return result;
    }

    public boolean select(int[] s, int k, int l){
    	
    	Combination comb = new Combination(k,l,s);
        int[] t = comb.getData();
        do{
            if(isClique(t,l)){
                iclique = t;
                return true;
            }else{
                comb = comb.successor();
                if(comb == null)
                    return false;
                t = comb.getData();
            }
        }while(comb != null);
        return false;
    }

    public boolean isClique(int[] t, int l){
        for(int i=0;i<t.length;++i){
            int deg = 0;
            for(int j=0;j<t.length;++j){
                if(incidenceMatrix[t[i]][t[j]])
                    ++deg;
            }
            if(deg != l-1)
                return false;
        }
        return true;
    }
}
