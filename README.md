# 🚀 Meu Primeiro Projeto em Java: Sistema de Biblioteca

Bem-vindo ao meu primeiro projeto de programação em Java! Este é um sistema de gerenciamento de biblioteca desenvolvido para aprender e aplicar conceitos fundamentais da linguagem, como manipulação de arquivos, orientação a objetos e interação com o usuário via console.

O objetivo é simular as operações básicas de uma biblioteca, como cadastrar livros, gerenciar clientes e controlar empréstimos.

## ✨ Funcionalidades Principais

*   **Cadastro de Livros e Clientes:** Adicione novos livros e clientes ao sistema.
*   **Consulta de Livros:** Liste todos os livros disponíveis ou busque por um título específico.
*   **Sistema de Empréstimo:** Registre a retirada e a devolução de livros.
*   **Persistência de Dados:** As informações são salvas em arquivos, garantindo que os dados não sejam perdidos ao fechar o programa.

## ⚙️ Como Funciona o Sistema de Registro (O Coração do Projeto)

A parte mais interessante deste projeto é o seu sistema de persistência de dados. Em vez de usar um banco de dados tradicional, eu criei um mecanismo próprio que salva todas as informações em arquivos de texto.

Isso foi feito para entender na prática como os dados podem ser gerenciados e estruturados manualmente.

### A Estrutura de Pastas

O sistema se baseia em duas pastas principais na raiz do projeto:

1.  `register_list/`: Esta pasta funciona como nosso "banco de dados". Ela armazena os registros de cada entidade do sistema em subpastas separadas:
    *   `register_list/book/`: Cada arquivo aqui dentro representa um livro cadastrado.
    *   `register_list/client/`: Cada arquivo aqui representa um cliente.
    *   `register_list/author/`: Cada arquivo representa um autor.

2.  `id_list/`: Para garantir que cada livro, cliente ou autor tenha um identificador único, esta pasta armazena o controle do próximo ID a ser utilizado para cada tipo de registro. Isso evita que existam dois livros com o mesmo ID, por exemplo.

### O Processo de Cadastro na Prática

Quando você cadastra um novo livro, por exemplo, o sistema executa os seguintes passos:

1.  **Busca o Próximo ID:** O sistema consulta a pasta `id_list` para saber qual é o próximo ID de livro disponível.
2.  **Cria o Arquivo de Registro:** Ele cria um novo arquivo `.txt` dentro da pasta `register_list/book/`. O nome do arquivo é o ID que ele acabou de obter (ex: `1.txt`).
3.  **Salva as Informações:** As informações do livro (título, autor, etc.) são escritas dentro deste novo arquivo.
4.  **Atualiza o Contador de ID:** Por fim, o sistema atualiza o controle na pasta `id_list` para que o próximo livro a ser cadastrado receba um novo ID, garantindo a sequência.

Este método, embora simples, é muito eficaz para entender os fundamentos do armazenamento e da manipulação de dados.

## ▶️ Como Executar o Projeto

1.  Clone ou faça o download do repositório.
2.  Abra o projeto em sua IDE Java preferida (como IntelliJ IDEA ou Eclipse).
3.  Localize e execute o arquivo `Main.java`.
4.  O programa será iniciado no console, onde você poderá interagir com os comandos.

### Comandos do Console

*   `l`: Lista todos os livros.
*   `s`: Busca um livro pelo nome.
*   `b`: Empresta um livro.
*   `d`: Devolve um livro.
*   `e`: Encerra o programa.

Espero que esta explicação tenha tornado o funcionamento do projeto mais claro. Sinta-se à vontade para explorar o código e ver como tudo se conecta!
