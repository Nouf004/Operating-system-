import java.util.*;

public class Scheduler {
    private List<Process> processes;
    private int contextSwitchTime;

    public Scheduler(List<Process> processes, int contextSwitchTime) {
        this.processes = processes;
        this.contextSwitchTime = contextSwitchTime;
    }

    public void run() {
        PriorityQueue<Event> eventQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));//Priority Queue is created to manage events.
        for (Process p : processes) {
            eventQueue.add(new Event(p.Arrival_Time, "arrival", p));
        }

        int currentTime = 0;
        int contextSwitches = 0; //counter for context switch.
        Process currentProcess = null;
        List<String> timeline = new ArrayList<>();

        while (!eventQueue.isEmpty()) {//there is still event to process
            Event event = eventQueue.poll();
            currentTime = Math.max(currentTime, event.time);

            if (event.type.equals("arrival")) {//the process is added back to the queue as an "execute" event at the current time.

                    eventQueue.add(new Event(currentTime, "execute", event.process));
            } else if (event.type.equals("execute")) {//shortest process at the current time is selected.
                Process shortest = getShortestProcess(currentTime);

                if (shortest == null) //no process is ready
                    continue;

                if (currentProcess != shortest) {
                    if (currentProcess != null) {
                        timeline.add(currentTime + "-" + (currentTime + contextSwitchTime) + " CS");// adding cs to the timeline.
                        currentTime += contextSwitchTime;
                        contextSwitches++;
                    }
                    currentProcess = shortest;
                }

                int startTime = currentTime;
                while (shortest.Remaining_Time > 0) {
                    Process nextShortest = getShortestProcess(currentTime);
                    if (nextShortest != null && nextShortest.Remaining_Time < shortest.Remaining_Time) //another shorter process arrives
                        break;

                    currentTime++;
                    shortest.Remaining_Time--;
                }

                updateTimeline(timeline, startTime, currentTime, shortest);

                if (shortest.Remaining_Time == 0) {//the process is completed.
                    shortest.Completion_Time = currentTime;
                    shortest.Turnaround_Time = shortest.Completion_Time - shortest.Arrival_Time;
                    shortest.Waiting_Time = shortest.Turnaround_Time - shortest.Burst_Time;
                } else {
                    eventQueue.add(new Event(currentTime, "execute", shortest)); // adding it to the queue with "execute" event
                }
            }
        }

        printResults(timeline, currentTime, contextSwitches);
    }

    private Process getShortestProcess(int currentTime) {
        Process shortest = null;
        for (Process p : processes) {
            if (p.Arrival_Time <= currentTime && p.Remaining_Time > 0) {
                if (shortest == null || p.Remaining_Time < shortest.Remaining_Time ||
                        (p.Remaining_Time == shortest.Remaining_Time && p.Arrival_Time < shortest.Arrival_Time)) {//If two processes have the same remaining time select the one that arrived first.
                    shortest = p;
                }
            }
        }
        return shortest;
    }

    private void updateTimeline(List<String> timeline, int startTime, int currentTime, Process shortest) {
        if (!timeline.isEmpty() && timeline.get(timeline.size() - 1).endsWith("P" + shortest.Process_ID)) {
            String lastEntry = timeline.remove(timeline.size() - 1);
            String[] parts = lastEntry.split("-");
            int prevStart = Integer.parseInt(parts[0].trim());
            timeline.add(prevStart + "-" + currentTime + " P" + shortest.Process_ID);
        } else {
            timeline.add(startTime + "-" + currentTime + " P" + shortest.Process_ID);
        }
    }

    private void printResults(List<String> timeline, int currentTime, int contextSwitches) {
        double totalTAT = 0, totalWT = 0;
        for (Process p : processes) {
            totalTAT += p.Turnaround_Time;
            totalWT += p.Waiting_Time;
        }

        double avgTAT = totalTAT / processes.size();
        double avgWT = totalWT / processes.size();
        double cpuUtilization = ((double) (currentTime - (contextSwitches * contextSwitchTime)) / currentTime) * 100;

        System.out.println("\nTime Process/CS");
        for (String entry : timeline) {
            System.out.println(entry);
        }

        System.out.println("\nPerformance Metrics:");
        System.out.printf("Average Turnaround Time: %.1f ms\n", avgTAT);
        System.out.printf("Average Waiting Time: %.1f ms\n", avgWT);
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtilization);
    }
}
