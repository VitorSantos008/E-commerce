
// Produto.java

package model;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private Categoria categoria;

    public Produto(int id, String nome, double preco, int estoque, Categoria categoria) {

        this.id = id;
        this.nome = nome;
        setPreco(preco);
        setEstoque(estoque);
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setEstoque(int estoque) {

        if (estoque >= 0) {
            this.estoque = estoque;
        }
    }

    public void setPreco(double preco) {

        if (preco > 0) {
            this.preco = preco;
        }
    }

    @Override
    public String toString() {

        return String.format(
                "ID: %d | %s | R$ %.2f | Estoque: %d",
                id,
                nome,
                preco,
                estoque
        );
    }
}