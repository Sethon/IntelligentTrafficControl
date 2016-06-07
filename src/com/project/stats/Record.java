package com.project.stats;

import java.util.Hashtable;

public class Record {
	private Hashtable<String, Double> data = new Hashtable<String, Double>();
	
	public Record(){
	}
	
	public void setValue(String key, double value){
		data.put(key, new Double(value));
	}
	
	public double getValue(String key){
		return data.get(key).doubleValue();
	}
	
	public void merge(Record other){
		for(String key: other.getKeys()){
			setValue(key, new Double(other.getValue(key)));
		}
	}
	
	//JSON is a format for serializing data that's easy for other programs to parse.
	public String toJSON(){
		String[] pairs = new String[data.size()];
		int i=0;
		for(String key: data.keySet()){
			pairs[i] = "\""+key+"\"" + ":" + data.get(key);
			i++;
		}
		return "{\n" + String.join(",\n", pairs) + "\n}";
	}
	
	public String toCSVRow(){
		String[] values = new String[data.size()];
		int i=0;
		for(String key: getKeys()){
			values[i] = getValue(key) + "";
			i++;
		}
		return String.join(",", values);
	}
	
	public String getCSVHeaderRow(){
		return String.join(",", getKeys());
	}
	
	public Iterable<String> getKeys(){
		return data.keySet();
	}
}
