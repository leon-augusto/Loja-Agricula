<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const unidades = ref([])
const novoNome = ref('')
const novaSigla = ref('')

const carregarUnidades = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/unidades')
    unidades.value = res.data
  } catch (err) {
    console.error('Erro ao buscar unidades:', err)
  }
}

const salvarUnidade = async () => {
  if (!novoNome.value.trim() || !novaSigla.value.trim()) return
  try {
    await axios.post('http://localhost:8080/api/unidades', { 
      nome: novoNome.value,
      sigla: novaSigla.value
    })
    novoNome.value = ''
    novaSigla.value = ''
    carregarUnidades()
  } catch (err) {
    alert('Erro ao salvar unidade!')
  }
}

const deletarUnidade = async (id) => {
  if (!confirm('Deseja realmente excluir esta unidade?')) return
  try {
    await axios.delete(`http://localhost:8080/api/unidades/${id}`)
    carregarUnidades()
  } catch (err) {
    alert('Erro ao excluir!')
  }
}

onMounted(() => {
  carregarUnidades()
})
</script>

<template>
  <div>
    <h2>Gerenciar Unidades de Medida</h2>

    <!-- Form -->
    <form @submit.prevent="salvarUnidade" style="margin-bottom: 20px;">
      <input 
        v-model="novoNome" 
        placeholder="Nome (ex: Quilograma)" 
        required 
        style="padding: 8px; width: 200px; margin-right: 10px;"
      />
      <input 
        v-model="novaSigla" 
        placeholder="Sigla (ex: kg)" 
        required 
        style="padding: 8px; width: 100px; margin-right: 10px;"
      />
      <button type="submit" style="padding: 8px 15px; cursor: pointer; background: #42b983; color: white; border: none; border-radius: 4px;">
        Cadastrar
      </button>
    </form>

    <!-- Tabela -->
    <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; max-width: 600px; border-collapse: collapse; text-align: left;">
      <thead style="background: #f2f2f2;">
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Sigla</th>
          <th>Ação</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="u in unidades" :key="u.id">
          <td>{{ u.id }}</td>
          <td>{{ u.nome }}</td>
          <td>{{ u.sigla }}</td>
          <td>
            <button @click="deletarUnidade(u.id)" style="color: red; cursor: pointer; background: none; border: 1px solid red; border-radius: 4px;">
              Excluir
            </button>
          </td>
        </tr>
        <tr v-if="unidades.length === 0">
          <td colspan="4" style="text-align: center; color: #777;">Nenhuma unidade cadastrada.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>