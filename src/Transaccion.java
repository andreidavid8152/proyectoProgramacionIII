import java.util.Date;

public class Transaccion {
    private int id;  // ID de la transaccion
    private double monto;
    private Date fecha;
    private String categoria;
    private String descripcion;

    public Transaccion(int id, double monto, Date fecha, String categoria, String descripcion) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
