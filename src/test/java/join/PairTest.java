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
import utils.Utils;

public class PairTest {
	
//	public static final char[] R = {'A', 'Z', 'G', 'J', 'U', 'K', 'E', 'B', 'V', 'D'};
//	public static final char[] S = {'B', 'U', 'Z', 'K', 'X', 'V', 'N', 'L', 'M', 'E'};
//	public static final char[] RS = {'Z', 'U', 'K', 'E', 'B', 'V'};
	
	@Before
	public void init() {
		
	}

	@Test
	public void generate() throws IOException {
		Segmentator segmentator = new Segmentator(96, 46);
		
		List<String> list = Utils.generate(segmentator.getrBlocks());
		Utils<dto.R> utils_r = new Utils<dto.R>();
		int[] array_r = utils_r.toASCII(list, R.class);
		utils_r.segmentData(array_r, R.class);

		List<String> list2 = Utils.generate(segmentator.getsBlocks());
		Utils<dto.S> utils_s = new Utils<dto.S>();
		int[] array_s = utils_s.toASCII(list2, S.class);
		utils_s.segmentData(array_s, S.class);

	}

}
