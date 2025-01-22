import java.util.Scanner;

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

    Scanner scanner;
    Task[] listItems;
    int listSz;
    String in;

    public Duchess(){
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.listItems = new Task[100];
        this.listSz = 0;
    }

    public void greeting(){
        System.out.println(this.logo);
        System.out.println(this.chatLine);
        System.out.println(this.greetingMsg);
        System.out.println(this.chatLine);
    }

    public void addTodo(String in) {
        String taskName = in.substring(in.indexOf(" ") + 1);
        listItems[listSz] = new Todo(taskName);
        System.out.println("added: " + listItems[listSz]);
        ++listSz;
        System.out.println(chatLine);
    }
    public void addDeadline(String in) {
        String byDelimiter = " /by ";
        String taskName = in.substring(in.indexOf(" ") + 1, in.indexOf(byDelimiter));
        String by = in.substring(in.indexOf(byDelimiter) + byDelimiter.length());
        listItems[listSz] = new Deadline(taskName, by);
        System.out.println("added: " + listItems[listSz]);
        ++listSz;
        System.out.println(chatLine);
    }
    public void addEvent(String in) {
        String fromDelimiter = " /from ";
        String toDelimiter = " /to ";
        String taskName = in.substring(in.indexOf(" ") + 1, in.indexOf(fromDelimiter));
        String from = in.substring(in.indexOf(fromDelimiter) + fromDelimiter.length(), in.indexOf(toDelimiter));
        String to = in.substring(in.indexOf(toDelimiter) + toDelimiter.length());
        listItems[listSz] = new Event(taskName, from, to);
        System.out.println("added: " + listItems[listSz]);
        ++listSz;
        System.out.println(chatLine);
    }

    public void printList() {
        for (int i = 0; i < listSz; ++i) {
            System.out.println(i+1 + ". " + listItems[i]);
        }
        System.out.println(chatLine);
    }

    public void mark(String taskNumStr) {
        int taskNum = Integer.parseInt(taskNumStr);
        this.listItems[taskNum - 1].mark();
        System.out.println("Okay!");
    }

    public void unmark(String taskNumStr) {
        int taskNum = Integer.parseInt(taskNumStr);
        this.listItems[taskNum - 1].unmark();
        System.out.println("Okay!");
    }

    public void start(){
        while (isRunning) {
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
                    this.mark(commandStr[1]);
                    break;
                case "unmark":
                    this.unmark(commandStr[1]);
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
                default:
                    break;
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
        app.greeting();
        app.start();
        app.exit();
    }

}
