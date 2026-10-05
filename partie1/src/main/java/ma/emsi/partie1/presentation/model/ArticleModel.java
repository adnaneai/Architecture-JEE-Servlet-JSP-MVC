package ma.emsi.partie1.presentation.model;

import ma.emsi.partie1.metier.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleModel {
  private Long id;
  private String article;
  private double prix;
  private int quantite;
  private List<Article> articleList = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getArticle() {
    return article;
  }

  public void setArticle(String article) {
    this.article = article;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }

  public int getQuantite() {
    return quantite;
  }

  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

  public List<Article> getArticleList() {
    return articleList;
  }

  public void setArticleList(List<Article> articleList) {
    this.articleList = articleList;
  }
}
