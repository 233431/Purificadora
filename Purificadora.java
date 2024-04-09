import java.util.InputMismatchException;
import java.util.Scanner;

public class Purificadora {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que establezca su nombre de usuario y contraseña
        System.out.print("Establezca su nombre de usuario: ");
        String nuevoUsuario = scanner.next();
        System.out.print("Establezca su contraseña: ");
        String nuevaContrasena = scanner.next();

        // Establecer las credenciales utilizando el método setCredenciales de la clase Login
        Login.setCredenciales(nuevoUsuario, nuevaContrasena);

        // Solicitar al usuario que ingrese su nombre de usuario y contraseña
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.next();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.next();

        // Autenticar las credenciales ingresadas utilizando el método autenticar de la clase Login
        if (Login.autenticar(usuario, contrasena)) {
            System.out.println("\n¡Inicio de sesión exitoso!");
            // Iniciar el sistema si las credenciales son válidas
            iniciarSistema(scanner);
        } else {
            System.out.println("\nInicio de sesión fallido. Credenciales incorrectas.");
        }

        scanner.close();
    }

    public static void iniciarSistema(Scanner scanner) {
        // Crear productos y objetos de inventario y registro de ventas
        Producto producto1 = new Producto("Botella de Agua Natural, 600.0ml", 10.0);
        Producto producto2 = new Producto("Garrafones Nuevos", 100.0);
        Producto producto3 = new Producto("Garrafón Rellenado", 10.0);
        Inventario inventario = new Inventario();
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        inventario.agregarProducto(producto3);
        RegistroVentas registroVentas = new RegistroVentas();
        Vendedor vendedor = new Vendedor("Eduardo", registroVentas, inventario);

        int opcion;
        do {
            try {
                // Mostrar el menú principal
                System.out.println("\nBienvenido, " + vendedor.getNombre() + "!");
                System.out.println("1. Realizar venta");
                System.out.println("2. Surtir productos");
                System.out.println("3. Ver corte de caja");
                System.out.println("4. Inventario");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        vendedor.realizarVenta(scanner);
                        break;
                    case 2:
                        vendedor.surtirProductos(scanner);
                        break;
                    case 3:
                        double corteCaja = registroVentas.calcularCorteCaja();
                        System.out.println("Corte de caja: $" + corteCaja);
                        break;
                    case 4:
                        mostrarInventario(inventario);
                        break;
                    case 5:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número válido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
                opcion = 0; // Restablecer la opción
            }
        } while (opcion != 5); // Salir del bucle cuando la opción es 5
    }

    public static void mostrarInventario(Inventario inventario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inventario:");
        inventario.mostrarProductos();
        System.out.println("\nPresione cualquier tecla para volver al menú principal.");
        scanner.nextLine(); // Esperar a que el usuario presione Enter para continuar
    }
}

