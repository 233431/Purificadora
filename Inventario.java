import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // Método para mostrar los productos del inventario
    public void mostrarProductos() {
        System.out.println("Inventario:");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio() +
                               " - En existencia: " + producto.getCantidadDisponible());
        }
    }

    // Método para obtener un producto por su nombre
    public Producto getProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null; // Devuelve null si no se encuentra el producto con el nombre especificado
    }
}
