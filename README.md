# 🎨 Diario Cultural FX

## 📝 Descrição

O Diario Cultural FX é uma aplicação de desktop desenvolvida em JavaFX para gerenciar um diário de mídias culturais. Ele permite que os usuários cadastrem, avaliem, listem, editem e busquem por filmes, livros e séries que consumiram.

## ⚙️ Funcionalidades

- 🆕 **Cadastro de Mídias:** Adicione novos filmes, livros e séries ao seu diário.
- 🌟 **Avaliação de Mídias:** Atribua notas e escreva resenhas para as mídias cadastradas.
- 📋 **Listagem de Mídias:** Visualize uma lista de todas as mídias cadastradas.
- 🔎 **Busca de Mídias:** Procure por mídias específicas em seu diário.
- ✏️ **Edição de Mídias:** Modifique as informações das mídias já cadastradas.
- 🗑️ **Exclusão de Mídias:** Delete as informações de mídias já cadastradas.
- 💾 **Gerenciamento de Dados:** Backup e restauração dos dados do diário a partir de um arquivo JSON.

## 🛠️ Tecnologias Utilizadas

- **Java:** Linguagem de programação principal.
- **JavaFX:** Framework para a interface gráfica.
- **Maven:** Ferramenta de automação de compilação e gerenciamento de dependências.
- **JSON:** Formato de arquivo para armazenamento de dados.

## 🚀 Como Executar o Projeto

### Pré-requisitos

- JDK (Java Development Kit) 11 ou superior.
- Apache Maven.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/antoniomediross/diario_cultural_fx.git
    cd diario_cultural_fx
    ```

2.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn javafx:run
    ```

## 🗂️ Estrutura do Projeto

O projeto segue a estrutura padrão de um projeto Maven:

-   `src/main/java`: Contém o código-fonte da aplicação em Java.
    -   `com.antonio.diarioculturalfx.controller`: Controladores da interface gráfica (padrão MVC).
    -   `com.antonio.diarioculturalfx.model`: Classes de modelo que representam as mídias.
    -   `com.antonio.diarioculturalfx.repository`: Classes responsáveis pelo acesso e manipulação dos dados.
    -   `com.antonio.diarioculturalfx.services`: Classes de serviço que contêm a lógica de negócio.
    -   `com.antonio.diarioculturalfx.util`: Classes utilitárias.
-   `src/main/resources`: Contém os recursos da aplicação.
    -   `com.antonio.diarioculturalfx.view`: Arquivos FXML que definem a interface gráfica.
    -   `com.antonio.diarioculturalfx.styles`: Arquivos de estilo CSS.
    -   `com.antonio.diarioculturalfx.icons`: Ícones utilizados na aplicação.
-   `pom.xml`: Arquivo de configuração do Maven, que define as dependências e o processo de build do projeto.


## 👨‍💻 Desenvolvedores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/antoniomedeiross">
        <img src="https://github.com/antoniomedeiross.png" width="100px;" alt="Foto de Usuario1"/><br />
        <sub><b>Antonio Medeiros</b></sub>
      </a>
      <br>
      <br>
      <a href="https://linkedin.com/in/antoniomedeiross" title="LinkedIn">
        <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="30px"/>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/NycolasDeLima">
        <img src="https://github.com/NycolasDeLima.png" width="100px;" alt="Foto de Usuario2"/><br />
        <sub><b>Nycolas de Lima</b></sub>
      </a> 
      <br>
      <br>
      <a href="https://linkedin.com/in/" title="LinkedIn">
        <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="30px"/>
      </a>
    </td>
  </tr>
</table>
