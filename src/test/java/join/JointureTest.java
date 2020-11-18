/**
 * TP n°: 2
 * 
 * Titre du TP : Merge Join
 *
 * Date : 5 novembre 2020
 * 
 * Nom  : MA
 * Prenom : Quentin
 *
 * email : quentin.ma@etu.u-paris.fr
 * 
 * Remarques : 
 */

package join;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JointureTest {
	
	public static final String[] R = {"A", "Z", "G", "J", "U", "K", "E", "B", "V", "D"};
	public static final String[] S = {"B", "U", "Z", "K", "X", "V", "N", "L", "M", "E"};
	public static final String[] RS = {"Z", "U", "K", "E", "B", "V"};
	public static final String[] RS_DUPLICATES = {"B", "B", "B", "B", "E", "K", "U", "U", "V"};
	public static final String R_FILE_TXT = "r.txt";
	public static final String S_FILE_TXT = "s.txt";
	public static final String R_FILE_WITH_DUPLICATES_TXT = "r_with_duplicates.txt";
	public static final String S_FILE_WITH_DUPLICATES_TXT = "s_with_duplicates.txt";
	public static final String RS_FILE_TXT = "rs.txt";
	
	public Jointure jointure;
	public Uplet uplet;
	public Uplet upletWithDuplicates;
	
	@Before
	public void init() {
		jointure = new Jointure();
		uplet = new Uplet(jointure.fileLineToArray(R_FILE_TXT), jointure.fileLineToArray(S_FILE_TXT));
		upletWithDuplicates = new Uplet(jointure.fileLineToArray(R_FILE_WITH_DUPLICATES_TXT), jointure.fileLineToArray(S_FILE_WITH_DUPLICATES_TXT));
	}
	
	@Test
	public void matchLeftTest() {
		Assert.assertEquals(uplet.getLeftUplet().length, 10);
		Assert.assertArrayEquals(uplet.getLeftUplet(), R);
		System.out.println("R = " + Arrays.toString(uplet.getLeftUplet()));
	}
	
	@Test
	public void matchRightTest() {
		Assert.assertEquals(uplet.getRightUplet().length, 10);
		Assert.assertArrayEquals(uplet.getRightUplet(), S);
		System.out.println("S = " + Arrays.toString(uplet.getRightUplet()));
	}
	
	@Test
	public void mergeJoinWithDuplicatesTest() {
		jointure.mergeJoinWithDuplicates(upletWithDuplicates);
		System.out.println("Merge join with duplicates result: RS = " + Arrays.toString(upletWithDuplicates.getRs().toArray()));
		Assert.assertTrue(upletWithDuplicates.getRs().size() < 10);
		Assert.assertEquals(upletWithDuplicates.getRs().size(), RS_DUPLICATES.length);
		Arrays.asList(RS_DUPLICATES);
		Assert.assertArrayEquals(upletWithDuplicates.getRs().toArray(), RS_DUPLICATES);
	}
	
	@Test
	public void mergeJoinTest() {
		jointure.mergeJoin(uplet);
		System.out.println("Merge Join result: RS = " + Arrays.toString(uplet.getRs().toArray()));
		Assert.assertTrue(uplet.getRs().size() < 10);
		Assert.assertEquals(uplet.getRs().size(), RS.length);
		Collections.sort(Arrays.asList(RS));
		Assert.assertArrayEquals(uplet.getRs().toArray(), RS);
	}
	
	@Test
	public void joinTest() {
		jointure.join(uplet);
		System.out.println("RS = " + Arrays.toString(uplet.getRs().toArray()));
		Assert.assertTrue(uplet.getRs().size() < 10);
		Assert.assertEquals(uplet.getRs().size(), RS.length); 
		Assert.assertArrayEquals(uplet.getRs().toArray(), RS);
	}
	
	@Test
	public void writeFileTestWithJoin() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Jointure.filePath(RS_FILE_TXT)));
		String collect = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		String[] arr = collect.split("\n");		
		System.out.println("file: " + Arrays.toString(arr));
		Assert.assertTrue(arr.length < 10);
		Assert.assertEquals(arr.length, RS_DUPLICATES.length);
		jointure.mergeJoinWithDuplicates(upletWithDuplicates);
		Assert.assertArrayEquals(arr,  upletWithDuplicates.getRs().toArray());
		reader.close();
	}

}
