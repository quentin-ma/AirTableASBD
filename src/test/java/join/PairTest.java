package join;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import dto.R;
import dto.S;
import utils.Disk;
import utils.Utils;

public class PairTest {
	
	public static final int[] RB = {7227,6320,4233,1563,5220,6330};
	public static final int[] SB = {7227,1552,3221,1563};
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void externalNestedLoopJoinTest() {
		Disk disk = new Disk();
		disk.generateDescriptor();
	}

}
