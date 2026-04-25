import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Usuario> usuarios = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();

        Administrador admin = new Administrador(1, "Admin");
        usuarios.add(admin);

        int opcao = 0;

        while (opcao != 6) {

            System.out.println("\n1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Categoria");
            System.out.println("3. Admin - Adicionar Produto");
            System.out.println("4. Listar Usuários (Polimorfismo)");
            System.out.println("5. Remover Produto (Admin)");
            System.out.println("6. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    usuarios.add(new Cliente(usuarios.size()+1, nome, cpf));
                    break;

                case 2:
                    System.out.print("Categoria: ");
                    String nomeCat = scanner.nextLine();
                    categorias.add(new Categoria(categorias.size()+1, nomeCat));
                    break;

                case 3:
                    if (categorias.isEmpty()) {
                        System.out.println("Cadastre categoria primeiro!");
                        break;
                    }

                    System.out.print("Produto: ");
                    String nomeProd = scanner.nextLine();

                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();

                    System.out.print("Estoque: ");
                    int estoque = scanner.nextInt();

                    Produto p = new Produto(produtos.size()+1, nomeProd, preco, estoque, categorias.get(0));

                    admin.adicionarProduto(produtos, p);
                    break;

                case 4:
                    // POLIMORFISMO
                    for (Usuario u : usuarios) {
                        System.out.println(u);
                        u.exibirTipo();
                    }
                    break;

                case 5:
                    System.out.print("ID do produto: ");
                    int id = scanner.nextInt();
                    admin.removerProduto(produtos, id);
                    break;
            }
        }

        scanner.close();
    }
}