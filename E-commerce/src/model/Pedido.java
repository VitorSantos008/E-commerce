package model;


import service.GerenciadorEstoque;
import service.CalculadoraPedido;
import util.Calculavel;
import util.Auditavel;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Calculavel, Auditavel {
    private final int id;
    private final Cliente cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private String status;
    private List<String> historico = new ArrayList<>();
    private CalculadoraPedido calculadora = new CalculadoraPedido();
    private GerenciadorEstoque estoque = new GerenciadorEstoque();

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.status = "Aberto";
    }


    public void adicionarItem(Produto p, int qtd) {
        if (estoque.verificarEstoque(p, qtd)) {
            itens.add(new ItemPedido(p, qtd));
            registrarLog(
                    "Produto adicionado: "
                            + p.getNome()
                            + " | Quantidade: "
                            + qtd
            );
        } else {
            System.out.println(
                    "❌ Erro: Estoque insuficiente para "
                            + p.getNome()
                            + " (Disponível: "
                            + p.getEstoque() + ")"
            );
        }
    }

    public void removerItem(int idProduto) {
        ItemPedido itemRemover = null;

        for (ItemPedido item : itens) {
            if (item.getProduto().getId() == idProduto) {
                itemRemover = item;
                break;
            }
        }

        if (itemRemover != null) {
            itens.remove(itemRemover);
            registrarLog("Produto removido do pedido: " + itemRemover.getProduto().getNome());
            System.out.println("Produto removido do pedido.");
        } else {
            System.out.println("Produto não encontrado no pedido.");
        }
    }

    public void confirmarPedido() {
        if (itens.isEmpty()) {
            System.out.println("Não é possível confirmar um pedido vazio.");
            return;
        }
        for (ItemPedido item : itens) {
            Produto p = item.getProduto();
            estoque.reduzirEstoque(item);

            if (p.getEstoque() < 10) {
                System.out.println("⚠️ ALERTA: Estoque baixo para " + p.getNome());
            }
        }

        registrarLog("Pedido confirmado");
        this.status = "Confirmado";
    }

    public double calcularSubtotal() {
        double sub = 0;
        for (ItemPedido i : itens) sub += i.getProduto().getPreco() * i.getQuantidade();
        return sub;
    }

    @Override
    public double calcularDesconto() {
        return calculadora.calcularDesconto(this);
    }

    @Override
    public String toString() {
        double subtotal = calcularSubtotal();
        double desc = calcularDesconto();
        return String.format("Model.Pedido #%d | Model.Cliente: %s\nSubtotal: R$ %.2f\nDesconto: R$ %.2f\nTotal: R$ %.2f",
                id, cliente.getNome(), subtotal, desc, (subtotal - desc));
    }
    @Override
    public double calcularTotal() {
        return calculadora.calcularSubtotal(this);
    }

    @Override
    public double calcularValorFinal() {
        return calculadora.calcularValorFinal(this);
    }
    @Override
    public void registrarLog(String acao) {
        historico.add(acao);
    }
    @Override
    public String obterHistorico() {

        String texto = "";

        for(String log : historico) {
            texto += log + "\n";
        }

        return texto;
    }
}
