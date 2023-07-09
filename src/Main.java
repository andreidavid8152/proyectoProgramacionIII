public class Main {
    public static void main(String[] args) {
        SistemaLogin sistema = new SistemaLogin();
        // Registro del primer usuario administrador
        sistema.registerUser("", "", "", true);
        new LoginWindow(sistema, null);
    }
}
