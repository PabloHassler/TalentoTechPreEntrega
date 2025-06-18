package com.techlab;

import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
import com.techlab.services.PedidoService;
import com.techlab.services.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductoService prodService = new ProductoService();
        PedidoService pedidoService = new PedidoService();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n===== SISTEMA DE GESTIÓN - TECHLAB =====");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(sc.nextLine());
                    prodService.agregarProducto(nombre, precio, stock);
                    break;
                }
                case 2: {
                    List<Producto> productos = prodService.listarProductos();
                    for (Producto p : productos) {
                        System.out.println(p);
                    }
                    break;
                }
                case 3: {
                    System.out.print("ID de producto: ");
                    int id = Integer.parseInt(sc.nextLine());
                    try {
                        System.out.println(prodService.buscarPorId(id));
                    } catch (ProductoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.print("ID a eliminar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    try {
                        prodService.eliminarProducto(id);
                        System.out.println("Eliminado.");
                    } catch (ProductoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    List<LineaPedido> lineas = new ArrayList<>();
                    while (true) {
                        System.out.print("ID producto (0 para terminar): ");
                        int id = Integer.parseInt(sc.nextLine());
                        if (id == 0) break;
                        try {
                            Producto prod = prodService.buscarPorId(id);
                            System.out.print("Cantidad: ");
                            int cant = Integer.parseInt(sc.nextLine());
                            lineas.add(new LineaPedido(prod, cant));
                        } catch (ProductoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    try {
                        Pedido pedido = pedidoService.crearPedido(lineas, prodService.listarProductos());
                        System.out.println("Pedido creado: " + pedido);
                    } catch (StockInsuficienteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: {
                    List<Pedido> pedidos = pedidoService.listarPedidos();
                    for (Pedido p : pedidos) {
                        System.out.println(p.toString());
                    }
                    break;
                }
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

        sc.close();
    }
}