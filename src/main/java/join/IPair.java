package join;

import java.io.IOException;

public interface IPair {
	
	public char[] join(char[] r, char[] s);
	public char[] mergeJoin(char[] r, char[] s);
	public char[] mergeJoinWithDuplicates(char[] r, char[] s);
	
	public void externalNestedLoopJoin() throws IOException, InterruptedException;
	public int[] nestedLoopJoin(int[] r, int[] s);
	
}
