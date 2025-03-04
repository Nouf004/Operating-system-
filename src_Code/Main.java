import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int p = scanner.nextInt();

        for (int i = 0; i < p; i++) {
            System.out.print("Enter arrival time and burst time for P" + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        // Print process details in the expected format
        System.out.println("\nNumber of processes= " + p + " " + getProcessIDs(processes));
        System.out.println("Arrival times and burst times as follows:");
        for (Process pObj : processes) {
            System.out.printf("P%d: Arrival time = %d, Burst time = %d ms\n", pObj.Process_ID, pObj.Arrival_Time, pObj.Burst_Time);
        }

        System.out.println("Scheduling Algorithm: Shortest remaining time first");
        int contextSwitch = 1 ;
        System.out.println("Context Switch: " + contextSwitch + " ms");

        Scheduler scheduler = new Scheduler(processes, contextSwitch);
        scheduler.run();
    }

    private static String getProcessIDs(List<Process> processes) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < processes.size(); i++) {
            sb.append("P").append(processes.get(i).Process_ID);
            if (i < processes.size() - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}
