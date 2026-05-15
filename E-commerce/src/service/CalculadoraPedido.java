package service;

import model.Pedido;

public class CalculadoraPedido {

    public double calcularSubtotal(Pedido pedido) {
        return pedido.calcularSubtotal();
    }

    public double calcularDesconto(Pedido pedido) {
        double subtotal = pedido.calcularSubtotal();

        if (subtotal > 1000) {
            return subtotal * 0.10;
        }

        if (subtotal > 500) {
            return subtotal * 0.05;
        }

        return 0;
    }

    public double calcularValorFinal(Pedido pedido) {
        return calcularSubtotal(pedido) - calcularDesconto(pedido);
    }
}