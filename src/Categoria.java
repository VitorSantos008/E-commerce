public class Categoria {
    private int id;
    private String nome;

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "Categoria: " + nome;
    }
}