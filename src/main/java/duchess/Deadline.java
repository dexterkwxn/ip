package duchess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDateTime by;
    public final static String taskSymbol = "D";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");


    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = LocalDateTime.parse(by, INPUT_FORMATTER);

    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName, this.by.format(INPUT_FORMATTER));
    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (by: " + this.by.format(OUTPUT_FORMATTER) + ")";
        return s;
    }
}
