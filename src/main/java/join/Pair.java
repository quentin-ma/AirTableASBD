package join;

import java.io.IOException;
import java.util.Arrays;

import dto.RS;
import utils.AirTable;
import utils.Descriptor;

public class Pair implements IPair {
	
	private AirTable airtable;
	
	public Pair() {
		airtable = new AirTable();
	}

	@Override
	public char[] join(char[] r, char[] s) {
		char[] rs = new char[Constants.RS_SIZE];
		int i = 0, j = 0, k = 0;
		while (i < r.length) {
			j = 0;
			while (j < s.length) {
				if (r[i] == s[j]) {
					rs[k] = r[i];
					k++;
				}
				j++;
			}
			i++;
		}
		return rs;
	}

	@Override
	public char[] mergeJoin(char[] r, char[] s) {
		char[] rs = new char[Constants.RS_SIZE];
		int i = 0, j = 0;
		while ((i < r.length)  && (j < s.length)) {
			if (r[i] == s[j]) {
				rs[i] = r[i];
				i++;
			} else if (r[i] > s[j]) {
				j++;
			} else if (r[i] < s[j]) {
				i++;
			}
		}
		return rs;
	}

	@Override
	public char[] mergeJoinWithDuplicates(char[] r, char[] s) {
		char[] rs = new char[Constants.R_SIZE];
		int i = 0, j = 0, k = 0;
		while ((i < r.length) && (j < s.length)) {
			if (r[i] == s[j]) {
				while ((i < r.length) && (r[i] == s[j])) {
					while ((j < s.length) && (r[i] == s[j])) {
						rs[i] = r[i];
						j++;
					}
					i++;
					j = k;
				}
			} else if (r[i] > s[j]) {
				j++;
			} else if (r[i] < s[j]) {
				i++;
			}
		}
		return rs;
	}
	
	@Override
	public void externalNestedLoopJoin() throws IOException, InterruptedException {
		int r_block = 0, s_block = 0;
		int idx = 0, k = 0;
		int[] rs_tmp = new int[Constants.BLOCK_SIZE];
		while (r_block < Descriptor.getRd().length) {
			int[] r = new int[Constants.BLOCK_SIZE];
			r = airtable.selectBlock(Descriptor.getRd()[r_block]);
			s_block = 0;
			while (s_block < Descriptor.getSd().length) {
				int[] s = new int[Constants.BLOCK_SIZE];
				s = airtable.selectBlock(Descriptor.getSd()[s_block]);
				int[] rs = new int[Constants.BLOCK_SIZE];
				rs = nestedLoopJoin(r, s);
				idx = 0;
				while (idx < rs.length) {
					if (r_block + 1 == Descriptor.getRd().length && s_block + 1 == Descriptor.getSd().length) {
						airtable.createTables(RS.class, rs_tmp);
						System.out.println(Arrays.toString(rs_tmp));
					}
					if (k == Constants.BLOCK_SIZE) {
						airtable.createTables(RS.class, rs_tmp);
						System.out.println(Arrays.toString(rs_tmp));
						rs_tmp = new int[Constants.BLOCK_SIZE];
						k = 0;
						break;
					}
					if (rs[idx] != 0) {
						rs_tmp[k] = rs[idx];
						idx++;
						k++;
					} else {
						break;
					}
				}
				s_block++;
			}
			r_block++;
		}
	}
	
	@Override
	public int[] nestedLoopJoin(int[] r, int[] s) {
		int[] rs = new int[Constants.BLOCK_SIZE];
		int i = 0, j = 0, k = 0;
		while (i < r.length) {
			j = 0;
			while (j < s.length) {
				if ((r[i] == 0) || (s[j] == 0)) {
					break;
				}
				if (r[i] == s[j]) {
					rs[k] = r[i];
					k++;
				}
				j++;
			}
			i++;
		}
		return rs;
	}
	
}
