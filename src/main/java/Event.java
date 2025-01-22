public class Event extends Task {
    String from;
    String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
        return s;
    }
    
}
