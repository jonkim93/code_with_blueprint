package Scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	/** takes in a file name for a csv file and parses the csv file for data
	 * **/
	public static ArrayList<String> parseCSV(String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		ArrayList<String> lines = new ArrayList<String>();
		String newline;
		while ((newline = reader.readLine())!=null){
			lines.add(newline);
		}
		reader.close();
		return lines;
	}
	
	/** takes the arraylist returned by parseCSV and makes an arrayList of Worker objects
	 * **/
	public static ArrayList<Worker> makeListWorkers(ArrayList<String> parsed){
		ArrayList<Worker> workers = new ArrayList<Worker>();
		String[] data = null;
		for (String s: parsed){
			data = s.split(",");
			String[] times = null;
			times = data[2].split(";");
			ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
			for(String str: times){
				timeBlocks.add(new TimeBlock(data[0],str.substring(0,3),Integer.parseInt(str.substring(3,7)), 
						Integer.parseInt(str.substring(8,12))));
			}
			Worker w = new Worker(data[0],Integer.parseInt(data[1]),timeBlocks);
			workers.add(w);
		}
		return workers;
	}
	
	public static void main(String[] args){
		try {
			System.out.println("here");
			ArrayList<Worker> workers = makeListWorkers(parseCSV("data.csv"));
			for (Worker w:workers){
				System.out.println(w.getName());
				for (TimeBlock t:w.getAvailabilities()){
					System.out.println(t);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
