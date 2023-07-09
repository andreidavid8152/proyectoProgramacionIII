import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoriaPrestamo extends Finanzas{
    public CategoriaPrestamo(String nombre) {
        super(nombre);
    }

    @Override
    public String verificarPagosPendientes() {
        StringBuilder mensaje = new StringBuilder();
        // Crear una lista de pagos
        List<Pago> pagosPendientes = new ArrayList<>();
        for (PagoRecurrente pagoRecurrente : super.getPagosRecurrentes().values()) {

            if(pagoRecurrente.getFechaInicio().equals(app.dia)){
                GestionFinanciera.saldo += pagoRecurrente.getMonto();
            }

            for (int i = 0; i < Integer.parseInt(pagoRecurrente.getFrecuencia()); i++) {
                // Cambio aquí
                if (!pagoRecurrente.getPagados().get(i) && !app.dia.isBefore(LocalDate.parse(pagoRecurrente.getFechasPago().get(i)))) {
                    pagosPendientes.add(new Pago(pagoRecurrente, i));
                }
            }

            // Verificar si todos los pagos han sido realizados
            if (pagoRecurrente.getPagados().stream().allMatch(p -> p == true)) {
                pagoRecurrente.setPagadoCompletamente(true);
            }
        }

        // Ordenar los pagos por fecha
        pagosPendientes.sort(Comparator.comparing(Pago::getFecha));

        // Procesar los pagos en orden
        for (Pago pago : pagosPendientes) {
            if (app.dia.isBefore(pago.getFecha())) {
                continue;
            }

            if (!pago.isPagado()) {
                double montoAPagar = pago.getMonto();
                if (realizarPagos(montoAPagar/Integer.parseInt(pago.getPagoRecurrente().getFrecuencia()), pago.getPagoRecurrente().getMoneda())) {
                    pago.setPagado(true);
                    mensaje.append("El prestamo de la categoria " + super.getNombre() +" con ID ").append(pago.getPagoRecurrente().getId())
                            .append(" para el mes ").append(pago.getMes() + 1).append(" ha sido pagado.\n");
                }
            }
            System.out.println("PAGADOS: " + pago.getPagoRecurrente().getPagados());

        }

        // Agrupar los pagos pendientes por ID de PagoRecurrente
        Map<Integer, List<Pago>> pagosPendientesPorId = pagosPendientes.stream()
                .filter(pago -> !pago.isPagado())
                .collect(Collectors.groupingBy(pago -> pago.getPagoRecurrente().getId()));

        // Construir el mensaje de salida para cada grupo
        for (Map.Entry<Integer, List<Pago>> entry : pagosPendientesPorId.entrySet()) {
            mensaje.append("El prestamo de la categoria " + super.getNombre() +" con ID ").append(entry.getKey()).append(" para los meses ");
            List<String> meses = entry.getValue().stream()
                    .map(pago -> Integer.toString(pago.getMes() + 1))
                    .collect(Collectors.toList());
            mensaje.append(String.join(",", meses));
            mensaje.append(" no ha sido pagado.\n");
        }


        return mensaje.toString();
    }
}
