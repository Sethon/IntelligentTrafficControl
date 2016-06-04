package com.project.stats;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

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
}
