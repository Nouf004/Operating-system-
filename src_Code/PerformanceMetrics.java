import java.util.*;

class PerformanceMetrics {
        public static void printResults(List<String> timeline, List<Process> processes, int currentTime, int contextSwitches, int contextSwitchTime) {
            double totalTAT = 0, totalWT = 0;
            for (Process p : processes) {
                totalTAT += p.Turnaround_Time;
                totalWT += p.Waiting_Time;
            }

            double avgTAT = totalTAT / processes.size();
            double avgWT = totalWT / processes.size();
            double cpuUtilization = ((double) (currentTime - (contextSwitches * contextSwitchTime)) / currentTime) * 100;

            GanttChart.displayGanttChart(timeline);

            System.out.println("Performance Metrics:");
            System.out.printf("Average Turnaround Time: %.0f ms\n", avgTAT);
            System.out.printf("Average Waiting Time: %.1f ms\n", avgWT);
            System.out.printf("CPU Utilization: %.2f\n", cpuUtilization);
        }

}
