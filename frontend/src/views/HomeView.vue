<script setup>
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'

const ferramentas = ref([])
const insumos = ref([])
const tipos = ref([])
const unidades = ref([])
const carregando = ref(true)

const carregarResumo = async () => {
  try {
    const [resF, resI, resT, resU] = await Promise.all([
      axios.get('http://localhost:8080/api/ferramentas'),
      axios.get('http://localhost:8080/api/insumos'),
      axios.get('http://localhost:8080/api/tipos'),
      axios.get('http://localhost:8080/api/unidades')
    ])
    ferramentas.value = resF.data
    insumos.value = resI.data
    tipos.value = resT.data
    unidades.value = resU.data
  } catch (err) {
    console.error('Erro ao carregar dados do painel:', err)
  } finally {
    carregando.value = false
  }
}

// Métricas do Dashboard
const totalFerramentas = computed(() => ferramentas.value.length)
const totalInsumos = computed(() => insumos.value.length)
const totalTipos = computed(() => tipos.value.length)
const totalUnidades = computed(() => unidades.value.length)

const valorTotalStock = computed(() => {
  const vFerramentas = ferramentas.value.reduce((acc, f) => acc + (f.preco * f.quantidade), 0)
  const vInsumos = insumos.value.reduce((acc, i) => acc + (i.preco * i.quantidade), 0)
  return vFerramentas + vInsumos
})

// Alerta de stock baixo (quantidade < 5)
const itensStockBaixo = computed(() => {
  const fBaixo = ferramentas.value
    .filter(f => f.quantidade < 5)
    .map(f => ({ id: `F-${f.id}`, nome: f.nome, qtd: f.quantidade, tipo: 'Ferramenta' }))
    
  const iBaixo = insumos.value
    .filter(i => i.quantidade < 5)
    .map(i => ({ id: `I-${i.id}`, nome: i.nome, qtd: i.quantidade, tipo: 'Insumo' }))
    
  return [...fBaixo, ...iBaixo]
})

onMounted(() => {
  carregarResumo()
})
</script>

<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="fw-bold text-secondary">Painel de Controlo</h2>
      <button @click="carregarResumo" class="btn btn-outline-success btn-sm">
        🔄 Atualizar Dados
      </button>
    </div>

    <!-- Indicadores Principais -->
    <div class="row g-3 mb-4">
      <div class="col-md-3">
        <div class="card border-0 shadow-sm bg-primary text-white p-3">
          <div class="card-body">
            <h6 class="card-title text-white-50">Ferramentas</h6>
            <h2 class="fw-bold m-0">{{ totalFerramentas }}</h2>
          </div>
        </div>
      </div>

      <div class="col-md-3">
        <div class="card border-0 shadow-sm bg-success text-white p-3">
          <div class="card-body">
            <h6 class="card-title text-white-50">Insumos</h6>
            <h2 class="fw-bold m-0">{{ totalInsumos }}</h2>
          </div>
        </div>
      </div>

      <div class="col-md-3">
        <div class="card border-0 shadow-sm bg-info text-white p-3">
          <div class="card-body">
            <h6 class="card-title text-white-50">Categorias / Tipos</h6>
            <h2 class="fw-bold m-0">{{ totalTipos }}</h2>
          </div>
        </div>
      </div>

      <div class="col-md-3">
        <div class="card border-0 shadow-sm bg-dark text-white p-3">
          <div class="card-body">
            <h6 class="card-title text-white-50">Valor Total em Stock</h6>
            <h3 class="fw-bold m-0">R$ {{ valorTotalStock.toFixed(2) }}</h3>
          </div>
        </div>
      </div>
    </div>

    <!-- Tabela de Alerta de Stock Baixo -->
    <div class="card border-0 shadow-sm">
      <div class="card-header bg-white py-3">
        <h5 class="fw-bold text-danger m-0">⚠️ Alertas de Stock Baixo (&lt; 5 unidades)</h5>
      </div>
      <div class="card-body p-0">
        <table class="table table-hover align-middle mb-0">
          <thead class="table-light">
            <tr>
              <th>Código</th>
              <th>Nome do Produto</th>
              <th>Categoria</th>
              <th>Quantidade Atual</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in itensStockBaixo" :key="item.id">
              <td><code>{{ item.id }}</code></td>
              <td class="fw-bold">{{ item.nome }}</td>
              <td>
                <span :class="item.tipo === 'Ferramenta' ? 'badge bg-primary' : 'badge bg-success'">
                  {{ item.tipo }}
                </span>
              </td>
              <td>{{ item.qtd }}</td>
              <td><span class="badge bg-danger">Reposição Necessária</span></td>
            </tr>
            <tr v-if="itensStockBaixo.length === 0">
              <td colspan="5" class="text-center text-muted py-4">
                ✅ Todos os produtos estão com níveis de stock adequados.
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>