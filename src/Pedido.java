import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private String status;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.status = "Aberto";
    }

    public void adicionarItem(Produto p, int qtd) {
        if (p.getEstoque() >= qtd) {
            itens.add(new ItemPedido(p, qtd));
        } else {
            System.out.println("❌ Erro: Estoque insuficiente para " + p.getNome() +
                    " (Disponível: " + p.getEstoque() + ")");
        }
    }

    public void confirmarPedido() {
        for (ItemPedido item : itens) {
            Produto p = item.getProduto();
            p.setEstoque(p.getEstoque() - item.getQuantidade());

            if (p.getEstoque() < 10) {
                System.out.println("⚠️ ALERTA: Estoque baixo para " + p.getNome());
            }
        }
        this.status = "Confirmado";
    }

    public double calcularSubtotal() {
        double sub = 0;
        for (ItemPedido i : itens) sub += i.getProduto().getPreco() * i.getQuantidade();
        return sub;
    }

    public double calcularDesconto() {
        double subtotal = calcularSubtotal();
        if (subtotal > 1000) return subtotal * 0.10;
        if (subtotal > 500) return subtotal * 0.05;
        return 0;
    }

    @Override
    public String toString() {
        double subtotal = calcularSubtotal();
        double desc = calcularDesconto();
        return String.format("Pedido #%d | Cliente: %s\nSubtotal: R$ %.2f\nDesconto: R$ %.2f\nTotal: R$ %.2f",
                id, cliente.getNome(), subtotal, desc, (subtotal - desc));
    }
}