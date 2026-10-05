package ma.emsi.partie1;

import ma.emsi.partie1.metier.model.Article;
import ma.emsi.partie1.metier.service.ArticleService;
import ma.emsi.partie1.metier.service.ArticleServiseImp;

import java.util.List;

public class Main {

  public static void main(String[] args) {

    ArticleService articleService = new ArticleServiseImp();

    List<Article> articleList = articleService.findByPrice(1.2);

    System.out.println("Nombre d'articles trouvés : " + articleList.size());

    for (Article article : articleList) {
      System.out.println(article);
    }
  }
}