import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Categoria> categorias = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        int opcao = 0;

        System.out.println("=== SISTEMA DE E-COMMERCE (CP1) ===");

        while (opcao != 5) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Realizar Pedido (Simulação)");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome da Categoria: ");
                    String nomeCat = scanner.nextLine();
                    categorias.add(new Categoria(categorias.size() + 1, nomeCat));
                    System.out.println("✅ Categoria cadastrada!");
                    break;

                case 2:
                    if (categorias.isEmpty()) {
                        System.out.println("❌ Cadastre uma categoria primeiro!");
                        break;
                    }
                    System.out.print("Nome do Produto: ");
                    String nomeProd = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Quantidade em Estoque: ");
                    int estoque = scanner.nextInt();


                    Produto novoProd = new Produto(produtos.size() + 1, nomeProd, preco, estoque, categorias.get(0));
                    produtos.add(novoProd);
                    System.out.println("✅ Produto cadastrado: " + novoProd);
                    break;

                case 3:
                    System.out.print("Nome do Cliente: ");
                    String nomeCli = scanner.nextLine();
                    System.out.print("CPF (11 dígitos): ");
                    String cpf = scanner.nextLine();
                    clientes.add(new Cliente(clientes.size() + 1, nomeCli, cpf));
                    System.out.println("✅ Cliente cadastrado!");
                    break;

                case 4:
                    if (clientes.isEmpty() || produtos.isEmpty()) {
                        System.out.println("❌ É necessário ter clientes e produtos cadastrados!");
                        break;
                    }

                    Pedido pedido = new Pedido(1, clientes.get(0));
                    System.out.println("\n--- Iniciando Pedido para: " + clientes.get(0).getNome() + " ---");

                    for (Produto p : produtos) {
                        System.out.print("Quantas unidades de '" + p.getNome() + "' deseja comprar? ");
                        int qtd = scanner.nextInt();
                        if (qtd > 0) {
                            pedido.adicionarItem(p, qtd);
                        }
                    }

                    System.out.println("\n--- Resumo do Pedido ---");
                    System.out.println(pedido.toString());

                    System.out.print("\nConfirmar fechamento do pedido? (s/n): ");
                    String confirmar = scanner.next();

                    if (confirmar.equalsIgnoreCase("s")) {
                        pedido.confirmarPedido();
                        System.out.println("✅ Pedido finalizado com sucesso!");
                    } else {
                        System.out.println("      Pedido cancelado.");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida!");
            }
        }
        scanner.close();
    }
}