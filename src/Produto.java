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
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) this.nome = nome;
    }

    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco > 0) this.preco = preco;
    }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) {
        if (estoque >= 0) this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d | Cat: %s",
                id, nome, preco, estoque, categoria.getNome());
    }
}