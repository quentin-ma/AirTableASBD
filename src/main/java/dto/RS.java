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
 * Remarques : Cette classe sert à représenter les objets que l'on transfert.
 * Elle suit le pattern DTO pour Data Transfer Object.
 * Ce pattern est très commode lorsqu'il s'agit de contruire des objets robustes 
 * afin d'effectuer un "mapping" des données.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

public class RS {

	public List<Record> records;
	
	public RS() {
		this.records = new ArrayList<RS.Record>();
	}
	
	public static class Record {
		
		public String id;
		
		public Fields fields;
		
		public static class Fields {
			
			public String RS;

			public String getRS() {
				return RS;
			}

			public void setRS(String rs) {
				RS = rs;
			}
		}
		
		public String createdTime;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Fields getFields() {
			return fields;
		}

		public void setFields(Fields fields) {
			this.fields = fields;
		}

		public String getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}	
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
	
}