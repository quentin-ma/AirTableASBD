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
 * Remarques :  Interface de Disk exposant les méthodes read et write.
 */
package models;

import java.io.IOException;

public interface IDisk {
	
	public String read(int descriptor) throws IOException, InterruptedException ;
	public String write(int descriptor, String body) throws IOException, InterruptedException;

}
