package duchess;
import java.util.Scanner;

public class Ui {
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

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(chatLine);
    }

    public void showGreeting() {
        System.out.println(logo);
        showLine();
        System.out.println(greetingMsg);
        showLine();
    }

    public void showExitMessage() {
        System.out.println(exitMsg);
        showLine();
    }

    public void exit() {
        showExitMessage();
        this.scanner.close();
    }

    public void showError(String s) {
        System.out.println(s);
        showLine();
    }

    public String readCommand() {
        String s = scanner.nextLine();
        showLine();
        return s;
    }

    public void printList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
        showLine();
    }

    public void showAddedTask(String task) {
        System.out.println("added: " + task);
        showLine();
    }

    public void showItemMarked() {
        System.out.println("Item marked!");
        showLine();
    }

    public void showItemUnmarked() {
        System.out.println("Item unmarked!");
        showLine();
    }

    public void showItemDeleted() {
        System.out.println("Item deleted!");
        showLine();
    }
}
