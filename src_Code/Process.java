class  Process implements Comparable<Process>{
    int Process_ID;
    int Arrival_Time, Burst_Time, Remaining_Time;
    int Completion_Time, Waiting_Time, Turnaround_Time;

    Process(int pid, int arrivalTime, int burstTime){
        Process_ID = pid;
        Arrival_Time = arrivalTime;
        Burst_Time = burstTime;
        Remaining_Time = burstTime; // Initially, remaining time = burst time
    }

    @Override
    public int compareTo(Process p) {
        if (this.Remaining_Time != p.Remaining_Time) {
            return Integer.compare(this.Remaining_Time, p.Remaining_Time);// priority SRTF
        }
        return Integer.compare(this.Arrival_Time, p.Arrival_Time); // FCFS
    }
}