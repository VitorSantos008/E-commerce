package service;

import model.ItemPedido;
import model.Produto;

public class GerenciadorEstoque {

    public boolean verificarEstoque(Produto produto, int quantidade) {
        return produto.getEstoque() >= quantidade;
    }

    public void reduzirEstoque(ItemPedido item) {

        Produto produto = item.getProduto();

        int novoEstoque =
                produto.getEstoque() - item.getQuantidade();

        produto.setEstoque(novoEstoque);

        if (novoEstoque < 10) {
            System.out.println(
                    "⚠ Estoque baixo do produto: "
                            + produto.getNome()
            );
        }
    }
}