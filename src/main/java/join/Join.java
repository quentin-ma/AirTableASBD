/**
 * TP n°4
 * 
 * Titre du TP : Block Nested Loop Airtable
 *
 * Date : 17 novembre 2020
 * 
 * Nom  : MA
 * Prenom : Quentin
 *
 * email : quentin.ma@etu.u-paris.fr
 * 
 * Remarques : Implémentation des fonctions de jointure.
 */
package join;

import java.io.IOException;
import java.util.Arrays;

import dto.RS;
import exceptions.MyException.ClassDoesNotExistException;
import models.Block;
import models.Constants;
import models.Descriptor;
import utils.Utils;

public class Join implements IJoin {

	private Block block;
	
	public Join() throws IOException {
		this.block = new Block();
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
	public void externalNestedLoopJoin() throws IOException, InterruptedException, ClassDoesNotExistException {
		int r_block = 0, s_block = 0, rs_block = 0;
		int idx = 0, idx_rsd = 0, k = 0;
		int[] rsd = new int[Constants.BUFFER_SIZE];
		int[] rs_tmp = new int[Constants.BUFFER_SIZE];
		while (r_block < Descriptor.getRd().length) {
			int[] r = new int[Constants.BUFFER_SIZE];
			r = block.selectBlock(Descriptor.getRd()[r_block]);
			s_block = 0;
			while (s_block < Descriptor.getSd().length) {
				int[] s = new int[Constants.BUFFER_SIZE];
				s = block.selectBlock(Descriptor.getSd()[s_block]);
				int[] rs = new int[Constants.BUFFER_SIZE];
				rs = nestedLoopJoin(r, s);
				idx = 0;
				while (idx < rs.length) {
					if (idx_rsd == rsd.length) {
						rsd = new int[Constants.BUFFER_SIZE];
					}
					if ((r_block + 1 == Descriptor.getRd().length) && (s_block + 1 == Descriptor.getSd().length)) {
						String str_id = RS.class.getSimpleName() + String.format("%02d", idx_rsd);
						int asc_id = Utils.encodeToASCII(str_id);
						rsd[idx_rsd] = asc_id;
						Descriptor.setRsd(rsd);
						block.createRSBlock(RS.class, rs_tmp, Descriptor.getRsd()[rs_block]);
						System.out.println(Arrays.toString(rs_tmp));
						break;
					}
					if (k == Constants.BLOCK_SIZE) {
						String str_id = RS.class.getSimpleName() + String.format("%02d", idx_rsd);
						int asc_id = Utils.encodeToASCII(str_id);
						rsd[idx_rsd] = asc_id;
						Descriptor.setRsd(rsd);
						block.createRSBlock(RS.class, rs_tmp, Descriptor.getRsd()[rs_block]);
						rs_block++;
						idx_rsd++;
						System.out.println(Arrays.toString(rs_tmp));
						rs_tmp = new int[Constants.BUFFER_SIZE];
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
