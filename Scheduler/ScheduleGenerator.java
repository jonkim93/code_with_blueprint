package Scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.IOException;

public class ScheduleGenerator {

	private ArrayList<Worker> workers;
	private ArrayList<TimeBlock> timeblocks;
	private HashMap<Worker, Integer> workerHours;
	private HashMap<String, Worker> workerDirectory;

	public ScheduleGenerator(ArrayList<Worker> workers) {
		this.workers = workers;
		this.timeblocks = new ArrayList<TimeBlock>();
		this.workerHours = new HashMap<Worker, Integer>();
		this.workerDirectory = new HashMap<String, Worker>();

		for (int i=0; i<workers.size(); i++) {
			this.workerHours.put(workers.get(i), new Integer(0));
			this.timeblocks.addAll(workers.get(i).getAvailabilities());
		}

		Collections.sort(this.timeblocks);
	}

	// this algorithm sucks, and it doesnt work, haha
	public ArrayList<TimeBlock> generate() {
		ArrayList<TimeBlock> ret = new ArrayList<TimeBlock>();

		// go through each day, and do two passes
		// one going through iteratively and finding the timeblocks that fit in order
		// and one starting with the largest timeblock and seeing if tey conflict.
		String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};

		int j=0;
		for (int i=0; i<days.length; i++) {
			TimeBlock thisTimeblock = this.timeblocks.get(j);

			// get all the timeblocks for today
			ArrayList<TimeBlock> thisDaysTimes = new ArrayList<TimeBlock>();
			// this ext line is super ghetto
			while (thisTimeblock.getDayInt() == i+1) {
				try {
					thisDaysTimes.add(thisTimeblock);
					j++;
					thisTimeblock = this.timeblocks.get(j);
				} catch (Exception e) {
					break;
				}
			}

			int time = 800;
			while (time <= 1700) {

				//incredibly stupid way to do this but i have to do this FAST
				for (int k=0; k<thisDaysTimes.size(); k++) {
					if (thisDaysTimes.get(k).getStart() == time) {
						TimeBlock thisTime = thisDaysTimes.get(k);
						ret.add(thisTime);

						// ignore all the days with the same start time
						while (thisDaysTimes.get(k+1).getStart() == time) {
							k++;
						}

						time = thisTime.getEnd();
						break;
					}
				}

				time += 30;
			}
			// then go on to the next day
		}

		return ret;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Worker> workers = Parser.makeListWorkers(Parser.parseCSV("data.csv"));
		ScheduleGenerator g = new ScheduleGenerator(workers);
		ArrayList<TimeBlock> l = g.generate();

		Writer.writeToCSV("output.csv", l);
	}
}