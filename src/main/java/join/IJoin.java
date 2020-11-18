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
 * Remarques : Interface qui expose les fonctions principales de jointure pour notre programme.
 */
package join;

import java.io.IOException;

public interface IJoin {
	
	public char[] join(char[] r, char[] s);
	public char[] mergeJoin(char[] r, char[] s);
	public char[] mergeJoinWithDuplicates(char[] r, char[] s);
	public int[] nestedLoopJoin(int[] r, int[] s);
	
	public void externalNestedLoopJoin() throws IOException, InterruptedException;
	
}
