import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Categoria {
    protected String nombre;
    protected List<Transaccion> transacciones;
    protected int nextId = 1;  // Contador para IDs autoincrementales

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.transacciones = new ArrayList<>();
    }

    public boolean agregarTransaccion(Transaccion transaccion) {
        Transaccion t = new Transaccion(nextId++, transaccion.getMonto(), transaccion.getFecha(), transaccion.getCategoria(), transaccion.getDescripcion());
        this.transacciones.add(t);
        return true;  // La transacción se agrega exitosamente por defecto
    }

    public boolean eliminarTransaccion(int id) {
        Transaccion transaccion = buscarTransaccion(id);
        if (transaccion != null) {
            transacciones.remove(transaccion);
            return true;  // La transacción se elimina exitosamente
        }
        return false;  // No se encontró la transacción
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public int getNextId() {
        return nextId;
    }

    public int actnextId() {
        return nextId++;
    }

    public Transaccion buscarTransaccion(int id) {
        return transacciones.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public int editarTransaccion(int id, double monto, String descripcion) {
        Transaccion transaccion = buscarTransaccion(id);
        if (transaccion != null) {
            transaccion.setMonto(monto);
            transaccion.setDescripcion(descripcion);
            return 1;
        }
        return -1;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
