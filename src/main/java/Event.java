public class Event extends Task {
    String from;
    String to;
    public final static String taskSymbol = "E";

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName, this.from, this.to);
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
        return s;
    }
    
}
