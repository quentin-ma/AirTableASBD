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
 * Remarques : Point d'entrée de notre programme.
 * Exécute la fonction principale du programme: effectuer une jointure sur blocs disques
 * représentés par les tables sur AirTable.
 */
package join;

import java.io.IOException;

import dto.R;
import dto.RS;
import dto.S;
import exceptions.MyException.ClassDoesNotExistException;
import utils.Block;
import utils.Disk;
import utils.Utils;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, ClassDoesNotExistException {
		Utils<R> utils_r = new Utils<>();
		int[] asc_r = utils_r.generateASCII(R.class);	
		
		Utils<S> utils_s = new Utils<>();
		int[] asc_s = utils_s.generateASCII(S.class);
		
		Disk disk = new Disk();
		disk.generateDescriptor(R.class);
		disk.generateDescriptor(S.class);
		disk.generateDescriptor(RS.class);
		
		Block block = new Block();
//		block.createBlock(R.class, asc_r);
//		block.createBlock(S.class, asc_s);
		
		Join join = new Join();
		join.externalNestedLoopJoin();
	}
	
}
