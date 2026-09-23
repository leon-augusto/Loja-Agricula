import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TiposView from '../views/TiposView.vue'
import UnidadesView from '../views/UnidadesView.vue'
import FerramentasView from '../views/FerramentasView.vue'
import InsumosView from "../views/InsumosView.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/tipos',
      name: 'tipos',
      component: TiposView
    },
    {
      path: '/unidades',
      name: 'unidades',
      component: UnidadesView
    },
    {
      patch: '/ferramentas',
      name: 'ferramentas',
      component: FerramentasView
    },
    {
      path: '/insumos',
      name: 'insumos',
      component: InsumosView
    }

  ]
})

export default router