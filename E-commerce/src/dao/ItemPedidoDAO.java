package dao;

import model.ItemPedido;
import model.Pedido;
import dao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemPedidoDAO {

    public void inserirItens(Pedido pedido) {
        String sql = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (ItemPedido item : pedido.getItens()) {
                stmt.setInt(1, pedido.getId());
                stmt.setInt(2, item.getProduto().getId());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getProduto().getPreco());

                stmt.addBatch();
            }

            stmt.executeBatch();

            System.out.println("✅ Itens do pedido salvos no banco!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar itens do pedido: " + e.getMessage());
        }
    }
}
