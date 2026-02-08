/**
 * Factory pattern for creating menu items based on category
 * Implements Factory design pattern for object creation
 */
public class MenuItemFactory {

    /**
     * Create a menu item of the appropriate type based on category
     */
    public static MenuItem createMenuItem(int id, String name, String description, 
                                         double price, String category) {
        switch (category.toLowerCase()) {
            case "appetizer":
                return new Appetizer(id, name, description, price);
            case "main course":
            case "main":
                return new MainCourse(id, name, description, price);
            case "dessert":
                return new Dessert(id, name, description, price);
            case "drink":
                Drink defaultDrink = new Drink(id, name, description, price, false);
                return defaultDrink;
            default:
                // Default to generic MenuItem
                return new MenuItem(id, name, description, price, category);
        }
    }

    /**
     * Get category enum-like string for filtering
     */
    public static String normalizeCategoryName(String category) {
        if (category.equalsIgnoreCase("appetizer")) return "Appetizer";
        if (category.equalsIgnoreCase("main") || 
            category.equalsIgnoreCase("main course")) return "Main Course";
        if (category.equalsIgnoreCase("dessert")) return "Dessert";
        if (category.equalsIgnoreCase("drink")) return "Drink";
        return category;
    }
}
