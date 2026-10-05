<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Articles | EMSI Shop</title>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.css" rel="stylesheet">
</head>
<body class="min-h-screen bg-gray-100 dark:bg-gray-900">
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
<main class="p-4 sm:ml-64 sm:p-6">
  <c:if test="${not empty message}">
    <div class="mb-5 rounded-lg bg-blue-100 p-4 text-blue-800" role="status">${message}</div>
  </c:if>
  <section class="mb-6 flex flex-wrap items-center justify-between gap-4">
    <div>
      <h1 class="text-2xl font-semibold text-gray-900 dark:text-white">Gestion des articles</h1>
      <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">Consultez et gérez les articles et le stock.</p>
    </div>
    <button data-modal-target="add-article-modal" data-modal-toggle="add-article-modal" type="button"
            class="rounded-lg bg-blue-700 px-5 py-2.5 text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300">
      Ajouter un article
    </button>
  </section>

  <section class="overflow-hidden rounded-lg bg-white shadow dark:bg-gray-800">
    <div class="overflow-x-auto">
      <table class="w-full text-left text-sm text-gray-600 dark:text-gray-300">
        <thead class="bg-gray-50 text-xs uppercase text-gray-700 dark:bg-gray-700 dark:text-gray-300">
        <tr>
          <th scope="col" class="px-5 py-3">ID</th>
          <th scope="col" class="px-5 py-3">Description</th>
          <th scope="col" class="px-5 py-3">Prix</th>
          <th scope="col" class="px-5 py-3">Quantité</th>
          <th scope="col" class="px-5 py-3 text-right" colspan="2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty articleModel.articleList}">
          <tr><td class="px-5 py-10 text-center text-gray-500" colspan="6">Aucun article trouvé. Vous pouvez en ajouter avec le bouton ci-dessus.</td></tr>
        </c:if>
        <c:forEach items="${articleModel.articleList}" var="article">
          <tr class="border-t border-gray-200 bg-white dark:border-gray-700 dark:bg-gray-800">
            <td class="whitespace-nowrap px-5 py-4 font-medium text-gray-900 dark:text-white">${article.id}</td>
            <td class="px-5 py-4">${article.description}</td>
            <td class="whitespace-nowrap px-5 py-4">${article.price}</td>
            <td class="whitespace-nowrap px-5 py-4">${article.quantity}</td>
            <td class="px-3 py-4 text-right">
              <button type="button" data-modal-target="edit-article-${article.id}" data-modal-toggle="edit-article-${article.id}"
                      class="font-medium text-blue-700 hover:underline dark:text-blue-400">Modifier</button>
            </td>
            <td class="px-5 py-4 text-right">
              <a href="supprimer.do?id=${article.id}" onclick="return confirm('Supprimer cet article ? Cette action est définitive.')"
                 class="font-medium text-red-600 hover:underline dark:text-red-400">Supprimer</a>
            </td>
          </tr>
          <div id="edit-article-${article.id}" tabindex="-1" aria-hidden="true" class="fixed inset-0 z-50 hidden items-center justify-center overflow-y-auto overflow-x-hidden">
            <div class="relative w-full max-w-md p-4">
              <div class="rounded-lg bg-white p-5 shadow dark:bg-gray-800">
                <div class="mb-4 flex items-center justify-between">
                  <h2 class="text-lg font-semibold text-gray-900 dark:text-white">Modifier l'article ${article.id}</h2>
                  <button type="button" data-modal-hide="edit-article-${article.id}" class="rounded-lg p-2 text-gray-500 hover:bg-gray-100 dark:hover:bg-gray-700" aria-label="Fermer">✕</button>
                </div>
                <form action="editer.do" method="post" class="space-y-4">
                  <input type="hidden" name="id" value="${article.id}">
                  <div><label class="mb-1 block text-sm font-medium text-gray-900 dark:text-white">Description</label><input type="text" name="description1" value="${article.description}" maxlength="255" required class="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"></div>
                  <div><label class="mb-1 block text-sm font-medium text-gray-900 dark:text-white">Prix</label><input type="number" name="prix1" value="${article.price}" min="0" step="0.01" required class="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"></div>
                  <div><label class="mb-1 block text-sm font-medium text-gray-900 dark:text-white">Quantité</label><input type="number" name="quantite1" value="${article.quantity}" min="0" step="1" required class="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"></div>
                  <button type="submit" class="rounded-lg bg-blue-700 px-5 py-2.5 text-sm font-medium text-white hover:bg-blue-800">Enregistrer</button>
                </form>
              </div>
            </div>
          </div>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </section>

  <div id="add-article-modal" tabindex="-1" aria-hidden="true" class="fixed inset-0 z-50 hidden items-center justify-center overflow-y-auto overflow-x-hidden">
    <div class="relative w-full max-w-md p-4">
      <div class="rounded-lg bg-white p-5 shadow dark:bg-gray-800">
        <div class="mb-4 flex items-center justify-between">
          <h2 class="text-lg font-semibold text-gray-900 dark:text-white">Nouvel article</h2>
          <button type="button" data-modal-hide="add-article-modal" class="rounded-lg p-2 text-gray-500 hover:bg-gray-100 dark:hover:bg-gray-700" aria-label="Fermer">✕</button>
        </div>
        <form action="ajouterArticle.do" method="post" class="space-y-4">
          <div><label for="new-description" class="mb-1 block text-sm font-medium text-gray-900 dark:text-white">Description</label><input id="new-description" type="text" name="description" maxlength="255" required class="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"></div>
          <div><label for="new-price" class="mb-1 block text-sm font-medium text-gray-900 dark:text-white">Prix</label><input id="new-price" type="number" name="prix" min="0" step="0.01" required class="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"></div>
          <div><label for="new-quantity" class="mb-1 block text-sm font-medium text-gray-900 dark:text-white">Quantité</label><input id="new-quantity" type="number" name="quantite" min="0" step="1" value="0" required class="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"></div>
          <button type="submit" class="rounded-lg bg-blue-700 px-5 py-2.5 text-sm font-medium text-white hover:bg-blue-800">Enregistrer l'article</button>
        </form>
      </div>
    </div>
  </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.js"></script>
</body>
</html>
