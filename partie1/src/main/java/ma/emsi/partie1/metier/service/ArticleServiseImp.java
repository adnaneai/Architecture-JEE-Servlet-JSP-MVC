package ma.emsi.partie1.metier.service;

import ma.emsi.partie1.dao.SingletonConnection;
import ma.emsi.partie1.metier.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArticleServiseImp implements ArticleService{
  @Override
  public Article create(Article article) {
    Connection connection = SingletonConnection.getConnection();
    try{
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement(
        "INSERT INTO ARTICLE (DESCRIPTION,PRIX," + quantityColumn + ") VALUES (?,?,?)",
        java.sql.Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1,article.getDescription());
      preparedStatement.setDouble(2,article.getPrice());
      preparedStatement.setInt(3,article.getQuantity());
      preparedStatement.executeUpdate();
      try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
        if (resultSet.next()) {
          article.setId(resultSet.getLong(1));
        }
      }
      preparedStatement.close();
    }catch (SQLException e){
      throw new IllegalStateException("Impossible d'ajouter l'article dans la base de données: " + e.getMessage(), e);
    }
    return article;
  }

  @Override
  public Article update(Long id,Article article) {
    ArticleService articleService = new ArticleServiseImp();
    Article articleExist = articleService.findById(id);
    if (articleExist == null) {
      System.out.println("Article n'existe pas");
      return null;
    }
    Connection connection = SingletonConnection.getConnection();
    try{
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ARTICLE SET DESCRIPTION = ?, PRIX = ?, " + quantityColumn + " = ? WHERE ID = ?");
      preparedStatement.setString(1,article.getDescription());
      preparedStatement.setDouble(2,article.getPrice());
      preparedStatement.setInt(3,article.getQuantity());
      preparedStatement.setLong(4,id);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      return articleExist;
    }catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }
  @Override
  public void delete(Long id) {
    ArticleService articleService = new ArticleServiseImp();
    Article articleExist = articleService.findById(id);
    if(articleExist == null){
      System.out.println("Article n'existe pas");
    }else {
      try {
        Connection connection = SingletonConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ARTICLE WHERE ID = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Article supprimé avec succsses");
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }
  @Override
  public Article findById(Long id) {
    Connection connection = SingletonConnection.getConnection();
    try{
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE ID = ?");
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        Article article = new Article();
        article.setId(id);
        article.setDescription(resultSet.getString("DESCRIPTION"));
        article.setPrice(resultSet.getDouble("PRIX"));
        article.setQuantity(resultSet.getInt(quantityColumn));
        return article;
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }
  @Override
  public List<Article> findAll() {
    List<Article> articleList = new ArrayList<>();
    Connection connection = SingletonConnection.getConnection();
    try{
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTICLE");
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        Article article = new Article();
        article.setId(resultSet.getLong("ID"));
        article.setDescription(resultSet.getString("DESCRIPTION"));
        article.setPrice(resultSet.getDouble("PRIX"));
        article.setQuantity(resultSet.getInt(quantityColumn));
        articleList.add(article);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return articleList;
  }
  @Override
  public List<Article> findByDescription(String description) {
    List<Article> articleList = new ArrayList<>();
    Connection connection = SingletonConnection.getConnection();
    try{
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE DESCRIPTION LIKE ?");
      preparedStatement.setString(1, "%"+description+"%");
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        Article article = new Article();
        article.setId(resultSet.getLong("ID"));
        article.setDescription(resultSet.getString("DESCRIPTION"));
        article.setPrice(resultSet.getDouble("PRIX"));
        article.setQuantity(resultSet.getInt(quantityColumn));
        articleList.add(article);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return articleList;
  }
  @Override
  public List<Article> findByPrice(double price) {
    List<Article> articleList = new ArrayList<>();
    Connection connection = SingletonConnection.getConnection();
    try{
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE PRIX = ?");
      preparedStatement.setDouble(1, price);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        Article article = new Article();
        article.setId(resultSet.getLong("ID"));
        article.setDescription(resultSet.getString("DESCRIPTION"));
        article.setPrice(resultSet.getDouble("PRIX"));
        article.setQuantity(resultSet.getInt(quantityColumn));
        articleList.add(article);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return articleList;
  }

  @Override
  public List<Article> findByQuantity(int quantity) {
    List<Article> articleList = new ArrayList<>();
    Connection connection = SingletonConnection.getConnection();
    try {
      String quantityColumn = findQuantityColumn(connection);
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE " + quantityColumn + " = ?");
      preparedStatement.setInt(1, quantity);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        Article article = new Article();
        article.setId(resultSet.getLong("ID"));
        article.setDescription(resultSet.getString("DESCRIPTION"));
        article.setPrice(resultSet.getDouble("PRIX"));
        article.setQuantity(resultSet.getInt(quantityColumn));
        articleList.add(article);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return articleList;
  }

  private String findQuantityColumn(Connection connection) throws SQLException {
    String[] acceptedNames = {"QUANTITE", "QUANTITY", "QANTITY"};
    try (var columns = connection.getMetaData().getColumns(connection.getCatalog(), null, "%", "%")) {
      while (columns.next()) {
        if (!"ARTICLE".equalsIgnoreCase(columns.getString("TABLE_NAME"))) {
          continue;
        }
        String columnName = columns.getString("COLUMN_NAME");
        for (String acceptedName : acceptedNames) {
          if (acceptedName.equals(columnName.toUpperCase(Locale.ROOT))) {
            return columnName;
          }
        }
      }
    }
    throw new SQLException("Colonne de quantité introuvable dans ARTICLE. Noms acceptés : QUANTITE, QUANTITY ou QANTITY.");
  }
}
