public class Fornecedor {
    private int id;
    private String nomeSocial;
    private String cnpj;

    public Fornecedor(int id, String nomeSocial, String cnpj) {
        this.id = id;
        this.nomeSocial = nomeSocial;
        this.cnpj = cnpj;
    }

    public String getNomeSocial() { return nomeSocial; }

    @Override
    public String toString() {
        return "Fornecedor: " + nomeSocial + " (CNPJ: " + cnpj + ")";
    }
}