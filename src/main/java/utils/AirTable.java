package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.R;
import dto.RS;
import dto.S;
import join.Constants;

public class AirTable {
	
	public static final String KEY = "keyDM0og9WhJ16ZN5";
	public static final String URL = "https://api.airtable.com/v0/appUQ8AkhUVcQAdHp/";
	
	public void generateDescriptor(Class<?> o) {
		int bufferSize = 0;
		if (o.equals(R.class)) {
			bufferSize = (int) Math.ceil((double) Constants.R_ELEMENTS / Constants.BLOCK_SIZE);
		} else if (o.equals(S.class)) {
			bufferSize = (int) Math.ceil((double) Constants.S_ELEMENTS / Constants.BLOCK_SIZE);
		} else if (o.equals(RS.class)) {
			bufferSize = (int) Math.ceil((double) Constants.RS_ELEMENTS / Constants.BLOCK_SIZE);
		}
		int[] td = new int[bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			String str_id = o.getSimpleName() + String.format("%02d", i);
			int asc_id = Utils.encodeToASCII(str_id);
			td[i] = asc_id;
		}
		if (o.equals(R.class)) {
			Descriptor.setRd(td);
		} else if (o.equals(S.class)) {
			Descriptor.setSd(td);
		} else if (o.equals(RS.class)) {
			Descriptor.setRsd(td);
		}
	}
	
	public void createRSBlock(Class<?> o, int[] array, int descriptor) throws IOException, InterruptedException {
		if (o.equals(RS.class)) {
			String body = createRecord(descriptor, array);
			String responseBody = sendRequest(descriptor, body);
			System.out.println(responseBody);
		}
	}
	
	public void createTables(Class<?> o, int[] array) throws IOException, InterruptedException {
		int row = 0, idx_block = 0, cpt = 0;
		int block_size = 10;
		String body = "";
		for (int i = 0; i < array.length && block_size >= 0; i += block_size) {
			int[] bloc = new int[block_size];
			row = 0;
			for (int j = i; j < i + block_size; j++) {
				bloc[row++] = array[j];
			}
			if (o.equals(R.class)) {
				body = createRecord(Descriptor.getRd()[cpt], bloc);
				sendRequest(Descriptor.getRd()[cpt], body);
			} else if (o.equals(S.class)) {
				body = createRecord(Descriptor.getSd()[cpt], bloc);
				sendRequest(Descriptor.getSd()[cpt], body);
			} else if (o.equals(RS.class)) {
				body = createRecord(Descriptor.getRsd()[cpt], bloc);
				sendRequest(Descriptor.getRsd()[cpt], body);
			}
			cpt++;
			idx_block++;
			System.out.println(body);
			block_size = (idx_block < array.length / 10) ? 10 : (array.length - (idx_block * 10));
		}
	}
	
	private String sendRequest(int asc_id, String body) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL + asc_id))
				.header("Authorization", "Bearer " + KEY)
				.header("Content-type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	
	private String createRecord(int id, int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"records\": [");
		for (int j = 0; j < array.length - 1; j++) {
			sb.append("{");
			sb.append("\"fields\": {");
			sb.append("\"" + id + "\": \"" + array[j] + "\"");
			sb.append("}");
			sb.append("},");
		}
		sb.append("{");
		sb.append("\"fields\": {");
		sb.append("\"" + id + "\": \"" + array[array.length - 1] + "\"");
		sb.append("}");
		sb.append("}");
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}
	
	public int[] selectBlock(int descriptor) {
		String jsonResponse = selectRecords(descriptor);
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
	
	private String selectRecords(int descriptor) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL + descriptor))
				.header("Authorization", "Bearer " + KEY)
				.build();
		return client
				.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body).join();
	}
	
}
