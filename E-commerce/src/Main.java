import model.*;
import dao.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Usuario> usuarios = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        ClienteDao clienteDAO = new ClienteDao();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();

        Administrador admin = new Administrador(1, "Admin");
        usuarios.add(admin);

        usuarios.addAll(clienteDAO.listar());
        categorias.addAll(categoriaDAO.listar());
        produtos.addAll(produtoDAO.listar(categorias));

        int opcaoLogin = -1;

        while (opcaoLogin != 0) {

            System.out.println("\n=== LOGIN ===");
            System.out.println("1 - Login Administrador"); // login:admin senha:1234
            System.out.println("2 - Login Cliente");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcaoLogin = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoLogin) {

                case 1:
                    System.out.print("Usuário: ");
                    String usuario = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    if (usuario.equals("admin") && senha.equals("1234")) {
                        menuAdmin(scanner, admin, produtos, categorias, produtoDAO, categoriaDAO);
                    } else {
                        System.out.println("Usuário ou senha inválidos.");
                    }
                    break;

                case 2:
                    System.out.print("CPF: ");
                    String cpfLogin = scanner.nextLine();


                    Cliente clienteLogado = null;

                    for (Usuario u : usuarios) {
                        if (u instanceof Cliente) {
                            Cliente c = (Cliente) u;

                            if (c.getCpf().equals(cpfLogin)) {
                                clienteLogado = c;
                                break;
                            }
                        }
                    }

                    if (clienteLogado == null) {
                        System.out.println("Cliente não encontrado.");
                    } else {
                        menuCliente(scanner, clienteLogado, produtos, categorias, pedidos);
                    }
                    break;

                case 3:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (nome.trim().isEmpty()) {
                        System.out.println("Nome não pode ficar vazio.");
                        break;
                    }

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    if (cpf.trim().isEmpty()) {
                        System.out.println("CPF não pode ficar vazio.");
                        break;
                    }

                    if (cpf.length() != 11) {
                        System.out.println("CPF deve ter 11 números.");
                        break;
                    }

                    Cliente novoCliente = new Cliente(usuarios.size() + 1, nome, cpf);

                    usuarios.add(novoCliente);
                    clienteDAO.inserir(novoCliente);

                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    public static void menuAdmin(
            Scanner scanner,
            Administrador admin,
            List<Produto> produtos,
            List<Categoria> categorias,
            ProdutoDAO produtoDAO,
            CategoriaDAO categoriaDAO
    ) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1 - Cadastrar Categoria");
            System.out.println("2 - Adicionar Produto");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("5 - Listar Produtos");
            System.out.println("6 - Listar Categorias");
            System.out.println("7 - Atualizar Categoria");
            System.out.println("8 - Remover Categoria");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome da categoria: ");
                    String nomeCat = scanner.nextLine();

                    Categoria categoria = new Categoria(categorias.size() + 1, nomeCat);

                    categorias.add(categoria);
                    categoriaDAO.inserir(categoria);

                    System.out.println("Categoria cadastrada com sucesso!");
                    break;

                case 2:
                    if (categorias.isEmpty()) {
                        System.out.println("Cadastre uma categoria primeiro.");
                        break;
                    }

                    System.out.print("Nome do produto: ");
                    String nomeProd = scanner.nextLine();

                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    if (preco <= 0) {
                        System.out.println("Preço deve ser maior que zero.");
                        break;
                    }

                    System.out.print("Estoque: ");
                    int estoque = scanner.nextInt();
                    if (estoque < 0) {
                        System.out.println("Estoque inválido.");
                        break;
                    }

                    Produto produto = new Produto(
                            produtos.size() + 1,
                            nomeProd,
                            preco,
                            estoque,
                            categorias.get(0)
                    );

                    admin.adicionarProduto(produtos, produto);
                    produtoDAO.inserir(produto);

                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.print("ID do produto: ");
                    int idAtualizar = scanner.nextInt();

                    Produto produtoAtualizar = null;

                    for (Produto p : produtos) {
                        if (p.getId() == idAtualizar) {
                            produtoAtualizar = p;
                            break;
                        }
                    }

                    if (produtoAtualizar == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();

                    System.out.print("Novo estoque: ");
                    int novoEstoque = scanner.nextInt();

                    produtoAtualizar.setPreco(novoPreco);
                    produtoAtualizar.setEstoque(novoEstoque);

                    produtoDAO.atualizar(produtoAtualizar);

                    System.out.println("Produto atualizado com sucesso!");
                    break;

                case 4:
                    System.out.print("ID do produto: ");
                    int idRemover = scanner.nextInt();

                    admin.removerProduto(produtos, idRemover);
                    produtoDAO.remover(idRemover);

                    System.out.println("Produto removido com sucesso!");
                    break;

                case 5:
                    System.out.println("=== PRODUTOS ===");

                    for (Produto p : produtos) {
                        System.out.println(p);
                    }
                    break;

                case 6:
                    System.out.println("=== CATEGORIAS ===");

                    for (Categoria c : categorias) {
                        System.out.println(c);
                    }
                    break;

                case 7:
                    System.out.print("ID da categoria que deseja atualizar: ");
                    int idCategoriaAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    Categoria categoriaAtualizar = null;

                    for (Categoria c : categorias) {
                        if (c.getId() == idCategoriaAtualizar) {
                            categoriaAtualizar = c;
                            break;
                        }
                    }

                    if (categoriaAtualizar == null) {
                        System.out.println("Categoria não encontrada.");
                        break;
                    }

                    System.out.print("Novo nome da categoria: ");
                    String novoNomeCategoria = scanner.nextLine();

                    categoriaAtualizar.setNome(novoNomeCategoria);

                    categoriaDAO.atualizar(categoriaAtualizar);

                    System.out.println("Categoria atualizada com sucesso!");
                    break;


                case 8:
                    System.out.print("ID da categoria que deseja remover: ");
                    int idCategoriaRemover = scanner.nextInt();

                    Categoria categoriaRemover = null;

                    for (Categoria c : categorias) {
                        if (c.getId() == idCategoriaRemover) {
                            categoriaRemover = c;
                            break;
                        }
                    }

                    if (categoriaRemover == null) {
                        System.out.println("Categoria não encontrada.");
                        break;
                    }

                    categorias.remove(categoriaRemover);
                    categoriaDAO.remover(idCategoriaRemover);

                    System.out.println("Categoria removida com sucesso!");
                    break;

                case 0:
                    System.out.println("Logout do administrador.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void menuCliente(
            Scanner scanner,
            Cliente clienteLogado,
            List<Produto> produtos,
            List<Categoria> categorias,
            List<Pedido> pedidos
    ) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("Cliente logado: " + clienteLogado.getNome());
            System.out.println("1 - Criar Pedido");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Listar Categorias");
            System.out.println("4 - Meus Pedidos");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }

                    Pedido pedido = new Pedido(pedidos.size() + 1, clienteLogado);

                    int continuar = 1;

                    while (continuar == 1) {

                        System.out.println("=== PRODUTOS ===");

                        for (Produto produto : produtos) {
                            System.out.println(produto);
                        }

                        System.out.print("ID do produto: ");
                        int idProduto = scanner.nextInt();

                        Produto produtoSelecionado = null;

                        for (Produto produtoAtual : produtos) {
                            if (produtoAtual.getId() == idProduto) {
                                produtoSelecionado = produtoAtual;
                                break;
                            }
                        }

                        if (produtoSelecionado == null) {
                            System.out.println("Produto não encontrado.");
                            continue;
                        }

                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();

                        pedido.adicionarItem(produtoSelecionado, quantidade);

                        System.out.print("Adicionar mais produtos? (1-Sim / 0-Não): ");
                        continuar = scanner.nextInt();
                    }

                    pedido.confirmarPedido();
                    pedidos.add(pedido);

                    System.out.println(pedido);
                    System.out.println("=== HISTÓRICO ===");
                    System.out.println(pedido.obterHistorico());

                    break;

                case 2:
                    System.out.println("=== PRODUTOS ===");

                    for (Produto p : produtos) {
                        System.out.println(p);
                    }
                    break;

                case 3:
                    System.out.println("=== CATEGORIAS ===");

                    for (Categoria c : categorias) {
                        System.out.println(c);
                    }
                    break;

                case 4:
                    System.out.println("=== MEUS PEDIDOS ===");

                    boolean encontrouPedido = false;

                    for (Pedido p : pedidos) {
                        if (p.toString().contains(clienteLogado.getNome())) {
                            System.out.println(p);
                            System.out.println("=== HISTÓRICO ===");
                            System.out.println(p.obterHistorico());
                            encontrouPedido = true;
                        }
                    }

                    if (!encontrouPedido) {
                        System.out.println("Nenhum pedido encontrado.");
                    }

                    break;

                case 0:
                    System.out.println("Logout do cliente.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    public static int lerInteiro(Scanner scanner, String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                int numero = scanner.nextInt();
                scanner.nextLine();

                return numero;

            } catch (InputMismatchException e) {

                System.out.println("Digite apenas números inteiros.");
                scanner.nextLine();
            }
        }
    }
    public static double lerDouble(Scanner scanner, String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                double numero = scanner.nextDouble();
                scanner.nextLine();

                return numero;

            } catch (InputMismatchException e) {

                System.out.println("Digite um valor numérico válido.");
                scanner.nextLine();
            }
        }
    }
}