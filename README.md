# 📱 Lista Telefónica – Projeto Final Android (Kotlin)

Este projeto foi desenvolvido como trabalho final do curso **“Iniciação ao Desenvolvimento de Aplicações para Android (Kotlin)”**.
O objetivo é criar uma aplicação Android que **simule uma lista telefónica**, implementando as principais funcionalidades de um CRUD (Create, Read, Update, Delete), autenticação simples por PIN e persistência de dados.

---

## 🎯 Objetivo do Projeto

Criar uma aplicação Android funcional que:

* Apresente uma **lista de contactos** organizada e interativa.
* Permita **inserir, editar e eliminar contactos**.
* Inclua um sistema simples de **login com PIN numérico **.
* Exiba detalhes de cada contacto e simule uma **chamada telefónica**.
* Guarde e recupere os dados entre sessões.

---

## 🧩 Funcionalidades Principais

1. **Ecrã de Login com PIN **

   * Validação do PIN com mensagens Toast (“Login válido” / “Login inválido”).
   * Possibilidade de **alterar o PIN** na Activity “Sobre” e no "Login".

2. **Lista Telefónica (ListView ou RecyclerView)**

   * Mostra todos os contactos ordenados alfabeticamente.
   * Permite ordenar a lista em **ordem crescente e decrescente** (A→Z / Z→A).

3. **Detalhes do Contacto**

   * Ao clicar num contacto, são mostrados:

     * Nome
     * Morada
     * Telefone/Telemóvel
     * Email
   * Permite abrir a aplicação de chamadas com o número preenchido.

4. **Gestão de Contactos**

   * Adicionar novo contacto.
   * Editar contacto existente.
   * Eliminar contacto.

5. **Activity “Sobre”**

   * Mostra a **identificação do autor**.
   * Permite **alterar o PIN de acesso**.

6. **Splash Screen**

   * Apresenta o logótipo/nome da aplicação antes do ecrã de login.

7. **Persistência de Dados**

   * Todos os contactos e o PIN são **guardados entre sessões** (usando `SharedPreferences` ou base de dados local).

---

## 💾 Tecnologias Utilizadas

* **Linguagem:** Kotlin
* **IDE:** Android Studio
* **Interface:** XML Layouts
* **Armazenamento:** `SharedPreferences`
* **Componentes principais:**

  * `Activity`, `Intent`, `ListView`/`RecyclerView`, `Toast`, `EditText`, `Button`, `ImageView`

---

## 🚀 Como Executar o Projeto

1. Abrir o **Android Studio**.
2. Selecionar **File → Open...** e escolher a pasta do projeto.
3. Esperar o Gradle sincronizar.
4. Correr a aplicação num **emulador** ou dispositivo Android real.
5. Introduzir o PIN `1234` no login inicial.
---

© 2025 – Curso “Iniciação ao Desenvolvimento de Aplicações para Android (Kotlin)”
