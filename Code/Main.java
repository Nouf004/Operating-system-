import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for P" + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        System.out.println("\nStarting Scheduler...");
        Scheduler scheduler = new Scheduler(processes, 1);
        scheduler.run();
        System.out.println("Process Scheduling Completed!");


        //Rest of the code
        System.out.println("\nNumber of processes= " + n);
        //print the processes
        System.out.println("\nArrival times and burst times as follows:");
        for (Process p : processes) {
            System.out.printf("P%d: Arrival time = %d, Burst time = %d ms",p.Process_ID, p.Arrival_Time, p.Burst_Time);
        }
        //prints scheduling algo and CS...
        //gantt chart...
        //Performance Metrics...


    }
}
