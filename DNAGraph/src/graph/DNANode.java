package graph;

public class DNANode {
	private int length;
	private String seq;
	private int instNo;
	private int pos;
	
	public DNANode(int l, String seq, int instNo, int pos) {
		length = l;
		this.pos = pos;
		if(seq.length() != length) {
			System.out.println("Length Error!");
		}
		this.seq=seq;
		this.instNo=instNo;
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getSeq() {
		return seq;
	}
	
	public int getInstNo() {
		return instNo;
	}
	
	public boolean isSimiliarTo(DNANode o) {
		int k=0;
		for(int i=0; i<length; i++) {
			if(seq.charAt(i) != o.seq.charAt(i)) {
				k++;
			}
		}
		if(k>2) {
			return false;
		}else {
			return true;
		}
	}
	public int hamming(DNANode o) {
		int r= 0;
		for(int i=0; i<length; ++i) {
			if(seq.charAt(i) != o.getSeq().charAt(i)) {
				r++;
			}
		}
		return r;
	}
}

