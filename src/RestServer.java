import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestServer {

    // database credentials (copied from DatabaseManager)
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/restaurant_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "soyarud";

    public static void main(String[] args) throws IOException {
        // creating a web server listening on port 8080
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // http://localhost:8080/api/menu
        server.createContext("/api/menu", new MenuHandler());

        // starting the server
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started on port " + port);
        System.out.println("Go to: http://localhost:8080/api/menu");
        server.start();
    }

    // this class handles the request when someone visits /api/menu
    static class MenuHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 1. only get request
            if ("GET".equals(exchange.getRequestMethod())) {

                // 2. fetching data from db lol
                List<MenuItem> menuItems = getMenuItemsFromDb();

                // 3. converting list to json
                String jsonResponse = convertToJson(menuItems);

                // 4. sending response in json
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.length());
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

    // helper method to get data (replicates logic from DatabaseManager but returns a list)
    private static List<MenuItem> getMenuItemsFromDb() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // creating menu item objects using the data from DB
                MenuItem item = new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("category")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // helper method to manually build json string: [{"id": 1, ...}, {"id": 2, ...}]
    private static String convertToJson(List<MenuItem> items) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            json.append("{");
            json.append("\"id\":").append(item.getId()).append(",");
            json.append("\"name\":\"").append(escape(item.getName())).append("\",");
            json.append("\"description\":\"").append(escape(item.getDescription())).append("\",");
            json.append("\"price\":").append(item.getPrice()).append(",");
            json.append("\"category\":\"").append(escape(item.getCategory())).append("\"");
            json.append("}");

            // add comma if not the last item
            if (i < items.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

    // simple helper to escape quotes in strings
    private static String escape(String text) {
        if (text == null) return "";
        return text.replace("\"", "\\\"").replace("\n", " ");
    }
}