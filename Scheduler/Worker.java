package Scheduler;

import java.util.ArrayList;

public class Worker {
	private String name;
	private double minRequiredHours;
	private ArrayList<TimeBlock> availabilities;
	
	public Worker(String name, double hours, ArrayList<TimeBlock> availabilities){
		this.name = name;
		this.minRequiredHours = hours;
		this.availabilities = availabilities;
	}
	
	public String getName(){
		return name;
	}
	public double getMinHours(){
		return minRequiredHours;
	}
	public ArrayList<TimeBlock> getAvailabilities(){
		return availabilities;
	}
}
