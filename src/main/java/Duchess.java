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
    String[] listItems;
    int listSz;
    String in;

    public Duchess(){
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.listItems = new String[100];
        this.listSz = 0;
    }

    public void greeting(){
        System.out.println(this.logo);
        System.out.println(this.chatLine);
        System.out.println(this.greetingMsg);
        System.out.println(this.chatLine);
    }

    public void echo(String in) {
        listItems[listSz] = in;
        ++listSz;
        System.out.println("added: " + in);
        System.out.println(chatLine);
    }

    public void printList() {
        for (int i = 0; i < listSz; ++i) {
            System.out.println(i+1 + ". " + listItems[i]);
        }
        System.out.println(chatLine);
    }

    public void start(){
        while (isRunning) {
            in = scanner.nextLine();
            System.out.println(chatLine);
            switch(in) {
                case "bye":
                    isRunning = false;
                    break;
                case "list":
                    this.printList();
                    break;
                default:
                    this.echo(in);
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
