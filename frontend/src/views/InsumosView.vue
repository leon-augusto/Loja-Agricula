<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const insumos = ref([])
const tipos = ref([])
const unidades = ref([])

const nome = ref('')
const quantidade = ref(0)
const preco = ref(0.0)
const tipoId = ref('')
const unidadeId = ref('')

const carregarDados = async () => {
  try {
    const [resInsumos, resTipos, resUnidades] = await Promise.all([
      axios.get('http://localhost:8080/api/insumos'),
      axios.get('http://localhost:8080/api/tipos'),
      axios.get('http://localhost:8080/api/unidades')
    ])
    insumos.value = resInsumos.data
    tipos.value = resTipos.data
    unidades.value = resUnidades.data
  } catch (err) {
    console.error('Erro ao carregar dados:', err)
  }
}

const salvarInsumo = async () => {
  if (!nome.value.trim() || !tipoId.value || !unidadeId.value) {
    alert('Preencha o nome, tipo e unidade de medida!')
    return
  }

  try {
    await axios.post('http://localhost:8080/api/insumos', {
      nome: nome.value,
      quantidade: quantidade.value,
      preco: preco.value,
      tipo: { id: tipoId.value },
      unidade: { id: unidadeId.value }
    })
    
    nome.value = ''
    quantidade.value = 0
    preco.value = 0.0
    tipoId.value = ''
    unidadeId.value = ''
    carregarDados()
  } catch (err) {
    alert('Erro ao salvar insumo!')
  }
}

const deletarInsumo = async (id) => {
  if (!confirm('Deseja realmente excluir este insumo?')) return
  try {
    await axios.delete(`http://localhost:8080/api/insumos/${id}`)
    carregarDados()
  } catch (err) {
    alert('Erro ao excluir!')
  }
}

onMounted(() => {
  carregarDados()
})
</script>

<template>
  <div>
    <h2>Gerenciar Insumos</h2>

    <!-- Forms -->
    <form @submit.prevent="salvarInsumo" style="margin-bottom: 20px; display: flex; gap: 10px; flex-wrap: wrap;">
      <input v-model="nome" placeholder="Nome do Insumo (ex: Adubo NPK)" required style="padding: 8px; width: 200px;" />
      <input v-model.number="quantidade" type="number" step="0.01" placeholder="Qtd" required style="padding: 8px; width: 80px;" />
      <input v-model.number="preco" type="number" step="0.01" placeholder="Preço" required style="padding: 8px; width: 100px;" />
      
      <select v-model="tipoId" required style="padding: 8px;">
        <option value="" disabled>Selecione o Tipo</option>
        <option v-for="t in tipos" :key="t.id" :value="t.id">{{ t.nome }}</option>
      </select>

      <select v-model="unidadeId" required style="padding: 8px;">
        <option value="" disabled>Unidade de Medida</option>
        <option v-for="u in unidades" :key="u.id" :value="u.id">{{ u.nome }} ({{ u.sigla }})</option>
      </select>

      <button type="submit" style="padding: 8px 15px; cursor: pointer; background: #42b983; color: white; border: none; border-radius: 4px;">
        Cadastrar
      </button>
    </form>

    <!-- Tabela -->
    <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; max-width: 900px; border-collapse: collapse; text-align: left;">
      <thead style="background: #f2f2f2;">
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Qtd</th>
          <th>Unidade</th>
          <th>Preço (R$)</th>
          <th>Tipo</th>
          <th>Ação</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="i in insumos" :key="i.id">
          <td>{{ i.id }}</td>
          <td>{{ i.nome }}</td>
          <td>{{ i.quantidade }}</td>
          <td>{{ i.unidade ? i.unidade.sigla : '-' }}</td>
          <td>{{ i.preco ? i.preco.toFixed(2) : '0.00' }}</td>
          <td>{{ i.tipo ? i.tipo.nome : '-' }}</td>
          <td>
            <button @click="deletarInsumo(i.id)" style="color: red; cursor: pointer; background: none; border: 1px solid red; border-radius: 4px;">
              Excluir
            </button>
          </td>
        </tr>
        <tr v-if="insumos.length === 0">
          <td colspan="7" style="text-align: center; color: #777;">Nenhum insumo cadastrado.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>