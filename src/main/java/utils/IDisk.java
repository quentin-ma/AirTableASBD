package utils;

import java.io.IOException;

public interface IDisk {
	
	public String read(int descriptor) throws IOException, InterruptedException ;
	public String write(int descriptor, String body) throws IOException, InterruptedException;

}
