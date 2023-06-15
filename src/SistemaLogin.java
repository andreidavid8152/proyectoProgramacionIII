import java.util.HashMap;
import java.util.Map;

public class SistemaLogin {
    private Map<String, Usuario> users;

    public SistemaLogin() {
        this.users = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        this.users.put(username, new Usuario(username, password));
    }

    public boolean login(String username, String password) {
        Usuario user = this.users.get(username);
        return user != null && user.checkPassword(password);
    }
}
