public class Deadline extends Task{
    String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (by: " + this.by + ")";
        return s;
    }
}
