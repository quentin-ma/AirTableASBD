package utils;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dto.R;
import dto.RS;
import dto.S;
import join.Pair;

class AirTableTest {

	@Test
	void test() throws IOException, InterruptedException {
		Utils<R> utils_r = new Utils<>();
		int[] asc_r = utils_r.generateASCII(R.class);	
		
		Utils<S> utils_s = new Utils<>();
		int[] asc_s = utils_s.generateASCII(S.class);
		
		AirTable airtable = new AirTable();
		airtable.generateDescriptor(R.class);
		airtable.generateDescriptor(S.class);
		airtable.generateDescriptor(RS.class);
		
//		airtable.createTables(R.class, asc_r);
//		airtable.createTables(S.class, asc_s);
		
		Pair pair = new Pair();
		pair.externalNestedLoopJoin();

	}

}


