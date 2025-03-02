import java.util.*;
class PerformanceMetrics {
    public static void calculateMetrics(List<Process> processes, int totalExecutionTime, int currentTime) {
        int totalTurnaroundTime = 0, totalWaitingTime = 0;

        for (Process p : processes) {
            p.Turnaround_Time = p.Completion_Time - p.Arrival_Time;
            p.Waiting_Time = p.Turnaround_Time - p.Burst_Time;
            totalTurnaroundTime += p.Turnaround_Time;
            totalWaitingTime += p.Waiting_Time;
        }

        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();
        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double cpuUtilization = ((double) totalExecutionTime / currentTime) * 100;

        System.out.println("\nPerformance Metrics:");
        System.out.printf("Average Turnaround Time: %.2f", avgTurnaroundTime);
        System.out.printf("Average Waiting Time: %.2f", avgWaitingTime);
        System.out.printf("CPU Utilization: %.2f", cpuUtilization);
    }
}