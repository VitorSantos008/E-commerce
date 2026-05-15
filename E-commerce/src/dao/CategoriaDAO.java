package dao;

import model.Categoria;
import dao.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void inserir(Categoria categoria) {

        String sql = "INSERT INTO categoria (id, nome) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoria.getId());
            stmt.setString(2, categoria.getNome());

            stmt.executeUpdate();
            System.out.println("✅ Categoria cadastrada!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
        }
    }

    public List<Categoria> listar() {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT * FROM categoria";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Categoria categoria = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome")
                );

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }

        return categorias;
    }

    public void atualizar(Categoria categoria) {

        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());

            stmt.executeUpdate();
            System.out.println("✅ Categoria atualizada!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void remover(int id) {

        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("🗑 Categoria removida!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
        }
    }
}