package graph;

/**
 * Represents combination of a given set.
 */

public class Combination {
	private int n;
    private int k;
    private int[] data;

    public Combination(int n, int k){
        this.n = n;
        this.k = k;
        data = new int[k];
        for(int i = 0;i<k;i++){
            data[i] = i;
        }
    }
    public Combination(int n, int k, int[] data){
        this.n = n;
        this.k = k;
        this.data = new int[k];
        for(int i = 0;i<k;i++){
            this.data[i] = data[i];
        }
    }

    @Override
    public String toString(){
        String s = "{ ";
        for(int i=0; i<k; i++){
            s += String.valueOf(data[i]) + " ";
        }
        s += " }";
        return s;
    }
    public Combination successor(){
        if(data[0] == n-k) // last combination
            return null;
        Combination ans = new Combination(n,k);
        for(int i=0; i<k;i++)// rewriting data
            ans.data[i] = this.data[i];
        int i;
        for(i=k-1;i>0 && ans.data[i] == n-k+i;i--)
            ;
        ++ans.data[i];
        for(int j=i;j<k-1;++j){
            ans.data[j+1] = ans.data[j] +1;
        }
        return ans;
    }
    public static void main(String[] args){
        Combination cmb = new Combination(7,4);
        while(cmb != null){
            System.out.println(cmb);
            cmb = cmb.successor();
        }
    }
    public int[] getData(){
        return data.clone();
    }
}
