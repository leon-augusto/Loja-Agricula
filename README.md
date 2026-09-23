# 🌾 Loja Agrícola

Sistema de gestão de inventário agrícola modernizado a partir de uma aplicação legada desenvolvida em Java Swing (NetBeans 8.2 / JDK 8). A versão atual utiliza uma arquitetura *Full Stack* desacoplada com API REST em **Java 21 (Spring Boot 3)**, interface **Vue.js 3 (Vite + Bootstrap 5)** e base de dados **MySQL 8**.

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Linguagem:** Java 21
- **Framework:** Spring Boot 3
- **ORM / Persistência:** Spring Data JPA / Hibernate
- **Construção / Gestão de Dependências:** Gradle
- **Base de Dados:** MySQL 8

### Frontend
- **Framework:** Vue.js 3 (Composition API)
- **Ferramenta de Build:** Vite
- **Navegação:** Vue Router
- **Cliente HTTP:** Axios
- **Interface / Estilos:** Bootstrap 5 (CDN)

---

## 📁 Estrutura do Repositório

```text
Loja-Agricula/
├── backend/                # API REST Spring Boot
│   ├── src/main/java/     # Entidades, Repositórios e Controladores
│   └── src/main/resources/# Configurações de acesso à base de dados (application.properties)
├── frontend/               # Single Page Application (SPA) em Vue.js 3
│   ├── src/views/         # Ecrãs do sistema (Painel de Controlo, Tipos, Unidades, Ferramentas, Insumos)
│   └── src/router/        # Definição das rotas de navegação
└── README.md
```

## ⚙️ Funcionalidades
Painel de Controlo (Dashboard): Métricas de inventário em tempo real, cálculo do valor total em stock e alertas automáticos para produtos com stock baixo (< 5 unidades).

- Gestão de Tipos / Categorias: CRUD completo para categorização de produtos.

- Gestão de Unidades de Medida: Registo de unidades (ex.: Kg, Litros, Unidades).

- Gestão de Ferramentas: Registo e controlo de ferramentas vinculadas a categorias.

- Gestão de Insumos: Registo avançado de insumos associados a tipos e unidades de medida.

## 🛠️ Como Executar o Projeto
Pré-requisitos
- **Java JDK 21+**

- **Node.js 22+**

- **MySQL 8+**

1. Configurar a Base de Dados
Acede à consola do MySQL e cria a base de dados e o utilizador:
~~~sql
SQL
CREATE DATABASE loja_agricola CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'loja_user'@'localhost' IDENTIFIED BY 'loja123';
GRANT ALL PRIVILEGES ON loja_agricola.* TO 'loja_user'@'localhost';
FLUSH PRIVILEGES;
~~~

### 2. Executar o Backend (Spring Boot)
No terminal, entra na pasta backend e inicia o servidor:

~~~bash
cd backend
./gradlew bootRun
~~~
A API ficará disponível em http://localhost:8080.

3. Executar o Frontend (Vue.js)
Num segundo terminal, entra na pasta frontend, instala as dependências e arranca o servidor de desenvolvimento:

~~~bash
cd frontend
npm install
npm run dev
~~~
Acede à aplicação web em http://localhost:5173.