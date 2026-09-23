<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div>
    <!-- Barra de Navegação Bootstrap -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-success shadow-sm mb-4">
      <div class="container">
        <RouterLink class="navbar-brand fw-bold fs-4" to="/">🌾 Loja Agrícola</RouterLink>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <RouterLink class="nav-link" to="/">Painel de Controlo</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/tipos">Tipos</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/unidades">Unidades</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/ferramentas">Ferramentas</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/insumos">Insumos</RouterLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Conteúdo Central -->
    <main class="container mb-5">
      <RouterView />
    </main>
  </div>
</template>