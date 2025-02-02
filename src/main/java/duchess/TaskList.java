package duchess;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        this.taskList.remove(i);
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }
}
