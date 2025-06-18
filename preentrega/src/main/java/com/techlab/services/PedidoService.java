package com.techlab.services;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.List;


public class PedidoService {
    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido crearPedido(List<LineaPedido> lineas, List<Producto> productos) throws StockInsuficienteException {
        // Validación de stock
        for (LineaPedido linea : lineas) {
            Producto producto = linea.getProducto();
            int cantidad = linea.getCantidad();

            for (Producto p : productos) {
                if (p.getId() == producto.getId()) {
                    if (p.getStock() < cantidad) {
                        throw new StockInsuficienteException("Stock insuficiente para el producto: " + p.getNombre());
                    }
                }
            }
        }

        for (LineaPedido linea : lineas) {
            Producto producto = linea.getProducto();
            int cantidad = linea.getCantidad();

            for (Producto p : productos) {
                if (p.getId() == producto.getId()) {
                    p.setStock(p.getStock() - cantidad);
                }
            }
        }

        Pedido pedido = new Pedido(lineas);
        pedidos.add(pedido);

        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return pedidos;
    }
}