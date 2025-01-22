public class Task {
    String taskName;
    boolean isDone;

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
    
    public String toString() {
        String s = "[" + (this.isDone ? "X" : " ") + "]" + " " + taskName;
        return s;
    }
}
