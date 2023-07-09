import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Finanzas {
    private String nombre;
    private int numPagos;
    private HashMap<Integer, PagoRecurrente> pagosRecurrentes;

    public Finanzas(String nombre){
        this.pagosRecurrentes = new HashMap<>();
        this.nombre = nombre;
        numPagos = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int registrarPagoRecurrente(double monto, String moneda, String frecuencia, LocalDate fechaInicio, boolean pagadoCompletamente, String descripcion) {
        int sePago = 0;
        // Crear un nuevo PagoRecurrente
        PagoRecurrente nuevoPago = new PagoRecurrente(monto, moneda, frecuencia, fechaInicio, descripcion, numPagos);

        numPagos++;

        // Llenar el ArrayList con las fechas de pago
        int frecuenciaInt = Integer.parseInt(frecuencia);
        for (int i = 0; i < frecuenciaInt; i++) {
            // Agregar i meses a la fecha de inicio y guardarla en el ArrayList
            nuevoPago.getFechasPago().add(fechaInicio.plusMonths(i).toString());
        }

        if(pagadoCompletamente){
            nuevoPago.setSoloRegistro(true);
            Collections.fill(nuevoPago.getPagados(), true);
            sePago = -1;
        } else if (fechaInicio.equals(app.dia)) { // Verificar si la fecha de inicio es igual a la fecha actual
            if (realizarPagos(monto/Integer.parseInt(frecuencia), nuevoPago.getMoneda())) {
                nuevoPago.getPagados().set(0, true);
                System.out.println("El pago con ID " + nuevoPago.getId() + " para el mes 1 ha sido pagado.");
                sePago = 1;
            } else {
                System.out.println("No se puede pagar el pago con ID " + nuevoPago.getId() + " para el mes 1.");
            }
        } else{
            sePago = -1;
        }

        // Agregar el nuevo pago al HashMap de pagosRecurrentes
        this.pagosRecurrentes.put(nuevoPago.getId(), nuevoPago);
        return sePago;
    }



    public int buscarPagoRecurrente(int id){
        // Buscar el PagoRecurrente en el HashMap usando el id
        PagoRecurrente pago = pagosRecurrentes.get(id);

        // Devolver el PagoRecurrente encontrado, o null si no se encontró ninguno

        if(pago == null){
            return 0; //No se encontro
        } else if(pago.isSoloRegistro() || noEstaPagandose(pago.getPagados())){
            System.out.println(pago.isSoloRegistro() + "||" + noEstaPagandose(pago.getPagados()));
            return 1; //Dejar editar todo
        } else{
            return -1; //No dejar editar
        }
    }

    public boolean noEstaPagandose(ArrayList<Boolean> pagados) {
        for (boolean pagado : pagados) {
            if (pagado) {
                return false; // Se encontró un valor verdadero, por lo tanto, no contiene solo false
            }
        }
        return true; // No se encontró ningún valor verdadero, contiene solo false
    }

    public HashMap<Integer, PagoRecurrente> mostrarPagosCategorizados(boolean bool) {
        HashMap<Integer, PagoRecurrente> pagosFiltrados = new HashMap<>();
        for (Map.Entry<Integer, PagoRecurrente> entry : pagosRecurrentes.entrySet()) {
            // Si el pago recurrente coincide con el estado de pago especificado
            if (bool) {
                if (entry.getValue().isPagadoCompletamente() || entry.getValue().isSoloRegistro()) {
                    pagosFiltrados.put(entry.getKey(), entry.getValue());
                }
            } else {
                if (!entry.getValue().isPagadoCompletamente() && !entry.getValue().isSoloRegistro()) {
                    pagosFiltrados.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return pagosFiltrados;
    }



    public String verificarPagosPendientes() {
        StringBuilder mensaje = new StringBuilder();

        // Crear una lista de pagos
        List<Pago> pagosPendientes = new ArrayList<>();
        for (PagoRecurrente pagoRecurrente : pagosRecurrentes.values()) {
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
                    mensaje.append("La deuda de la categoria " + nombre + " con ID ").append(pago.getPagoRecurrente().getId())
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
            mensaje.append("La deuda de la categoria " + nombre + " con ID ").append(entry.getKey()).append(" para los meses ");
            List<String> meses = entry.getValue().stream()
                    .map(pago -> Integer.toString(pago.getMes() + 1))
                    .collect(Collectors.toList());
            mensaje.append(String.join(",", meses));
            mensaje.append(" no ha sido pagado.\n");
        }


        return mensaje.toString();
    }



    public boolean realizarPagos(double monto, String moneda){

        if (moneda.equals("EUR")) {
            monto = monto * 1.18;  // monto en dólares
        } else if (moneda.equals("GBP")) {
            monto = monto * 1.33;  // monto en dólares
        }

        if(monto > GestionFinanciera.saldo){
            return false;
        }

        GestionFinanciera.saldo -= monto;

        return true;
    }

    public int actualizarPagoRecurrente(int id, double monto, String moneda, String frecuencia, LocalDate fechaInicio, String descripcion){
        int sePago = 0;

        PagoRecurrente pago = pagosRecurrentes.get(id);

        pago.setMonto(monto);
        pago.setMoneda(moneda);
        pago.setFrecuencia(frecuencia);
        pago.setFechaInicio(fechaInicio);
        pago.setDescripcion(descripcion);

        //nuevos meses
        pago.getFechasPago().clear();
        for (int i = 0; i < Integer.parseInt(frecuencia); i++) {
            // Agregar i meses a la fecha de inicio y guardarla en el ArrayList
            pago.getFechasPago().add(fechaInicio.plusMonths(i).toString());
        }

        if(pago.isSoloRegistro()){
            pago.setPagados(new ArrayList<>(Collections.nCopies(Integer.parseInt(frecuencia), true)));
            sePago = -1;
        }else{
            pago.setPagados(new ArrayList<>(Collections.nCopies(Integer.parseInt(frecuencia), false)));
            sePago = -1;

            if(pago.getFechaInicio().equals(app.dia) && realizarPagos(pago.getMonto()/Integer.parseInt(pago.getFrecuencia()), pago.getMoneda())){
                pago.getPagados().set(0, true);
                sePago = 1;
            }
        }

        return sePago;
    }

    public void eliminarPagoRecurrente(int id){
        pagosRecurrentes.remove(id);
    }

    public void mostrarPagoRecurrente(){
        // Imprimir cada entrada en pagosRecurrentes
        for (Map.Entry<Integer, PagoRecurrente> entry : pagosRecurrentes.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }


    public HashMap<Integer, PagoRecurrente> getPagosRecurrentes() {
        return pagosRecurrentes;
    }
}
