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
 * Remarques :  Cette class est à l'interface entre notre programme et AirTable.
 * C'est elle qui gère l'envoie des données à travers une requête POST
 * mais aussi la réception des données à travers une requête GET.
 * Le parser et le StringBuilder sont inhérents aux méthodes de sélections et de créations.
 */
package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

import dto.R;
import dto.RS;
import dto.S;

public class Disk implements IDisk {

	public Disk()  {}
	
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

	@Override
	public String read(int descriptor) throws IOException {
		String blockName = Utils.decodeASCII(descriptor);
		FileReader file = new FileReader(Utils.filePathProperties("application.properties"));
		Properties properties = new Properties();
		properties.load(file);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(properties.getProperty("api_url") + blockName))
				.header("Authorization", "Bearer " + properties.getProperty("api_key"))
				.build();
		return client
				.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body).join();
	}
	
	@Override
	public String write(int descriptor, String body) throws IOException, InterruptedException {
		String blockName = Utils.decodeASCII(descriptor);
		FileReader file = new FileReader(Utils.filePathProperties("application.properties"));
		Properties properties = new Properties();
		properties.load(file);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(properties.getProperty("api_url") + blockName))
				.header("Authorization", "Bearer " + properties.getProperty("api_key"))
				.header("Content-type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	
}
