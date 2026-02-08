/**
 * Exception thrown when an order is not found
 */
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(int orderId) {
        super("Order with ID " + orderId + " not found");
    }
}
