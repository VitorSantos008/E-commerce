public abstract class Usuario {
    protected int id;
    protected String nome;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    public abstract void exibirTipo();

    @Override
    public String toString() {
        return "Usuário: " + nome;
    }
}