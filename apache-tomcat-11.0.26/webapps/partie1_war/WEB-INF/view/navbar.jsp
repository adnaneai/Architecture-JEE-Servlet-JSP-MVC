<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="sticky top-0 z-30 border-b border-gray-200 bg-white dark:border-gray-700 dark:bg-gray-900">
  <div class="mx-auto flex max-w-screen-2xl flex-wrap items-center justify-between gap-4 px-4 py-3 sm:ml-64 sm:px-6">
    <a href="index.do" class="text-lg font-semibold text-gray-900 dark:text-white">EMSI Shop</a>
    <div class="flex w-full flex-wrap items-center justify-end gap-3 sm:w-auto">
    <form action="chercher.do" method="get" class="flex min-w-0 flex-1 gap-2 sm:w-auto sm:flex-none">
      <label for="search-articles" class="sr-only">Rechercher un article</label>
      <input id="search-articles" type="search" name="input" value="${articleModel.article}"
             class="min-w-0 flex-1 rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
             placeholder="Rechercher par ID, description, prix ou quantité">
      <button type="submit" class="rounded-lg bg-blue-700 px-4 py-2.5 text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300">Rechercher</button>
      <a href="index.do" class="rounded-lg border border-gray-300 px-4 py-2.5 text-sm font-medium text-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:text-gray-200 dark:hover:bg-gray-700">Tout</a>
    </form>
    <a href="logout.do" onclick="return confirm('Voulez-vous vraiment vous déconnecter ?')"
       class="whitespace-nowrap rounded-lg border border-red-300 px-4 py-2.5 text-sm font-medium text-red-700 hover:bg-red-50 focus:outline-none focus:ring-4 focus:ring-red-200 dark:border-red-700 dark:text-red-400 dark:hover:bg-gray-800">
      Déconnexion
    </a>
    </div>
  </div>
</nav>
