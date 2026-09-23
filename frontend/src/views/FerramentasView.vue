<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const ferramentas = ref([])
const tipos = ref([])

const nome = ref('')
const quantidade = ref(0)
const preco = ref(0.0)
const tipoId = ref('')

const carregarDados = async () => {
  // Carrega os Tipos
  try {
    const resTipos = await axios.get('http://localhost:8080/api/tipos')
    tipos.value = resTipos.data
  } catch (err) {
    console.error('Erro ao carregar tipos:', err)
  }

  // Carrega as Ferramentas
  try {
    const resFerramentas = await axios.get('http://localhost:8080/api/ferramentas')
    ferramentas.value = resFerramentas.data
  } catch (err) {
    console.error('Erro ao carregar ferramentas:', err)
  }
}

const salvarFerramenta = async () => {
  if (!nome.value.trim() || !tipoId.value) {
    alert('Preencha o nome e selecione um tipo!')
    return
  }

  try {
    await axios.post('http://localhost:8080/api/ferramentas', {
      nome: nome.value,
      quantidade: quantidade.value,
      preco: preco.value,
      tipo: { id: tipoId.value }
    })
    
    nome.value = ''
    quantidade.value = 0
    preco.value = 0.0
    tipoId.value = ''
    carregarDados()
  } catch (err) {
    alert('Erro ao salvar ferramenta!')
  }
}

const deletarFerramenta = async (id) => {
  if (!confirm('Deseja realmente excluir esta ferramenta?')) return
  try {
    await axios.delete(`http://localhost:8080/api/ferramentas/${id}`)
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
    <h2>Gerenciar Ferramentas</h2>

    <!-- Aviso se não houver tipos cadastrados -->
    <div v-if="tipos.length === 0" style="background: #fff3cd; color: #856404; padding: 10px; border-radius: 4px; margin-bottom: 15px; border: 1px solid #ffeeba;">
      ⚠️ Nenhuma categoria/tipo cadastrada. <router-link to="/tipos">Clique aqui para cadastrar um Tipo</router-link> antes de criar ferramentas.
    </div>

    <!-- Form -->
    <form @submit.prevent="salvarFerramenta" style="margin-bottom: 20px; display: flex; gap: 10px; flex-wrap: wrap;">
      <input v-model="nome" placeholder="Nome da Ferramenta" required style="padding: 8px; width: 200px;" />
      <input v-model.number="quantidade" type="number" placeholder="Qtd" required style="padding: 8px; width: 80px;" />
      <input v-model.number="preco" type="number" step="0.01" placeholder="Preço" required style="padding: 8px; width: 100px;" />
      
      <select v-model="tipoId" required style="padding: 8px;" :disabled="tipos.length === 0">
        <option value="" disabled>Selecione o Tipo</option>
        <option v-for="t in tipos" :key="t.id" :value="t.id">{{ t.nome }}</option>
      </select>

      <button type="submit" :disabled="tipos.length === 0" style="padding: 8px 15px; cursor: pointer; background: #42b983; color: white; border: none; border-radius: 4px;">
        Cadastrar
      </button>
    </form>

    <!-- Tabela -->
    <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; max-width: 800px; border-collapse: collapse; text-align: left;">
      <thead style="background: #f2f2f2;">
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Qtd</th>
          <th>Preço (R$)</th>
          <th>Tipo</th>
          <th>Ação</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="f in ferramentas" :key="f.id">
          <td>{{ f.id }}</td>
          <td>{{ f.nome }}</td>
          <td>{{ f.quantidade }}</td>
          <td>{{ f.preco ? f.preco.toFixed(2) : '0.00' }}</td>
          <td>{{ f.tipo ? f.tipo.nome : '-' }}</td>
          <td>
            <button @click="deletarFerramenta(f.id)" style="color: red; cursor: pointer; background: none; border: 1px solid red; border-radius: 4px;">
              Excluir
            </button>
          </td>
        </tr>
        <tr v-if="ferramentas.length === 0">
          <td colspan="6" style="text-align: center; color: #777;">Nenhuma ferramenta cadastrada.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>