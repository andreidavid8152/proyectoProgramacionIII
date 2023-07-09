import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Categoria {
    protected String nombre;
    protected List<Transaccion> transacciones;
    protected double impuestos;
    protected int nextId = 1;  // Contador para IDs autoincrementales

    public Categoria(String nombre) {
        impuestos = 0;
        this.nombre = nombre;
        this.transacciones = new ArrayList<>();
    }

    public boolean agregarTransaccion(Transaccion transaccion) {
        Transaccion t = new Transaccion(nextId++, transaccion.getMonto(), transaccion.getFecha(), transaccion.getCategoria(), transaccion.getDescripcion(), transaccion.getImpuesto(), transaccion.getTasaImpuesto());
        this.transacciones.add(t);
        this.impuestos += transaccion.getImpuesto();
        return true;  // La transacción se agrega exitosamente por defecto
    }

    public Transaccion buscarTransaccion(int id) {
        return transacciones.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public double getImpuestos() {
        return impuestos;
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

    public String getNombre() {
        return nombre;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

}
