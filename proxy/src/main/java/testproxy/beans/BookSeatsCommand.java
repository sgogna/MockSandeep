package testproxy.beans;

/**
 * BookSeatsCommand
 */
public class BookSeatsCommand extends GenericHostCommand {
    public BookSeatsCommand(String commandStr) {
        super(commandStr);
    }

    @Override
    public String toString() {
        return "BookSeats";
    }
}
