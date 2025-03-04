import java.util.*;

public class Scheduler {
    private List<Process> processes;
    private int contextSwitchTime;
    private List<String> timeline;
    private int contextSwitches;
    private int currentTime;
    private PriorityQueue<Event> eventQueue;

    public Scheduler(List<Process> processes, int contextSwitchTime) {
        this.processes = processes;
        this.contextSwitchTime = contextSwitchTime;
        this.timeline = new ArrayList<>();
        this.contextSwitches = 0;
        this.currentTime = 0;
        this.eventQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));

        initializeEvents();
    }

    private void initializeEvents() {
        for (Process p : processes) {
            eventQueue.add(new Event(p.Arrival_Time, "ARRIVAL", p));
        }
    }

    public void run() {
        Process currentProcess = null;

        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            currentTime = Math.max(currentTime, event.time);

            switch (event.type) {
                case "ARRIVAL":
                    handleArrival(event);
                    break;
                case "EXECUTION":
                    currentProcess = handleExecution(currentProcess);
                    break;
            }
        }

        printResults(timeline, processes, currentTime, contextSwitches, contextSwitchTime);
    }

    private void handleArrival(Event event) {
        eventQueue.add(new Event(currentTime, "EXECUTION", event.process));
    }

    private Process handleExecution(Process currentProcess) {
        Process shortestProcess = getShortest(currentTime);

        if (shortestProcess == null) return currentProcess;

        if (currentProcess != shortestProcess) {
            addContextSwitch(currentProcess);
            currentProcess = shortestProcess;
        }

        executeProcess(shortestProcess);
        return currentProcess;
    }

    private void addContextSwitch(Process currentProcess) {
        if (currentProcess != null) {
            timeline.add(String.format("%d-%d CS", currentTime, currentTime + contextSwitchTime));
            currentTime += contextSwitchTime;
            contextSwitches++;
        }
    }

    private void executeProcess(Process shortest) {
        int startTime = currentTime;

        while (shortest.Remaining_Time > 0) {
            Process nextShortest = getShortest(currentTime);
            if (nextShortest != null && nextShortest.Remaining_Time < shortest.Remaining_Time)
                break;

            currentTime++;
            shortest.Remaining_Time--;
        }

        updateTimeline(startTime, currentTime, shortest);

        if (shortest.Remaining_Time == 0) {
            shortest.Completion_Time = currentTime;
            shortest.Turnaround_Time = shortest.Completion_Time - shortest.Arrival_Time;
            shortest.Waiting_Time = shortest.Turnaround_Time - shortest.Burst_Time;
        } else {
            eventQueue.add(new Event(currentTime, "EXECUTION", shortest));
        }
    }

    private Process getShortest(int now) {
        Process shortest = null;
        for (Process p : processes) {
            if (p.Arrival_Time <= now && p.Remaining_Time > 0) {
                if (shortest == null || p.Remaining_Time < shortest.Remaining_Time || (p.Remaining_Time == shortest.Remaining_Time && p.Arrival_Time < shortest.Arrival_Time)) {
                    shortest = p;
                }
            }
        }
        return shortest;
    }
    private void updateTimeline(int startTime, int currentTime, Process shortest) {
        if (!timeline.isEmpty() && timeline.get(timeline.size() - 1).endsWith("P" + shortest.Process_ID)) {
            String lastEntry = timeline.remove(timeline.size() - 1);
            String[] parts = lastEntry.split("-");
            int prevStart = Integer.parseInt(parts[0].trim());
            timeline.add(String.format("%d-%d P%d", prevStart, currentTime, shortest.Process_ID));
        } else {
            timeline.add(String.format("%d-%d P%d", startTime, currentTime, shortest.Process_ID));
        }
    }

    public static void printResults(List<String> timeline, List<Process> processes, int currentTime, int contextSwitches, int contextSwitchTime) {
        double totalTAT = 0, totalWT = 0;
        for (Process p : processes) {
            totalTAT += p.Turnaround_Time;
            totalWT += p.Waiting_Time;
        }

        double avgTAT = totalTAT / processes.size();
        double avgWT = totalWT / processes.size();
        double cpuUtilization = ((double) (currentTime - (contextSwitches * contextSwitchTime)) / currentTime) * 100;

        System.out.println("Time Process/CS");
        for (String entry : timeline) {
            System.out.println(entry);
        }

        System.out.println("Performance Metrics:");
        System.out.printf("Average Turnaround Time: %.0f ms\n", avgTAT);
        System.out.printf("Average Waiting Time: %.1f ms\n", avgWT);
        System.out.printf("CPU Utilization: %.2f\n", cpuUtilization);
    }
}
