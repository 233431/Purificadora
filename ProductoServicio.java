public class ProductoServicio extends Producto {
    public ProductoServicio(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public void setCantidadDisponible(int cantidadDisponible) {
        System.out.println("Error: No se puede establecer la cantidad disponible para un producto de servicio.");
    }
}
