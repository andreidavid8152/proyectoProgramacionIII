public class Main {
    public static void main(String[] args) {
        SistemaLogin sistema = new SistemaLogin();
        sistema.registerUser( "admin", "admin");
        new LoginWindow(sistema);
    }
}
