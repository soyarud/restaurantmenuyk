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

    static class MenuHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();

            if ("GET".equals(method)) {
                // response: sending db data as json
                List<MenuItem> menuItems = getMenuItemsFromDb();
                String jsonResponse = convertToJson(menuItems);
                sendResponse(exchange, jsonResponse, 200);

            } else if ("POST".equals(method)) {
                // request: getting json from user to add item
                java.util.Scanner s = new java.util.Scanner(exchange.getRequestBody()).useDelimiter("\\A");
                String body = s.hasNext() ? s.next() : "";

                try {
                    // simple parsing logic
                    String name = extractValue(body, "name");
                    String desc = extractValue(body, "description");
                    double price = Double.parseDouble(extractValue(body, "price"));
                    String cat = extractValue(body, "category");

                    // inserting to db using your manager logic
                    DatabaseManager db = new DatabaseManager();
                    db.insertMenuItem(name, desc, price, cat);

                    sendResponse(exchange, "{\"status\":\"added\"}", 201);
                } catch (Exception e) {
                    sendResponse(exchange, "{\"status\":\"error\"}", 400);
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }

        // helper to send json back
        private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(code, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // simple logic to grab values from the json string
        private String extractValue(String json, String key) {
            String pattern = "\"" + key + "\":\"";
            int start = json.indexOf(pattern);
            if (start == -1) { // for numbers (no quotes)
                pattern = "\"" + key + "\":";
                start = json.indexOf(pattern) + pattern.length();
                int end = json.indexOf(",", start);
                if (end == -1) end = json.indexOf("}", start);
                return json.substring(start, end).trim();
            }
            start += pattern.length();
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
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