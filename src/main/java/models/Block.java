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
 * Remarques :  Cette classe concerne la gestion des blocs sur disque.
 */
package models;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.R;
import dto.RS;
import dto.S;
import exceptions.MyException;
import exceptions.MyException.ClassDoesNotExistException;
import utils.Utils;

public class Block {
	
	private Disk disk;
	
	public Block() {
		this.disk = new Disk();
	}

	public int[] selectBlock(int descriptor) throws IOException {
		String jsonResponse = disk.read(descriptor);
		return parser(jsonResponse, descriptor);
	}
	
	private int[] parser(String json, int descriptor) {
		int[] r = new int[10];
		System.out.println(json);
		String string = json;
		Pattern pattern = Pattern.compile(
				"(?:\\\"|\\')(?<key>[^\"]*)(?:\\\"|\\')(?=:)(?:\\:\\s*)(?:\\\"|\\')?(?<value>true|false|[0-9a-zA-Z\\+\\-\\,\\.\\$]*)"
				);
		final Matcher matcher = pattern.matcher(string);
		int cpt = 0, idx = 0;
		while (matcher.find()) {
		    for (int i = 2; i <= matcher.groupCount(); i++) {
		    	cpt++;
		        if (cpt % 4 == 0) {
		        	if (idx == Constants.BLOCK_SIZE) {
		        		break;
		        	}
		        	r[idx++] = Integer.parseInt(matcher.group(i));
		        }
		    }
		}
		return r;
	}
	
	public void createRSBlock(Class<?> o, int[] array, int descriptor) throws IOException, InterruptedException, ClassDoesNotExistException {
		if (o.equals(RS.class)) {
			String body = createRecord(descriptor, array);
			String responseBody = disk.write(descriptor, body);
			System.out.println(responseBody);
		} else {
			throw new MyException.ClassDoesNotExistException("Provided class is not implemented yet.");
		}
	}
	
	public void createBlock(Class<?> o, int[] array) throws IOException, InterruptedException, ClassDoesNotExistException {
		int row = 0, idx_block = 0, cpt = 0;
		int block_size = 10;
		String body = "";
		for (int i = 0; i < array.length && block_size >= 0; i += block_size) {
			int[] block = new int[block_size];
			row = 0;
			for (int j = i; j < i + block_size; j++) {
				block[row++] = array[j];
			}
			if (o.equals(R.class)) {
				body = createRecord(Descriptor.getRd()[cpt], block);
				disk.write(Descriptor.getRd()[cpt], body);
			} else if (o.equals(S.class)) {
				body = createRecord(Descriptor.getSd()[cpt], block);
				disk.write(Descriptor.getSd()[cpt], body);
			} else {
				throw new MyException.ClassDoesNotExistException("Provided class is not implemented yet.");
			}
			cpt++;
			idx_block++;
			System.out.println(body);
			block_size = (idx_block < array.length / 10) ? 10 : (array.length - (idx_block * 10));
		}
	}
	
	private String createRecord(int descriptor, int[] array) {
		String blockName = Utils.decodeASCII(descriptor);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"records\": [");
		for (int j = 0; j < array.length - 1; j++) {
			sb.append("{");
			sb.append("\"fields\": {");
			sb.append("\"" + blockName + "\": \"" + array[j] + "\"");
			sb.append("}");
			sb.append("},");
		}
		sb.append("{");
		sb.append("\"fields\": {");
		sb.append("\"" + blockName + "\": \"" + array[array.length - 1] + "\"");
		sb.append("}");
		sb.append("}");
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}
	
}
