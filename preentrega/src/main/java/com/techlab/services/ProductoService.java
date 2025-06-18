package com.techlab.services;

import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(String nombre, double precio, int stock) {
        productos.add(new Producto(nombre, precio, stock));
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarPorId(int id) throws ProductoNoEncontradoException {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado.");
    }

    public void eliminarProducto(int id) throws ProductoNoEncontradoException {
        Producto producto = buscarPorId(id);
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.remove(i);
                return;
            }
        }
        throw new ProductoNoEncontradoException("No se pudo eliminar el producto con ID " + id);
    }
}