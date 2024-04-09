import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private List<Producto> productos;
    private double total;
    private LocalDate fecha; 

  
    
   
    public Venta(List<Producto> productosVenta, LocalDate fecha) {
        this.productos = productosVenta;
        this.fecha = fecha;
        calcularTotal();
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public Venta(List<Producto> productos) {
        this.productos = productos;
        calcularTotal();
    }
    public boolean esVentaDeInventario() {
        return productos.isEmpty();
    }

    public void agregarProductoConCantidad(Producto producto, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            productos.add(producto);
        }
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
    }

    public int getTotalProductos() {
        return productos.size();
    }

    public double getTotal() {
        return total;
    }
    public boolean contieneGarrafonRellenado() {
        for (Producto producto : productos) {
            if (producto.getNombre().equals("Garrafón Rellenado")) {
                return true;
            }
        }
        return false;
    }
}
