package ma.emsi.partie1.metier.service;

import ma.emsi.partie1.metier.model.Article;

import java.util.List;

public interface ArticleService {
  public Article create(Article article);
  public Article update(Long id,Article article);
  public void delete(Long id);
  public Article findById(Long id);
  public List<Article> findAll();
  public List<Article> findByDescription(String description);
  public List<Article> findByPrice(double price);
  public List<Article> findByQuantity(int quantity);
}
