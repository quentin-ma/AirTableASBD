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
 * Remarques : Cette classe expose les descripteurs principaux de notre programme.
 * Chaque descripteur identifie les blocs disques.
 * RD(R)
 * RS(S)
 * RSD(RS)
 */
package models;

public final class Descriptor {

	public static int[] rd;
	public static int[] sd;
	public static int[] rsd;
	
	public Descriptor() {
		rd = new int[Constants.BUFFER_SIZE];
		sd = new int[Constants.BUFFER_SIZE];
		rsd = new int[Constants.BUFFER_SIZE];
	}

	/**
	 * @return the rd
	 */
	public static int[] getRd() {
		return rd;
	}

	/**
	 * @param rd the rd to set
	 */
	public static void setRd(int[] rd) {
		Descriptor.rd = rd;
	}

	/**
	 * @return the sd
	 */
	public static int[] getSd() {
		return sd;
	}

	/**
	 * @param sd the sd to set
	 */
	public static void setSd(int[] sd) {
		Descriptor.sd = sd;
	}

	/**
	 * @return the rsd
	 */
	public static int[] getRsd() {
		return rsd;
	}

	/**
	 * @param rsd the rsd to set
	 */
	public static void setRsd(int[] rsd) {
		Descriptor.rsd = rsd;
	}

	
}
