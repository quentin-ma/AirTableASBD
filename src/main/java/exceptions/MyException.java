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
 * Remarques : Class contenant l'ensemble des exceptions propres à notre programme.
 */
package exceptions;

@SuppressWarnings("serial")
public class MyException extends Exception {

	public MyException(String message) {
		super(message);
	}
	
	public static class ClassDoesNotExistException extends MyException {
		
		public ClassDoesNotExistException(String message) {
			super(message);
		}
		
	}

}
