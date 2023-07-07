import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PagoRecurrente {
    private static int ultimoId = 0;
    private int id;
    private double monto;
    private String moneda;
    private String frecuencia; //tiempo del pago recurrente
    private LocalDate fechaInicio;
    private String descripcion;
    private boolean pagadoCompletamente; //sirve para saber si un pago se lo ha realizado completamente
    private boolean soloRegistro; //sirve para saber si un pago es un pago solo de registro
    private int mesActual; //sirve para saber en que mes estamos
    private ArrayList<Boolean> pagados; //arreglo de meses pagados siempre empieza en false
    private ArrayList<String> fechasPago; //fechas de pago proxximas a la fecha de inicio siempre la primera fecha de pago es el sigueitne mes de la fecha de inicio

    public PagoRecurrente(double monto, String moneda, String frecuencia, LocalDate fechaInicio, String descripcion){
        this.pagados = new ArrayList<>(Collections.nCopies(Integer.parseInt(frecuencia), false));
        this.fechasPago = new ArrayList<>(Integer.parseInt(frecuencia));
        this.id = ++ultimoId;
        this.monto = monto;
        this.moneda = moneda;
        this.frecuencia = frecuencia;
        this.fechaInicio = fechaInicio;
        mesActual = 0;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isPagadoCompletamente() {
        return pagadoCompletamente;
    }

    public int getMesActual() {
        return mesActual;
    }

    public void setMesActual(int mesActual) {
        this.mesActual = mesActual;
    }

    public ArrayList<String> getFechasPago() {
        return fechasPago;
    }

    public ArrayList<Boolean> getPagados() {
        return pagados;
    }

    public boolean isSoloRegistro() {
        return soloRegistro;
    }

    public void setSoloRegistro(boolean soloRegistro) {
        this.soloRegistro = soloRegistro;
    }

    public void setPagados(ArrayList<Boolean> pagados) {
        this.pagados = pagados;
    }

    public void setPagadoCompletamente(boolean pagadoCompletamente) {
        this.pagadoCompletamente = pagadoCompletamente;
    }

    @Override
    public String toString() {
        return "PagoRecurrente{" +
                "id=" + id +
                ", monto=" + monto +
                ", moneda='" + moneda + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", descripcion='" + descripcion + '\'' +
                ", pagadoCompletamente=" + pagadoCompletamente +
                ", soloRegistro=" + soloRegistro +
                ", mesActual='" + mesActual + '\'' +
                ", pagados=" + pagados +
                ", fechasPago=" + fechasPago +
                '}';
    }

}
