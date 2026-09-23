<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const tipos = ref([])
const novoNome = ref('')

const carregarTipos = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/tipos')
    tipos.value = res.data
  } catch (err) {
    console.error('Erro ao buscar tipos:', err)
  }
}

const salvarTipo = async () => {
  if (!novoNome.value.trim()) return
  try {
    await axios.post('http://localhost:8080/api/tipos', { nome: novoNome.value })
    novoNome.value = ''
    carregarTipos()
  } catch (err) {
    alert('Erro ao salvar tipo no servidor!')
  }
}

const deletarTipo = async (id) => {
  if (!confirm('Deseja realmente excluir este tipo?')) return
  try {
    await axios.delete(`http://localhost:8080/api/tipos/${id}`)
    carregarTipos()
  } catch (err) {
    alert('Erro ao excluir!')
  }
}

onMounted(() => {
  carregarTipos()
})
</script>

<template>
  <div>
    <h2>Gerenciar Tipos / Categorias</h2>

    <!-- Form -->
    <form @submit.prevent="salvarTipo" style="margin-bottom: 20px;">
      <input 
        v-model="novoNome" 
        placeholder="Digite o novo tipo (ex: Defensivo)" 
        required 
        style="padding: 8px; width: 250px; margin-right: 10px;"
      />
      <button type="submit" style="padding: 8px 15px; cursor: pointer; background: #42b983; color: white; border: none; border-radius: 4px;">
        Cadastrar
      </button>
    </form>

    <!-- Tabela -->
    <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; max-width: 500px; border-collapse: collapse; text-align: left;">
      <thead style="background: #f2f2f2;">
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Ação</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="t in tipos" :key="t.id">
          <td>{{ t.id }}</td>
          <td>{{ t.nome }}</td>
          <td>
            <button @click="deletarTipo(t.id)" style="color: red; cursor: pointer; background: none; border: 1px solid red; border-radius: 4px;">
              Excluir
            </button>
          </td>
        </tr>
        <tr v-if="tipos.length === 0">
          <td colspan="3" style="text-align: center; color: #777;">Nenhum tipo cadastrado.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>