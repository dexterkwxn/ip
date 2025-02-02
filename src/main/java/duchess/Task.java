package duchess;
/**
 * Represents a task with a name and completion status.
 */
public class Task {
    String taskName;
    boolean isDone;
    public final static String taskSymbol = "-";

    /**
     * Constructs a Task with the given name.
     * The task is initially unmarked (not completed).
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isMarked() {
        return this.isDone;
    }
    
    /**
     * Returns a string representation of the task in file storage format.
     *
     * @return A formatted string for file storage.
     */
    public String toFileFormat() {
        return String.format("%s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string showing the task status and name.
     */
    public String toString() {
        String s = "[" + (this.isDone ? "X" : " ") + "]" + " " + taskName;
        return s;
    }

}
