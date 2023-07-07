import java.time.LocalDate;

public class Pago {
    private PagoRecurrente pagoRecurrente;
    private int mes;

    public Pago(PagoRecurrente pagoRecurrente, int mes) {
        this.pagoRecurrente = pagoRecurrente;
        this.mes = mes;
    }

    public PagoRecurrente getPagoRecurrente() {
        return pagoRecurrente;
    }

    public int getMes() {
        return mes;
    }

    public LocalDate getFecha() {
        return pagoRecurrente.getFechaInicio().plusMonths(mes);
    }

    public double getMonto() {
        return pagoRecurrente.getMonto();
    }

    public boolean isPagado() {
        return pagoRecurrente.getPagados().get(mes);
    }

    public void setPagado(boolean pagado) {
        pagoRecurrente.getPagados().set(mes, pagado);
    }
}
