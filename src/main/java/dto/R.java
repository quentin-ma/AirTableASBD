package dto;

import java.util.ArrayList;
import java.util.List;

public class R {

	public List<Record> records;
	
	public R() {
		this.records = new ArrayList<R.Record>();
	}
	
	public static class Record {
		
		public String id;
		
		public Fields fields;
		
		public static class Fields {
			
			public String R;

			public String getR() {
				return R;
			}

			public void setR(String r) {
				R = r;
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
	