public class Parser {

    public Parser() {
    }

    public String[] parseCommand(String s) throws Exception {
        try {
            return s.split(" ");
        } catch (Exception e) {
            throw e;
        }
    }
}
