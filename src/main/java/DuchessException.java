public class DuchessException extends Exception {
    private final ErrorType errorType;

    public DuchessException(String s) {
        super(s);
        this.errorType = ErrorType.UNKNOWN_ERROR;
    }

    public DuchessException(String s, ErrorType errorType) {
        super(s);
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        String s;
        switch(this.errorType) {
            case INVALID_COMMAND:
                s = "What are you blabbering about you babboon.";
                break;
            case INVALID_FORMAT:
                s = "You can do better than that... format your command properly...";
                break;
            default:
                s = "We got an unknown error here...\n" + super.getMessage();
                break;
        }
        return s;
    }
}
