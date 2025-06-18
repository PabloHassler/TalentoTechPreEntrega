package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;
    private int id;
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido(List<LineaPedido> lineas) {
        this.id = contador++;
        this.lineas = lineas;
    }

    public Pedido(LineaPedido linea) {
        this.id = contador++;
        lineas.add(linea);
    }

    public void agregarLinea(LineaPedido lp) {
        lineas.add(lp);
    }

    public double getTotal() {
        double total = 0;
        for (LineaPedido lp : lineas) {
            total += lp.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - Total: $" + getTotal();
    }

}