/**
 * Exception thrown when a menu item is not found
 */
public class MenuItemNotFoundException extends Exception {
    public MenuItemNotFoundException(String message) {
        super(message);
    }

    public MenuItemNotFoundException(int id) {
        super("Menu item with ID " + id + " not found");
    }
}
