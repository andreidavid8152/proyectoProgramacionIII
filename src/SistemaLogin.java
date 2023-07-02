import java.util.*;

public class SistemaLogin {
    private Map<String, Usuario> users;

    public SistemaLogin() {
        this.users = new HashMap<>();
    }

    public boolean registerUser(String adminUsername, String username, String password, boolean isAdmin) {
        if (!users.isEmpty()) { // Si ya hay usuarios registrados, se requiere ser admin para agregar más
            Usuario admin = this.users.get(adminUsername);
            if (admin == null || !admin.isAdmin()) {
                throw new SecurityException("Operación no permitida. Solo los administradores pueden registrar usuarios.");
            }
        }

        if (users.containsKey(username)) {
            return false;
        }
        this.users.put(username, new Usuario(username, password, isAdmin));
        return true;
    }


    public boolean login(String username, String password) {
        Usuario user = this.users.get(username);
        return user != null && user.checkPassword(password);
    }

    public boolean eliminarUsuario(String username) {
        if (!users.containsKey(username)) {
            return false;
        }
        this.users.remove(username);
        return true;
    }


    public boolean actualizarUsuario(String oldUsername, String newUsername, String newPassword) {

        if (users.containsKey(newUsername) && !oldUsername.equals(newUsername)) {
            return false;
        }

        Usuario user = this.users.remove(oldUsername);
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        this.users.put(newUsername, user);
        return true;
    }

    public String mostrarUsuarios() {
        if (users.isEmpty()) {
            return "No hay usuarios registrados en el sistema.";
        }

        StringBuilder result = new StringBuilder();
        result.append("Usuarios registrados:\n");

        for (Usuario user : users.values()) {
            result.append("Nombre de usuario: ").append(user.getUsername()).append("\n");
            result.append("Es administrador: ").append(user.isAdmin() ? "Sí" : "No").append("\n");
            result.append("---------------------------\n");
        }

        return result.toString();
    }


    public Map<String, Usuario> getUsers() {
        return users;
    }
}
