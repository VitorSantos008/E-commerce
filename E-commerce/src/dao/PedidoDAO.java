package dao;

import dao.ConexaoBD;
import model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDAO {

    public void inserir(Pedido pedido, int clienteId) {
        String sql = "INSERT INTO pedido (id, cliente_id, status, valor_total) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getId());
            stmt.setInt(2, clienteId);
            stmt.setString(3, pedido.getStatus());
            stmt.setDouble(4, pedido.calcularValorFinal());

            stmt.executeUpdate();

            System.out.println("Pedido salvo no banco.");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar pedido: " + e.getMessage());
        }
    }
}