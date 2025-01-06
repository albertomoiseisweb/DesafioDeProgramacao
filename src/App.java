import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        // Falta fazer
        // Representação do produto em formato legível
        @Override
        public String toString() {
            return String.format("ID: %d | Nome: %s | Categoria: %s | Quantidade: %d | Preço: %.2f",
                    id, nome, categoria, quantidade, preco);
        }

        // Converte o produto para uma linha CSV
        public String toCsv() {
            return id + "," + nome + "," + categoria + "," + quantidade + "," + preco;
        }

        // Falta fazer
        // Cria um objeto Produto a partir de uma linha CSV
        public static Produto fromCsv(String csvLine) {
            String[] parts = csvLine.split(",");
            return new Produto(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    Integer.parseInt(parts[3]),
                    Double.parseDouble(parts[4]));
        }
    }
// Lista para armazenar os produtos
    private static List<Produto> inventario = new ArrayList<>(); 
    // Próximo ID a ser atribuíd
    private static int nextId = 1; 
    // Caminho do arquivo CSV
    private static final String FILE_PATH = "inventario.csv"; 
    public static void main(String[] args) {
        carregarDados(); // Carrega os dados do arquivo CSV ao iniciar
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
                case 5:
                    buscarProduto(scanner);
                    break;
                case 6:
                    salvarDados(); // Salva os dados antes de sair
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
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

    // Busca produtos no inventário por ID ou nome
    private static void buscarProduto(Scanner scanner) {
        System.out.print("Buscar por ID ou Nome (1 para ID, 2 para Nome): ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            System.out.print("Informe o ID: ");
            int id = scanner.nextInt();

            Produto produto = inventario.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

            if (produto == null) {
                System.out.println("Produto não encontrado.");
            } else {
                System.out.println("Produto encontrado: " + produto);
            }

        } else if (escolha == 2) {
            System.out.print("Informe o Nome ou parte do Nome: ");
            String nome = scanner.nextLine();

            List<Produto> resultados = inventario.stream()
                    .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());

            if (resultados.isEmpty()) {
                System.out.println("Nenhum produto encontrado.");
            } else {
                System.out.println("Produtos encontrados:");
                for (Produto produto : resultados) {
                    System.out.println(produto);
                }
            }

        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Salva os dados do inventário no arquivo CSV
    private static void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Produto produto : inventario) {
                writer.write(produto.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Carrega os dados do arquivo CSV para o inventário
    private static void carregarDados() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Produto produto = Produto.fromCsv(line);
                inventario.add(produto);
                nextId = Math.max(nextId, produto.getId() + 1);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}
