public class Deadline extends Task{
    String by;
    public final static String taskSymbol = "D";

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName, this.by);
    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (by: " + this.by + ")";
        return s;
    }
}
