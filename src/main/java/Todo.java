public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String s = "[T]" + super.toString();
        return s;
    }
}
