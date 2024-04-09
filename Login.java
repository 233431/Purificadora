public class Login {
    private static String usuario;
    private static String contrasena;

    public static void setCredenciales(String nuevoUsuario, String nuevaContrasena) {
        usuario = nuevoUsuario;
        contrasena = nuevaContrasena;
    }

    public static boolean autenticar(String usuarioIngresado, String contrasenaIngresada) {
        return usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena);
    }
}
