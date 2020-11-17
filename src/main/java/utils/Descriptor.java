package utils;

public class Descriptor {

	public static int[] rd;
	public static int[] sd;
	public static int[] rsd;
	
	public Descriptor() {
		rd = new int[10];
		sd = new int[10];
		rsd = new int[10];
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
