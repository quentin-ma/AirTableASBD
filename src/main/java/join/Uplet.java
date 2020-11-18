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

import java.util.ArrayList;
import java.util.List;

public class Uplet {

	private char[] rs_c;
	private List<String> rs;
	private List<String> listLeft;
	private List<String> listRight;
	
	private String[] left;
	private String[] right;
	
	public Uplet(String[] left, String[] right) {
		this.rs = new ArrayList<>();
		this.rs_c = new char[100];
		this.listLeft = new ArrayList<>();
		this.listRight = new ArrayList<>();
		this.left = left;
		this.right = right;
	}
	
	public char[] getRs_c() {
		return this.rs_c;
	}
	
	public String[] getLeftUplet() {
		return this.left;
	}
	
	public String[] getRightUplet() {
		return this.right;
	}
	
	public List<String> getRs() {
		return this.rs;
	}

	public List<String> getListLeft() {
		return listLeft;
	}

	public void setListLeft(List<String> listLeft) {
		this.listLeft = listLeft;
	}

	public List<String> getListRight() {
		return listRight;
	}

	public void setListRight(List<String> listRight) {
		this.listRight = listRight;
	}
}
