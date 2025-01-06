# Desafio de Programação - 2025/1

## Gerenciamento de Produtos para a Loja AgilStore

Este projeto foi desenvolvido para otimizar o controle de inventário da loja AgilStore, uma loja de eletrônicos que busca gerenciar seu catálogo de produtos de forma eficiente e precisa. A aplicação permite operações como adicionar, listar, atualizar, excluir e buscar produtos.
## Funcionalidades

1. **Adicionar Produto**: 
   - Permite inserir novos produtos no inventário com os seguintes dados:
     - Nome do Produto
     - Categoria
     - Quantidade em Estoque
     - Preço
   - Cada produto recebe um ID único automaticamente.

2. **Listar Produtos**: 
   - Exibe todos os produtos cadastrados em formato de tabela:
     - ID, Nome, Categoria, Quantidade e Preço
   - Opções de filtragem por categoria e ordenação por nome, quantidade ou preço (opcional).

3. **Atualizar Produto**: 
   - Permite alterar dados de um produto existente pelo ID.
   - Campos atualizáveis: Nome, Categoria, Quantidade, Preço.

4. **Excluir Produto**: 
   - Remove um produto do inventário pelo ID, com confirmação opcional.

5. **Buscar Produto**: 
   - Busca um produto específico pelo ID ou parte do nome.

6. **Persistência de Dados** *(Opcional)*: 
   - Os dados são salvos automaticamente em um arquivo JSON.


## Tecnologias Utilizadas

- Linguagem: Python, Node.js ou Java (escolha uma ao implementar)
- Terminal para interações com o usuário.

## Como Configurar e Executar o Projeto

1. **Clonar o Repositório**:
   ```bash
   git clone git@github.com:albertomoiseisweb/DesafioDeProgramacao.git
   ```

2. **Acessar o Diretório do Projeto**:
   ```bash
   cd DesafioDeProgramacao
   ```

3. **Instalar Dependências** *(se necessário)*:
   - Java:
     Configure o ambiente usando um gerenciador de dependências como Maven ou Gradle.

4. **Executar a Aplicação**:

- Java:
     Compile e execute o arquivo principal:
```bash
     javac Main.java
     java Main
```

5. **Interagir com a Aplicação**:
   - Use os comandos disponíveis no terminal para adicionar, listar, atualizar, excluir e buscar produtos.


## Estrutura do Projeto

- `src/`: Código-fonte do projeto.
- `data/`: Arquivo JSON para persistência de dados (opcional).
- `README.md`: Este arquivo.

## Contribuição

Sinta-se à vontade para contribuir com melhorias no projeto. Basta abrir uma issue ou enviar um pull request no repositório oficial.

## Licença

Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais informações.
