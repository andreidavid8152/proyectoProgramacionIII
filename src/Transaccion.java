import java.util.Date;

public class Transaccion {
    private int id;  // ID de la transaccion
    private double monto;
    private double tasaImpuesto;
    private Date fecha;
    private String categoria;
    private String descripcion;
    private double impuesto;

    public Transaccion(int id, double monto, Date fecha, String categoria, String descripcion, double impuesto, double tasaImpuesto) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.impuesto = impuesto;
        this.tasaImpuesto = tasaImpuesto;
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

    public double getImpuesto() {
        return impuesto;
    }

    public double getTasaImpuesto() {
        return tasaImpuesto;
    }

    public void setTasaImpuesto(double tasaImpuesto) {
        this.tasaImpuesto = tasaImpuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
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
                ", impuesto =" + impuesto +
                ", tasa impuesto =" + tasaImpuesto +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
