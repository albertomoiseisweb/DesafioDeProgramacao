import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
        // Classe Produto para representar os produtos no inventário
    static class Produto {
        private int id; // Identificador único do produto
        private String nome; // Nome do produto
        private String categoria; // Categoria do produto
        private int quantidade; // Quantidade em estoque
        private double preco; // Preço do produto

        // Construtor para inicializar um produto
        public Produto(int id, String nome, String categoria, int quantidade, double preco) {
            this.id = id;
            this.nome = nome;
            this.categoria = categoria;
            this.quantidade = quantidade;
            this.preco = preco;
        }

        // Métodos getters e setters para acessar e modificar os atributos
        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

    
    }

    private static List<Produto> inventario = new ArrayList<>(); // Lista para armazenar os produtos
    private static int nextId = 1; // Próximo ID a ser atribuído
  
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            // Exibe o menu de opções
            System.out.println("\nBem-vindo ao Gerenciamento de Produtos da AgilStore");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    adicionarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
            
                case 3:
                    atualizarProduto(scanner);
                    break;
                case 4:
                    excluirProduto(scanner);
                    break;
                 /*
                case 5:
                    buscarProduto(scanner);
                    break;
                case 6:
                    salvarDados(); // Salva os dados antes de sair
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                 */
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close(); // Fecha o scanner ao finalizar
    }

      // Adiciona um novo produto ao inventário
      private static void adicionarProduto(Scanner scanner) {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Quantidade em Estoque: ");
        int quantidade = scanner.nextInt();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        Produto produto = new Produto(nextId++, nome, categoria, quantidade, preco);
        inventario.add(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    // Lista todos os produtos no inventário
    private static void listarProdutos() {
        if (inventario.isEmpty()) {
            System.out.println("O inventário está vazio.");
            return;
        }

        System.out.println("\nProdutos no Inventário:");
        for (Produto produto : inventario) {
            System.out.println(produto);
        }
    }

    // Atualiza as informações de um produto existente
    private static void atualizarProduto(Scanner scanner) {
        System.out.print("Informe o ID do produto a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = inventario.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Produto encontrado: " + produto);
        System.out.print("Novo Nome (deixe em branco para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) {
            produto.setNome(nome);
        }

        System.out.print("Nova Categoria (deixe em branco para manter): ");
        String categoria = scanner.nextLine();
        if (!categoria.isBlank()) {
            produto.setCategoria(categoria);
        }

        System.out.print("Nova Quantidade (deixe em branco para manter): ");
        String quantidadeStr = scanner.nextLine();
        if (!quantidadeStr.isBlank()) {
            produto.setQuantidade(Integer.parseInt(quantidadeStr));
        }

        System.out.print("Novo Preço (deixe em branco para manter): ");
        String precoStr = scanner.nextLine();
        if (!precoStr.isBlank()) {
            produto.setPreco(Double.parseDouble(precoStr));
        }

        System.out.println("Produto atualizado com sucesso!");
    }

    // Remove um produto do inventário
    private static void excluirProduto(Scanner scanner) {
        System.out.print("Informe o ID do produto a ser excluído: ");
        int id = scanner.nextInt();

        Produto produto = inventario.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        inventario.remove(produto);
        System.out.println("Produto excluído com sucesso!");
    }


}
