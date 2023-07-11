public class Main {
    public static void main(String[] args) {
        SistemaLogin sistema = new SistemaLogin();
        // Registro del primer usuario administrador
        sistema.registerUser("", "admin", "1234", true);
        new LoginWindow(sistema, null);
    }
}
