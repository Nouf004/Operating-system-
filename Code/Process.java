import java.util.*;
 class  Process implements Comparable<Process>{
   int Process_ID;
   int Arrival_Time,Burst_Time;
   int Remaining_Time;
   int Completion_Time;
   int Waiting_Time;
   int Turnaround_Time;


   Process(int pid, int arrivalTime, int burstTime){      
      Process_ID = pid;
      Arrival_Time = arrivalTime;    
      Burst_Time = burstTime;
      Remaining_Time = burstTime; 
   }
   
   public int compareTo(Process p) {
      if (this.Remaining_Time != p.Remaining_Time) {
         return Integer.compare(this.Remaining_Time, p.Remaining_Time);//priorty
            
      }
      return Integer.compare(this.Arrival_Time, p.Arrival_Time); // FCFS 
    
   }
}///class