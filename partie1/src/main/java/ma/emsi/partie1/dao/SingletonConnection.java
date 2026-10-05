package ma.emsi.partie1.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
  private static Connection connection;
  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/emsi_shop?useSSL=false&serverTimezone=UTC");
      String user = System.getenv().getOrDefault("DB_USER", "root");
      String password = System.getenv().getOrDefault("DB_PASSWORD", "");
      connection = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      throw new ExceptionInInitializerError("Connexion MySQL impossible. Vérifiez MySQL, la base emsi_shop et DB_URL/DB_USER/DB_PASSWORD. Cause: " + e.getMessage());
    }
  }
  public static Connection getConnection() {
    return connection;
  }
}
