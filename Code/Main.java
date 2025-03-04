import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>(); //creating a list of processes

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for P" + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        System.out.print("\nNumber of processes = " + processes.size()+" (");
        for (int i = 0; i < processes.size(); i++) {
            System.out.print("P" + (i + 1)); // Print process name
            if (i < processes.size() - 1) { // Add a comma and space if not the last element
                System.out.print(", ");
            }
        }
        System.out.print(")");

        int contextSwitchTime = 1;
        Scheduler scheduler = new Scheduler(processes, contextSwitchTime);
        scheduler.run();
    }
}
