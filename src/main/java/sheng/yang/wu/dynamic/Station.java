package sheng.yang.wu.dynamic;

public class Station implements TicketService {
    public void sellTicket() {
        System.out.println("\n\t售票.....\n");
    }

    public void inquire() {
        System.out.println("\n\t问询.....\n");
    }

    public void withdraw() {
        System.out.println("\n\t退票.....\n");
    }
}
