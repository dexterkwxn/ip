import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {
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

        System.out.println(logo);
        System.out.println(chatLine);
        System.out.println(greetingMsg);
        System.out.println(chatLine);

        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        System.out.println(chatLine);
        while (!in.equals("bye")) {
            System.out.println(in);
            System.out.println(chatLine);
            in = scanner.nextLine();
            System.out.println(chatLine);
        }
        System.out.println(exitMsg);
        System.out.println(chatLine);

        scanner.close();

    }

}
