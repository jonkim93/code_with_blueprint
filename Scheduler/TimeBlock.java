package Scheduler;

public class TimeBlock implements Comparable<TimeBlock>{
	private String name;
	private String day;
	private int start;
	private int end;
	
	public int compareTo(TimeBlock b) {
		int s = b.getStart();
		if (start < s) {
			return -1;
		} else if (start == s) {
			return 0;
		} else {
			return 1;
		}
	}
	
	String getDay() {
		return day;
	}
	
	public TimeBlock(String n, String d, int s, int e) {
		name = n;
		day = d;
		start = s;
		end = e;
	}
	int getDayInt(){
		if (day=="Mon"){
			return 1;
		} else if (day=="Tue"){
			return 2;
		} else if (day=="Wed"){
			return 3;
		} else if (day=="Thu"){
			return 4;
		} 
		return 5;
	}
	String getName(){
		return name;
	}
	double getDuration() {
		double dur = end - start;
		if (dur % 100 == 0) {
			return (dur / 100);
		} else {
			double mod = dur % 100;
			dur = dur - mod;
			mod = mod / 60;
			return ((dur / 100) + mod);
		}
	}
	
	int getStart() {
		return start;
	}
	
	int getEnd() {
		return end;
	}
	
	/* If this overlaps with another one. */
	boolean overlap(TimeBlock t) {
		if (t.getEnd() < start) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String s = this.day;
		s += (" "+start+"-"+end+"("+this.name+")");
		return s;
	}
}
