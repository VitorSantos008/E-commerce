package model;

public class Fornecedor {
    private final int id;
    private final String nomeSocial;
    private final String cnpj;

    public Fornecedor(int id, String nomeSocial, String cnpj) {
        this.id = id;
        this.nomeSocial = nomeSocial;
        this.cnpj = cnpj;
    }

    public String getNomeSocial() { return nomeSocial; }

    @Override
    public String toString() {
        return "Model.Fornecedor: " + nomeSocial + " (CNPJ: " + cnpj + ")";
    }
}