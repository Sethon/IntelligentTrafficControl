package com.project.stats;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFileChooser;

public class RecordSet {
	private ArrayList<Record> records = new ArrayList<Record>();
	
	public RecordSet(){
	}
	
	public void addRecord(Record rec){
		records.add(rec);
	}
	
	//JSON is a data serialization format that'seasy to read for other applications.
	public String toJSON(){
		String[] objects = new String[records.size()];
		for(int i=0; i<records.size(); i++){
			objects[i] = records.get(i).toJSON();
		}
		return "[" + String.join(",", objects) + "]";
	}
	
	public String toCSV(){
		String[] rows = new String[records.size() + 1];
		
		rows[0] = records.get(0).getCSVHeaderRow();
		for(int i=0; i<records.size(); i++){
			rows[i+1] = records.get(i).toCSVRow();
		}
		return String.join("\n", rows);
	}
	
	public void saveCSVFile(){
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setSelectedFile(new File("testResults.csv"));
		
		int response = fc.showSaveDialog(null);
		if(response == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile();
			try {
				FileOutputStream out = new FileOutputStream(f);
				PrintWriter pw = new PrintWriter(out);
				pw.write(toCSV());
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public RecordSet makeSummary(){
		RecordSet s = new RecordSet();
		Hashtable<String, Double> carSpeeds = new Hashtable<String, Double>();
		Hashtable<String, Double> carWaitTimes = new Hashtable<String, Double>();
		int ticks = 0;
		for(Record rec: this.records){
			String carID = "" + rec.getValue("carID");
			double prevSpeed = carSpeeds.getOrDefault(carID, new Double(0));
			double currentSpeed = rec.getValue("velocity");
			double prevWaitTime = carWaitTimes.getOrDefault(carID, new Double(0));
			if(currentSpeed == 0){
				carWaitTimes.put(carID, prevWaitTime + 1);
			}
			carSpeeds.put(carID, prevSpeed + currentSpeed);
			
			ticks = Math.max(ticks, (int)rec.getValue("tick"));
		}
		
		for(String car: carSpeeds.keySet()){
			Record rec = new Record();
			rec.setValue("CarID", Double.parseDouble(car));
			rec.setValue("avgSpeed", carSpeeds.get(car)/ticks);
			rec.setValue("waitTime", carWaitTimes.getOrDefault(car, new Double(0)));
			rec.setValue("ticks", ticks);
			s.addRecord(rec);
		}
		return s;
	}
}
