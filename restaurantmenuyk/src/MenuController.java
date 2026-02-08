import java.util.List;

/**
 * MenuController handles all menu-related REST API endpoints
 */
public class MenuController {
    private final MenuItemRepository menuRepository;

    public MenuController(MenuItemRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * GET /api/menu - Get all menu items
     */
    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    /**
     * GET /api/menu/{id} - Get menu item by ID
     */
    public MenuItem getMenuItemById(int id) throws MenuItemNotFoundException {
        MenuItem item = menuRepository.findById(id);
        if (item == null) {
            throw new MenuItemNotFoundException(id);
        }
        return item;
    }

    /**
     * GET /api/menu/category/{category} - Get items by category
     */
    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuRepository.findByCategory(category);
    }

    /**
     * GET /api/menu/search/{keyword} - Search items by name
     */
    public List<MenuItem> searchMenuItems(String keyword) {
        return menuRepository.findByNameContaining(keyword);
    }

    /**
     * POST /api/menu - Create new menu item
     */
    public MenuItem createMenuItem(String name, String description, double price, String category) {
        MenuItem item = MenuItemFactory.createMenuItem(
            (int) menuRepository.count() + 1,
            name,
            description,
            price,
            category
        );
        return menuRepository.save(item);
    }

    /**
     * GET menu items within price range
     */
    public List<MenuItem> getMenuItemsByPriceRange(double minPrice, double maxPrice) {
        return menuRepository.findByPriceRange(minPrice, maxPrice);
    }
}
