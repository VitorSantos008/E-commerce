package model;

import java.util.List;

public class Administrador extends Usuario {

    public Administrador(int id, String nome) {
        super(id, nome);
    }

    @Override
    public void exibirTipo() {
        System.out.println("Model.Administrador");
    }

    public void adicionarProduto(List<Produto> produtos, Produto p) {
        produtos.add(p);
        System.out.println("✅ Model.Produto adicionado pelo administrador!");
    }

    public void removerProduto(List<Produto> produtos, Produto p) {
        produtos.remove(p);
        System.out.println("🗑 Model.Produto removido!");
    }

    public void removerProduto(List<Produto> produtos, int id) {
        produtos.removeIf(prod -> prod.getId() == id);
        System.out.println("🗑 Model.Produto removido por ID!");
    }
}