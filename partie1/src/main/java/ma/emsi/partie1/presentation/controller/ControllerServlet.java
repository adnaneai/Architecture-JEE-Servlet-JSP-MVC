package ma.emsi.partie1.presentation.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.partie1.metier.model.Article;
import ma.emsi.partie1.metier.service.ArticleService;
import ma.emsi.partie1.metier.service.ArticleServiseImp;
import ma.emsi.partie1.presentation.model.ArticleModel;
import java.io.IOException;
import java.util.List;

public class ControllerServlet extends HttpServlet {

  private ArticleService articleService;
  @Override
  public void init() throws ServletException {
    articleService = new ArticleServiseImp();
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getServletPath();
    if (path.equals("/logout.do")) {
      if (request.getSession(false) != null) {
        request.getSession(false).invalidate();
      }
      response.sendRedirect(request.getContextPath() + "/index.do");
    } else if (path.equals("/index.do")) {
      ArticleModel articleModel = new ArticleModel();
      List<Article> articleList = articleService.findAll();
      articleModel.setArticleList(articleList);
      Object flashMessage = request.getSession().getAttribute("flashMessage");
      if (flashMessage != null) {
        request.setAttribute("message", flashMessage);
        request.getSession().removeAttribute("flashMessage");
      }
      request.setAttribute("articleModel", articleModel);
      request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    } else if (path.equals("/supprimer.do")) {
      ArticleModel articleModel = new ArticleModel();
      Long id = Long.parseLong(request.getParameter("id"));
      articleService.delete(id);
      List<Article> articleList = articleService.findAll();
      articleModel.setArticleList(articleList);
      request.setAttribute("articleModel", articleModel);
      request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }else if (path.equals("/editer.do") && request.getMethod().equals("POST")) {
      Long id = Long.parseLong(request.getParameter("id"));
      String description = request.getParameter("description1");
      double price = Double.parseDouble(request.getParameter("prix1"));
      int quantity = Integer.parseInt(request.getParameter("quantite1"));
      articleService.update(id,new Article(description,price,quantity));
      response.sendRedirect(request.getContextPath() + "/index.do");
    }else if (path.equals("/chercher.do")) {
      String input = request.getParameter("input");
      ArticleModel articleModel = new ArticleModel();
      if (input == null || input.trim().isEmpty()){
        List<Article> articleList = articleService.findAll();
        articleModel.setArticleList(articleList);
        request.setAttribute("articleModel", articleModel);
        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        return;
      }
      if (input != null && !input.trim().isEmpty()) {
        boolean matched = false;
        // Essayer par ID
        try {
          Long id = Long.parseLong(input);
          Article article = articleService.findById(id);
          if (article != null) {
            articleModel.getArticleList().add(article);
            matched = true;
          }
        } catch (NumberFormatException e) {
          // Pas un ID
        }
        // Essayer par quantité
        if (!matched) {
          try {
            int quantity = Integer.parseInt(input);
            List<Article> articles = articleService.findByQuantity(quantity); // À implémenter
            if (articles != null && !articles.isEmpty()) {
              articleModel.setArticleList(articles);
              matched = true;
            }
          } catch (NumberFormatException e) {
            // Pas une quantité
          }
        }
        // Essayer par prix
        if (!matched) {
          try {
            double price = Double.parseDouble(input);
            List<Article> articles = articleService.findByPrice(price); // À implémenter
            if (articles != null && !articles.isEmpty()) {
              articleModel.setArticleList(articles);
              matched = true;
            }
          } catch (NumberFormatException e) {
            // Pas un prix
          }
        }
        // Essayer par description
        if (!matched) {
          articleModel.setArticle(input);
          List<Article> articles = articleService.findByDescription(input);
          if (articles != null && !articles.isEmpty()) {
            articleModel.setArticleList(articles);
            matched = true;
          }
        }
        // Si rien trouvé, ajouter un message (optionnel)
        if (!matched) {
          request.setAttribute("message", "Aucun article ne correspond à votre recherche.");
        }
      } else {
        request.setAttribute("message", "Veuillez entrer un critère de recherche valide.");
      }
      request.setAttribute("articleModel", articleModel);
      request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }
    else if (path.equals("/ajouter.do")) {
      request.setAttribute("article",new Article());
      request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
    } else if (path.equals("/ajouterArticle.do") && request.getMethod().equals("POST")) {
      try {
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("prix"));
        int quantity = Integer.parseInt(request.getParameter("quantite"));
        articleService.create(new Article(description, price, quantity));
        request.getSession().setAttribute("flashMessage", "Article ajouté avec succès.");
      } catch (NumberFormatException | IllegalStateException e) {
        request.getSession().setAttribute("flashMessage", "Échec de l'ajout : " + e.getMessage());
      }
      response.sendRedirect(request.getContextPath() + "/index.do");
    }else
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
