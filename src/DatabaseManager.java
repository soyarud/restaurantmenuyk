import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurant_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "soyarud";

    private Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    // INSERT menu item
    public void insertMenuItem(String name, String description, double price, String category) {
        String sql = "INSERT INTO menu_items (name, description, price, category) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setString(4, category);
            pstmt.executeUpdate();
            System.out.println("Inserted menu item: " + name);
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    // READ all menu items
    public void readMenuItems() {
        String sql = "SELECT * FROM menu_items";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n=== MENU FROM DATABASE ===");
            while (rs.next()) {
                System.out.printf("%d | %-20s | $%.2f | %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("Read failed: " + e.getMessage());
        }
    }

    // UPDATE price of menu item by name
    public void updateMenuItemPrice(String name, double newPrice) {
        String sql = "UPDATE menu_items SET price = ? WHERE name = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setString(2, name);
            int rows = pstmt.executeUpdate();
            System.out.println("Updated " + rows + " item(s). New price for " + name + ": $" + newPrice);
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // DELETE menu item by name
    public void deleteMenuItem(String name) {
        String sql = "DELETE FROM menu_items WHERE name = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            System.out.println("Deleted " + rows + " item(s): " + name);
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }
}