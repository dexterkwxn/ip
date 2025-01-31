import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.BufferedWriter; 
import java.io.BufferedReader; 
import java.io.FileWriter; 
import java.io.FileReader; 

public class Duchess {
    boolean isRunning;
    String logo = "   ___                    _                              \n"
            +"  |   \\   _  _     __    | |_      ___     ___     ___   \n"
            +"  | |) | | +| |   / _|   | ' \\    / -_)   (_-<    (_-<   \n"
            +"  |___/   \\_,_|   \\__|_  |_||_|   \\___|   /__/_   /__/_  \n"
            +"_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
            +"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\n";
    String chatbotName = "Duchess";
    String chatLine = "________________________________\n";
    String greetingMsg = "Hello! I'm " + chatbotName + "\n" + 
                    "What can I do for you?";
    String exitMsg = "Bye. Hope to see you again soon!\n";
    private static final String FILE_PATH = "./data/list.txt";


    Scanner scanner;
    ArrayList<Task> taskList;
    String in;

    public Duchess() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.taskList = new ArrayList<>();
    }

    public void greeting() {
        System.out.println(this.logo);
        System.out.println(this.chatLine);
        System.out.println(this.greetingMsg);
        System.out.println(this.chatLine);
    }

    public void addTodo(String in) throws DuchessException  {
        try {
            String taskName = in.substring(in.indexOf(" ") + 1);
            Todo task = new Todo(taskName);
            this.taskList.add(task);
            System.out.println("added: " + task);
            System.out.println(chatLine);
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
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
            System.out.println("added: " + task);
            System.out.println(chatLine);
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    public void addEvent(String in) throws DuchessException {
        try {
            String fromDelimiter = " /from ";
            String toDelimiter = " /to ";
            String taskName = in.substring(in.indexOf(" ") + 1, in.indexOf(fromDelimiter));
            String from = in.substring(in.indexOf(fromDelimiter) + fromDelimiter.length(), in.indexOf(toDelimiter));
            String to = in.substring(in.indexOf(toDelimiter) + toDelimiter.length());
            Event task = new Event(taskName, from, to);
            this.taskList.add(task);
            System.out.println("added: " + task);
            System.out.println(chatLine);
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }

    public void printList() {
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
        System.out.println(chatLine);
    }

    public void mark(String in) throws DuchessException {
        int taskNum;
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.get(taskNum - 1).mark();
            System.out.println("Item marked!");
            System.out.println(chatLine);
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }

    public void unmark(String in) throws DuchessException {
        int taskNum;
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.get(taskNum - 1).unmark();
            System.out.println("Item unmarked!");
            System.out.println(chatLine);
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    public void deleteTask(String in) throws DuchessException {
        int taskNum;
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.remove(taskNum - 1);
            System.out.println("Item deleted!");
            System.out.println(chatLine);
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    public void processUnrecognisedCommand(String command) throws DuchessException {
        throw new DuchessException(command, ErrorType.INVALID_COMMAND);
    }


    public void saveList() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();  
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : this.taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadList() {
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            System.out.println("No existing task file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    this.taskList.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public Task parseTask(String s) throws Exception {
        try {
            String[] parts = s.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String taskName = parts[2];

            Task task;
            if (type.equals("T")) {
                task = new Todo(taskName);
            } else if (type.equals("D")) {
                task = new Deadline(taskName, parts[3]);
            } else if (type.equals("E")) {
                task = new Event(taskName, parts[3], parts[4]);
            } else {
                return null;
            }

            if (isDone) {
                task.mark();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted task entry: " + s);
            return null;
        }
    }

    public void start(){
        while (isRunning) {
            try {
                in = scanner.nextLine();
                String[] commandStr= in.split(" ");
                System.out.println(chatLine);
                switch(commandStr[0]) {
                    case "bye":
                        isRunning = false;
                        break;
                    case "list":
                        this.printList();
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
                    default:
                        this.processUnrecognisedCommand(in);
                        break;
                }
                this.saveList();
            } catch (DuchessException e) {
                System.out.println(e.getMessage());
                System.out.println(chatLine);
            } catch (Exception e) {
                System.out.println("Exception Caught: " + e.getMessage());
                System.out.println(chatLine);
            }
        }
    }

    public void exit() {
        System.out.println(exitMsg);
        System.out.println(chatLine);
        this.scanner.close();
    }
    public static void main(String[] args) {
        Duchess app = new Duchess();
        app.loadList();
        app.greeting();
        app.start();
        app.exit();
    }

}
