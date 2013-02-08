## Java/Python 2: Scheduler

Jonathan Kim, Noah Gilmore, Melissa Huang 

Run ScheduleGenerator main and there are some automatic TimeBlocks to demonstrate the output, though the algorithm is a little buggy. 

The program intakes a CSV file formatted like such: 
required: unique names, military time, Mon-Fri, 0800-1700
Name,hours_required,Day[start]-[end];Day[start]-[end]

Jon,3,Mon0800-1100;Tue1500-1700;Thu1300-1700;Fri0800-1000
Noah,3,Mon1100-1230;Tue0800-1000;Wed1200-1700
Melissa,3,Mon1230-1700;Wed0800-1200;Thu0900-1200;Fri1000-1230
Jay,3,Tue1000-1500;Thu0800-0900;Fri1500-1700

It parses the CSV file, runs the algorithm, and outputs to output.csv.

This program is structured around the TimeBlock and Worker classes and uses an algorithm to fill as much time as possible each day. 