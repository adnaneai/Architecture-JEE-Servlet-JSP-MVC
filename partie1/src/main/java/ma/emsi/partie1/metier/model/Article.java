package ma.emsi.partie1.metier.model;

public class Article {
  private Long id;
  private String description;
  private double price;
  private int quantity;

  public Article() {

  }

  public Article(String description, double price, int quantity) {

    this.description = description;
    this.price = price;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Article{" +
      "id=" + id +
      ", description='" + description + '\'' +
      ", price=" + price +
      ", quantity=" + quantity +
      '}';
  }
}
