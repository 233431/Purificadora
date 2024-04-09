import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vendedor {
    private String nombre;
    private RegistroVentas registroVentas;
    private Inventario inventario;

    public Vendedor(String nombre, RegistroVentas registroVentas, Inventario inventario) {
        this.nombre = nombre;
        this.registroVentas = registroVentas;
        this.inventario = inventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void surtirProductos(Scanner scanner) {
        System.out.println("Seleccione el producto que desea surtir:");
        for (int i = 0; i < inventario.getProductos().size(); i++) {
            Producto producto = inventario.getProductos().get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio() + " - En existencia: " + producto.getCantidadDisponible());
        }

        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        if (opcion < 1 || opcion > inventario.getProductos().size()) {
            System.out.println("Opción inválida.");
            return;
        }

        Producto productoSeleccionado = inventario.getProductos().get(opcion - 1);

        System.out.print("Ingrese la cantidad a agregar al inventario: ");
        int cantidadAgregar = scanner.nextInt();
        productoSeleccionado.setCantidadDisponible(productoSeleccionado.getCantidadDisponible() + cantidadAgregar);

        System.out.println("El inventario de " + productoSeleccionado.getNombre() + " se ha actualizado correctamente.");
    }

    public void realizarVenta(Scanner scanner) {
        List<Producto> productosVenta = new ArrayList<>();
        double totalVenta = 0;

        mostrarInventario();

        System.out.println("Por favor, seleccione los productos que el cliente desea comprar (Ingrese '0' para terminar):");
        for (int i = 0; i < inventario.getProductos().size(); i++) {
            Producto producto = inventario.getProductos().get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio() + " - En existencia: " + producto.getCantidadDisponible());
        }

        while (true) {
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            if (opcion == 0) {
                break;
            } else if (opcion < 1 || opcion > inventario.getProductos().size()) {
                System.out.println("Opción inválida. Por favor, seleccione nuevamente.");
            } else {
                Producto productoSeleccionado = inventario.getProductos().get(opcion - 1);
                System.out.print("Ingrese la cantidad de '" + productoSeleccionado.getNombre() + "' que desea comprar: ");
                int cantidadComprar = scanner.nextInt();
                if (cantidadComprar > productoSeleccionado.getCantidadDisponible()) {
                    System.out.println("No hay suficientes existencias de '" + productoSeleccionado.getNombre() + "'.");
                    continue;
                }
                productosVenta.add(productoSeleccionado);
                totalVenta += productoSeleccionado.getPrecio() * cantidadComprar;
                productoSeleccionado.setCantidadDisponible(productoSeleccionado.getCantidadDisponible() - cantidadComprar);
            }
        }

        Venta venta = new Venta(productosVenta, LocalDate.now());
        registroVentas.agregarVenta(venta);

        System.out.println("Venta realizada por un total de: $" + totalVenta);
    }

    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (int i = 0; i < inventario.getProductos().size(); i++) {
            Producto producto = inventario.getProductos().get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio() + " - En existencia: " + producto.getCantidadDisponible());
        }
        System.out.println("Seleccione una opción:");
        System.out.println("1. Realizar venta");
        System.out.println("2. Surtir productos");
        System.out.println("3. Ver corte de caja");
        System.out.println("4. Salir");
    }
}

