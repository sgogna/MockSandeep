package testproxy.beans;

/**
 * TicketingCCFOPCommand
 */
public class TicketingCCFOPCommand extends GenericHostCommand {
    public TicketingCCFOPCommand(String commandStr) {
        super(commandStr);
    }

    @Override
    public String toString() {
        return "TicketingCCFOP";
    }
}
