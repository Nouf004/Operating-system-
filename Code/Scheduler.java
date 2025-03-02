import java.util.*;
class Scheduler{
   List<Process> processes;
   PriorityQueue<Process> readyQueue;
   int currentTime;
   int contextSwitchTime;

   Scheduler(List<Process> processes){
      this.processes=processes;
      readyQueue=new PriorityQueue<>();
   }
   public void run(){
      int completingtime=0;
      Process currentProces = null;
      int processIndex = 0; 
      while (completingtime < processes.size()) {
      
         while (processIndex < processes.size() && processes.get(processIndex). Arrival_Time == currentTime) {
            readyQueue.add(processes.get(processIndex));//*
            processIndex++;
         }
      }
      if (currentProces != null ){
         if( currentProces.Remaining_Time == 0){
            currentProces.Completion_Time = currentTime;
            completingtime++;
            currentProces = null;
            currentTime += contextSwitchTime; 
         }
      }
      if (!readyQueue.isEmpty()) {
         Process nextProcess = readyQueue.poll();
      
         if (currentProces != nextProcess) { 
            if (currentProces != null) readyQueue.add(currentProces); 
            currentProces = nextProcess;
         }
      
         currentProces.Remaining_Time--;
      }
   
      currentTime++;
   }
}