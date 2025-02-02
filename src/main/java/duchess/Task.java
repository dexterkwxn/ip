package duchess;
public class Task {
    String taskName;
    boolean isDone;
    public final static String taskSymbol = "-";

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMarked() {
        return this.isDone;
    }
    
    public String toFileFormat() {
        return String.format("%s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName);
    }

    public String toString() {
        String s = "[" + (this.isDone ? "X" : " ") + "]" + " " + taskName;
        return s;
    }

}
