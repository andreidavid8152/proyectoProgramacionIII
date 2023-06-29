public class Main {
    public static void main(String[] args) {
        SistemaLogin sistema = new SistemaLogin();
        sistema.registerUser( "", "");
        new LoginWindow(sistema);
    }
}
