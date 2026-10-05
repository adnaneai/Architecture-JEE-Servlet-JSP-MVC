<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Ajouter un article | EMSI Shop</title>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body class="bg-gray-100 dark:bg-gray-900">
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
<main class="p-4 sm:ml-64">
  <h1 class="mb-6 text-2xl font-semibold text-gray-900 dark:text-white">Ajouter un article</h1>
  <form action="ajouterArticle.do" method="post" class="max-w-3xl rounded-lg bg-white p-6 shadow dark:bg-gray-800">
    <div class="mb-6">
      <label for="description" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white">Description</label>
      <input type="text" id="description" name="description" value="${article.description}" maxlength="255" class="block w-full rounded-lg border border-gray-300 bg-gray-50 p-3 text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white" required />
    </div>
    <div class="grid gap-6 mb-6 md:grid-cols-2">
      <div>
        <label for="price" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white">Prix</label>
        <input type="number" id="price" name="prix" value="${article.price}" min="0" step="0.01" class="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white" required />
      </div>
      <div>
        <label for="quantity-input" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white">Quantité</label>
        <div class="relative flex items-center max-w-[20rem]">
          <button type="button" id="decrement-button" class="bg-gray-100 dark:bg-gray-700 dark:hover:bg-gray-600 dark:border-gray-600 hover:bg-gray-200 border border-gray-300 rounded-s-lg p-3 h-11 focus:ring-gray-100 dark:focus:ring-gray-700 focus:ring-2 focus:outline-none">
            <svg class="w-3 h-3 text-gray-900 dark:text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 2">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h16"/>
            </svg>
          </button>
          <input type="number" id="quantity-input" name="quantite" value="${article.quantity}" min="0" step="1" class="block h-11 w-full border-x-0 border-gray-300 bg-gray-50 py-2.5 text-center text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white" required />
          <button type="button" id="increment-button" class="bg-gray-100 dark:bg-gray-700 dark:hover:bg-gray-600 dark:border-gray-600 hover:bg-gray-200 border border-gray-300 rounded-e-lg p-3 h-11 focus:ring-gray-100 dark:focus:ring-gray-700 focus:ring-2 focus:outline-none">
            <svg class="w-3 h-3 text-gray-900 dark:text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 18">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 1v16M1 9h16"/>
            </svg>
          </button>
        </div>
      </div>
    </div>
    <a href="index.do" class="mb-2 me-2 inline-block rounded-lg border border-gray-300 px-5 py-2.5 text-sm font-medium text-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:text-gray-200 dark:hover:bg-gray-700">Annuler</a>
    <button type="submit" class="mb-2 me-2 rounded-lg bg-blue-700 px-5 py-2.5 text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600">Ajouter l'article</button>
  </form>
</main>
<script src="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.js"></script>
<script>
  const input = document.getElementById('quantity-input');
  const incrementBtn = document.getElementById('increment-button');
  const decrementBtn = document.getElementById('decrement-button');

  if (!input || !incrementBtn || !decrementBtn) return;
  incrementBtn.addEventListener('click', () => {
    let value = parseInt(input.value) || 0;
    input.value = value + 1;
  });

  decrementBtn.addEventListener('click', () => {
    let value = parseInt(input.value) || 0;
    if (value > 0) {
      input.value = value - 1;
    }
  });

  // (Optionnel) Empêche la saisie de texte non numérique
  input.addEventListener('input', () => {
    input.value = input.value.replace(/[^0-9]/g, '');
    if (input.value === '' || parseInt(input.value) < 0) {
      input.value = '0';
    }
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.js"></script>
</body>
</html>
