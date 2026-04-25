public class Cliente extends Usuario {
    private String cpf;

    public Cliente(int id, String nome, String cpf) {
        super(id, nome);
        setCpf(cpf);
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) {
        if (cpf != null && cpf.length() == 11) {
            this.cpf = cpf;
        }
    }

    @Override
    public void exibirTipo() {
        System.out.println("Cliente");
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " (CPF: " + cpf + ")";
    }
}