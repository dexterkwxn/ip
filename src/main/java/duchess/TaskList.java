package duchess;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public int size() {
        return this.taskList.size();
    }
    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.taskName.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
