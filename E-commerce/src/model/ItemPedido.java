package model;

public class ItemPedido {
        private final Produto produto;
        private int quantidade;

        public ItemPedido(Produto produto, int quantidade) {
            this.produto = produto;
            setQuantidade(quantidade);
        }

        public Produto getProduto() { return produto; }
        public int getQuantidade() { return quantidade; }

        public void setQuantidade(int quantidade) {
            if (quantidade > 0) this.quantidade = quantidade;
        }
}

