package join;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class JoinTest {
	
	public static final char[] R = {'A', 'X', 'Z', 'D', 'E', 'F', 'G', 'H'};
	public static final char[] S = {'B', 'A', 'C', 'C', 'B', 'B', 'B', 'H'};
	
	public static final int[] BR = {7226, 1553, 1554, 4556, 9668, 7551};
	public static final int[] BS = {7221, 1553, 4556, 5223, 7669, 7551, 8854, 9969};
	
	@Test
	public void joinTest() throws IOException {
		Join join = new Join();
		char[] res = join.join(R, S);
		char[] rs = {'A', 'H', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
		Assert.assertArrayEquals(res, rs);
		System.out.println(Arrays.toString(res));
	}
	
	@Test
	public void nestedLoopJoinTest() throws IOException {
		Join join = new Join();
		System.out.println(Arrays.toString(join.nestedLoopJoin(BR, BS)));
		int[] BSD = join.nestedLoopJoin(BR,  BS);
		int[] BRS = {1553, 4556, 7551, 0, 0, 0, 0, 0, 0, 0};
		Assert.assertArrayEquals(BSD, BRS);
	}

}
