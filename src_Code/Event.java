class Event {
    int time;
    String type; // "ARRIVAL", "EXECUTION", "COMPLETION"
    Process process;

    Event(int time, String type, Process process) {
        this.time = time;
        this.type = type;
        this.process = process;
    }
}
