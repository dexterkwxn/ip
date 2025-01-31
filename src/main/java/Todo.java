public class Todo extends Task {
    public final static String taskSymbol = "T";
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName);
    }

    @Override
    public String toString() {
        String s = "[T]" + super.toString();
        return s;
    }
}
