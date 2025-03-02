import java.util.*;
public class Main{
   public static void main(String []args){
      Scanner input= new Scanner(System.in);
      int num_process;
      int ArrTime,BrustTime;
      int idProcess;
      
      List<Process> processes=new ArrayList<>();
      
      System.out.println("Number of processes=");
      
      num_process=input.nextInt();
   
      for(int i=0;i<num_process;i++){
         System.out.println("Arrival times and burst times as follows:"+(i+1)+":");
         ArrTime=input.nextInt();
         BrustTime=input.nextInt();
         processes.add(new Process(i+1,ArrTime,BrustTime));
      }//end for
         
   //Scheduler scheduler = new Scheduler(processes);
        //scheduler.run();
   
   
   
   }//main
}//class