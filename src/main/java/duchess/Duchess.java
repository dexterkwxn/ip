package duchess;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.BufferedWriter; 
import java.io.BufferedReader; 
import java.io.FileWriter; 
import java.io.FileReader; 

public class Duchess {
    boolean isRunning;
    private static final String FILE_PATH = "./data/list.txt";


    TaskList taskList;
    String in;
    Ui ui;
    Parser parser;
    Storage storage;

    /**
     * Constructs a new Duchess application instance.
     * Initializes UI, parser, storage, and loads the task list.
     */
    public Duchess() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.isRunning = true;
        this.storage = new Storage(FILE_PATH);
        this.taskList = this.storage.loadList();
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @param in The user input containing the task description.
     * @throws DuchessException If the input format is invalid.
     */
    public void addTodo(String in) throws DuchessException  {
        try {
            String taskName = in.substring(in.indexOf(" ") + 1);
            Todo task = new Todo(taskName);
            this.taskList.add(task);
            this.ui.showAddedTask(task.toString());
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    /**
     * Adds a Deadline task to the task list.
     *
     * @param in The user input containing the task description and deadline.
     * @throws DuchessException If the input format is invalid.
     */
    public void addDeadline(String in) throws DuchessException {
        try {
            String byDelimiter = " /by ";
            if (in.indexOf(" ") == -1 || in.indexOf(byDelimiter) == -1) {
                throw new DuchessException(in, ErrorType.INVALID_FORMAT);
            }
            String taskName = in.substring(in.indexOf(" ") + 1, in.indexOf(byDelimiter));
            String by = in.substring(in.indexOf(byDelimiter) + byDelimiter.length());
            Deadline task = new Deadline(taskName, by);
            this.taskList.add(task);
            this.ui.showAddedTask(task.toString());
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    /**
     * Adds an Event task to the task list.
     *
     * @param in The user input containing the task description, start time, and end time.
     * @throws DuchessException If the input format is invalid.
     */
    public void addEvent(String in) throws DuchessException {
        try {
            String fromDelimiter = " /from ";
            String toDelimiter = " /to ";
            String taskName = in.substring(in.indexOf(" ") + 1, in.indexOf(fromDelimiter));
            String from = in.substring(in.indexOf(fromDelimiter) + fromDelimiter.length(), in.indexOf(toDelimiter));
            String to = in.substring(in.indexOf(toDelimiter) + toDelimiter.length());
            Event task = new Event(taskName, from, to);
            this.taskList.add(task);
            this.ui.showAddedTask(task.toString());
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }


    /**
     * Marks a task as completed.
     *
     * @param in The user input specifying the task number.
     * @throws DuchessException If the input format is invalid.
     */
    public void mark(String in) throws DuchessException {
        int taskNum;
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.get(taskNum - 1).mark();
            this.ui.showItemMarked();
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }

    /**
     * Unmarks a task as not completed.
     *
     * @param in The user input specifying the task number.
     * @throws DuchessException If the input format is invalid.
     */
    public void unmark(String in) throws DuchessException {
        int taskNum;
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.get(taskNum - 1).unmark();
            this.ui.showItemUnmarked();
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param in The user input specifying the task number.
     * @throws DuchessException If the input format is invalid.
     */
    public void deleteTask(String in) throws DuchessException {
        int taskNum;
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.remove(taskNum - 1);
            this.ui.showItemDeleted();
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    /**
     * Handles unrecognized commands by throwing an exception.
     *
     * @param command The unrecognized command input by the user.
     * @throws DuchessException Always thrown with an invalid command error.
     */
    public void processUnrecognisedCommand(String command) throws DuchessException {
        throw new DuchessException(command, ErrorType.INVALID_COMMAND);
    }

    /**
     * Finds items in the task list that match the keyword and displays it.
     *
     * @param in The command input by the user.
     */
    public void find(String in) {
        String keyword = in.substring(in.indexOf(" ") + 1);
        ArrayList<Task> matchingTasks = this.taskList.find(keyword);
        this.ui.showMatchingTasks(matchingTasks); 
    }

    /**
     * Starts the Duchess application, handling user input in a loop.
     */
    public void start(){
        while (isRunning) {
            try {
                in = this.ui.readCommand();
                String[] commandStr= this.parser.parseCommand(in);
                switch(commandStr[0]) {
                    case "bye":
                        isRunning = false;
                        break;
                    case "list":
                        this.ui.printList(this.taskList);
                        break;
                    case "mark":
                        this.mark(in);
                        break;
                    case "unmark":
                        this.unmark(in);
                        break;
                    case "todo":
                        this.addTodo(in);
                        break;
                    case "deadline":
                        this.addDeadline(in);
                        break;
                    case "event":
                        this.addEvent(in);
                        break;
                    case "delete":
                        this.deleteTask(in);
                        break;
                    case "find":
                        this.find(in);
                        break;
                    default:
                        this.processUnrecognisedCommand(in);
                        break;
                }
                this.storage.saveList(this.taskList);
            } catch (DuchessException e) {
                this.ui.showError(e.getMessage());
            } catch (Exception e) {
                this.ui.showError("Exception caught: " + e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Duchess application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Duchess app = new Duchess();
        app.ui.showGreeting();
        app.start();
        app.ui.exit();
    }
}
