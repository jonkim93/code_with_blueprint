package Scheduler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	public static void writeToCSV(String outputName, ArrayList<TimeBlock> timeBlocks) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
		writer.write(",Monday,Tuesday,Wednesday,Thursday,Friday");
		writer.newLine();
		String[][] overallLines = new String[18][6];
		int[] hours = {800,830,900,930,1000,1030,1100,1130,1200,1230,1300,1330,1400,1430,1500,1530,1600,1630};
		for (int x =0;x<18;x++){
			for (int y=0;x<6;x++){
				overallLines[x][y] = "-";
			}
		}
		for (TimeBlock t:timeBlocks){
			for (int x=0; x<hours.length;x++){
				overallLines[x][0]=Integer.toString(hours[x]);
				if (hours[x]<t.getEnd() && hours[x]>=t.getStart()){
					if (overallLines[x][t.getDayInt()]==null){
						overallLines[x][t.getDayInt()] = t.getName();
					} else {
						overallLines[x][t.getDayInt()] += t.getName();
					}
				}
			}
		}
		for(int i = 0; i<18; i++){
			String s = "";
			for (int n = 0; n<6; n++){
				s += (overallLines[i][n]+",");
			}
			writer.append(s);
			writer.newLine();
		}
		writer.close();
	}
	
	
	/** THIS IS TEST CODE; RUN IT IF YOU LIKE
	 * */
	public static ArrayList<TimeBlock> generate(){
		ArrayList<TimeBlock> blocks = new ArrayList<TimeBlock>();
		blocks.add(new TimeBlock("A", "Mon", 800, 1500));
	    blocks.add(new TimeBlock("B", "Wed", 900, 1200));
	    blocks.add(new TimeBlock("C", "Tue", 1000, 1400));
	    blocks.add(new TimeBlock("D", "Tue", 800, 1000));
	    blocks.add(new TimeBlock("E", "Thu", 1200, 1500));
	    blocks.add(new TimeBlock("F", "Fri", 800, 1400));
	    blocks.add(new TimeBlock("G", "Mon", 1500, 1700));
		return blocks;
	}
	
	public static void main(String[] args){
		ArrayList<TimeBlock> timeBlocks = generate();
		try {
			writeToCSV("output.csv",timeBlocks);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
