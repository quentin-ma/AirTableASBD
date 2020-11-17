package join;

public class Segmentator {
	
	private int rBlocks;
	private int sBlocks;
	
	public Segmentator(int rBlocks, int sBlocks) {
		this.rBlocks = rBlocks;
		this.sBlocks = sBlocks;
	}

	public int getrBlocks() {
		return (int) Math.ceil((double) this.rBlocks / 10);
	}

	public void setrBlocks(int rBlocks) {
		this.rBlocks = rBlocks;
	}

	public int getsBlocks() {
		return (int) Math.ceil((double) this.sBlocks / 10);
	}

	public void setsBlocks(int sBlocks) {
		this.sBlocks = sBlocks;
	}

}
