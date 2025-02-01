package duchess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;
    public final static String taskSymbol = "E";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s | %s", taskSymbol, this.isDone ? 1 : 0, this.taskName, this.from.format(INPUT_FORMATTER), this.to.format(INPUT_FORMATTER));
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString() + " (from: " + this.from.format(OUTPUT_FORMATTER) + ", to: " + this.to.format(OUTPUT_FORMATTER) + ")";
        return s;
    }
    
}
