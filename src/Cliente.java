public class Cliente {
    private int id;
    private String nome;
    private String cpf;

    public Cliente(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        setCpf(cpf);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    public void setCpf(String cpf) {
        if (cpf != null && cpf.length() == 11) this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " (CPF: " + cpf + ")";
    }
}