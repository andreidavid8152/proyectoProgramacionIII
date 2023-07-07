import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class GestionFinanciera {
    private Map<String, CategoriaIngreso> categoriasIngreso;
    private Map<String, CategoriaGasto> categoriasGasto;
    private HashMap<Integer, PagoRecurrente> pagosRecurrentes;
    private double presupuestoTotal;
    private double saldo;

    public GestionFinanciera() {
        this.pagosRecurrentes = new HashMap<>();
        this.categoriasIngreso = new HashMap<>();
        this.categoriasGasto = new HashMap<>();
        this.presupuestoTotal = 0;
        this.saldo = 500;
    }

    public boolean agregarIngreso(double monto, Date fecha, String descripcion, String categoria, double tasaImpuesto) {
        CategoriaIngreso cat = this.categoriasIngreso.get(categoria);
        Double impuesto = monto*(tasaImpuesto/100);

        saldo += monto;

        if (cat == null) {
            System.out.println("La categoría de ingreso no existe.");
            return false;
        }

        Transaccion transaccion = new Transaccion(cat.getNextId(),monto, fecha, categoria, descripcion, impuesto, tasaImpuesto);
        cat.agregarTransaccion(transaccion);
        cat.setIngresos(cat.getIngresos()+monto);
        return true;
    }


    public int agregarGasto(double monto, Date fecha, String descripcion, String categoria, double tasaImpuesto) {
        CategoriaGasto cat = this.categoriasGasto.get(categoria);
        Double impuesto = monto*(tasaImpuesto/100);
        if (cat == null) {
            System.out.println("La categoría de gasto no existe.");
            return -1;
        }
        Transaccion transaccion = new Transaccion(cat.actnextId(), monto, fecha, categoria, descripcion, impuesto, tasaImpuesto);
        if (!cat.agregarTransaccion(transaccion)) {
            System.out.println(categoria);
            System.out.println("El gasto excede el presupuesto de la categoría.");
            return 0;
        }
        // Actualizar el presupuesto de la categoría.
        cat.setPresupuesto(cat.getPresupuesto() - transaccion.getMonto());
        return 1;
    }

    public boolean crearCategoriaIngreso(String categoria) {
        if (!validarCategoria(categoria)) {
            return false;
        }
        this.categoriasIngreso.put(categoria, new CategoriaIngreso("categoria"));
        return true;
    }

    public boolean crearCategoriaGasto(String categoria, double presupuesto) {
        if (!validarCategoria(categoria)) {
            return false;
        }
        this.categoriasGasto.put(categoria, new CategoriaGasto(categoria, presupuesto));
        return true;
    }

    public boolean editarNombreCategoria(String viejoNombre, String nuevoNombre) {
        // Verificar si el nuevo nombre ya existe en el sistema
        if (validarCategoria(nuevoNombre)) {
            if (this.categoriasIngreso.containsKey(viejoNombre)) {
                CategoriaIngreso cat = this.categoriasIngreso.get(viejoNombre);
                this.categoriasIngreso.remove(viejoNombre);
                this.categoriasIngreso.put(nuevoNombre, cat);
                for (Transaccion transaccion : cat.getTransacciones()) {
                    transaccion.setCategoria(nuevoNombre);
                }
                return true;
            } else if (this.categoriasGasto.containsKey(viejoNombre)) {
                CategoriaGasto cat = this.categoriasGasto.get(viejoNombre);
                this.categoriasGasto.remove(viejoNombre);
                this.categoriasGasto.put(nuevoNombre, cat);
                for (Transaccion transaccion : cat.getTransacciones()) {
                    transaccion.setCategoria(nuevoNombre);
                }
                return true;
            } else {
                System.out.println("La categoría '" + viejoNombre + "' no existe.");
                return false;
            }
        } else {
            System.out.println("El nuevo nombre de categoría '" + nuevoNombre + "' ya existe en el sistema.");
            return false;
        }
    }

    public int eliminarCategoria(String categoria) {
        if (this.categoriasIngreso.containsKey(categoria)) {
            CategoriaIngreso cit = this.categoriasIngreso.get(categoria);
            if (!cit.getTransacciones().isEmpty()) {
                System.out.println("La categoría de gasto '" + categoria + "' tiene transacciones asignadas. No se puede eliminar.");
                return 1;
            } else {
                this.categoriasIngreso.remove(categoria);
                System.out.println("Categoría de gasto '" + categoria + "' eliminada con éxito.");
                return 0;
            }
        } else if (this.categoriasGasto.containsKey(categoria)) {
            CategoriaGasto cat = this.categoriasGasto.get(categoria);
            // Verificar si hay presupuesto asignado y transacciones
            if (!cat.getTransacciones().isEmpty()) {
                System.out.println("La categoría de gasto '" + categoria + "' tiene transacciones asignadas. No se puede eliminar.");
                return 1;
            } else {
                this.presupuestoTotal += cat.getPresupuesto();
                this.categoriasGasto.remove(categoria);
                System.out.println("Categoría de gasto '" + categoria + "' eliminada con éxito.");
                return 0;
            }
        } else {
            System.out.println("La categoría '" + categoria + "' no existe.");
            return -1;
        }
    }

    public boolean asignarPresupuestoGeneral(double presupuesto){
        System.out.println("Saldo" + saldo);
        System.out.println("P: " + presupuestoTotal);
        System.out.println("PA: " + presupuesto);
        if(presupuesto > saldo){
            return false;
        }

        saldo = (saldo-presupuesto)+presupuestoTotal;
        presupuestoTotal = presupuesto;
        // Recorrer el HashMap utilizando un bucle for-each
        for (Map.Entry<String, CategoriaGasto> entry : categoriasGasto.entrySet()) {
            entry.getValue().setPresupuesto(0);
            entry.getValue().setImpuestos(0);
        }

        return true;
    }

    public boolean aumentarPresupuestoGeneral(double presupuesto){

        if(presupuesto > saldo){
            return false;
        }
        presupuestoTotal += presupuesto;
        saldo -= presupuesto;
        return true;
    }

    public boolean asignarPresupuestoACategoriaGasto(String nombreCategoria, double monto) {
        CategoriaGasto cat = this.categoriasGasto.get(nombreCategoria);

        if(presupuestoTotal >= monto || monto <= cat.getPresupuesto()){
            presupuestoTotal = (presupuestoTotal+cat.getPresupuesto())-monto;
            cat.setPresupuesto(monto);
        }else{
            return false;
        }

        return true;
    }

    public boolean aumentarPresupuestoACategoriGasto(String nombre, double monto){
        CategoriaGasto cat = this.categoriasGasto.get(nombre);

        double montoNuevo = cat.getPresupuesto()+monto;

        if(presupuestoTotal < monto){
            return false;
        }

        cat.setPresupuesto(montoNuevo);

        // Restamos la diferencia entre el monto nuevo y el presupuesto actual del presupuesto total
        presupuestoTotal -= monto;
        return true;
    }

    public boolean validarCategoria(String categoria) {
        if (this.categoriasIngreso.containsKey(categoria) || this.categoriasGasto.containsKey(categoria)) {
            System.out.println("La categoría ya existe.");
            return false;
        }
        return true;
    }

    public int registrarPagoRecurrente(double monto, String moneda, String frecuencia, LocalDate fechaInicio, boolean pagadoCompletamente, String descripcion) {
        int sePago = 0;
        // Crear un nuevo PagoRecurrente
        PagoRecurrente nuevoPago = new PagoRecurrente(monto, moneda, frecuencia, fechaInicio, descripcion);

        // Llenar el ArrayList con las fechas de pago
        int frecuenciaInt = Integer.parseInt(frecuencia);
        for (int i = 0; i < frecuenciaInt; i++) {
            // Agregar i meses a la fecha de inicio y guardarla en el ArrayList
            nuevoPago.getFechasPago().add(fechaInicio.plusMonths(i).toString());
        }

        if(pagadoCompletamente){
            nuevoPago.setSoloRegistro(true);
            Collections.fill(nuevoPago.getPagados(), true);
        } else if (fechaInicio.equals(app.dia)) { // Verificar si la fecha de inicio es igual a la fecha actual
            if (realizarPagos(monto)) {
                nuevoPago.getPagados().set(0, true);
                System.out.println("El PagoRecurrente con ID " + nuevoPago.getId() + " para el mes 1 ha sido pagado.");
                sePago = 1;
            } else {
                System.out.println("No se puede pagar el PagoRecurrente con ID " + nuevoPago.getId() + " para el mes 1.");
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

    private boolean noEstaPagandose(ArrayList<Boolean> pagados) {
        for (boolean pagado : pagados) {
            if (pagado) {
                return false; // Se encontró un valor verdadero, por lo tanto, no contiene solo false
            }
        }
        return true; // No se encontró ningún valor verdadero, contiene solo false
    }

    public String verificarPagosPendientes(LocalDate dia) {
        StringBuilder mensaje = new StringBuilder();

        // Crear una lista de pagos
        List<Pago> pagosPendientes = new ArrayList<>();
        for (PagoRecurrente pagoRecurrente : pagosRecurrentes.values()) {
            for (int i = 0; i < Integer.parseInt(pagoRecurrente.getFrecuencia()); i++) {
                if (!pagoRecurrente.getPagados().get(i) && dia.isAfter(LocalDate.parse(pagoRecurrente.getFechasPago().get(i)))) {
                    pagosPendientes.add(new Pago(pagoRecurrente, i));
                }
            }
        }

        // Ordenar los pagos por fecha
        pagosPendientes.sort(Comparator.comparing(Pago::getFecha));

        // Procesar los pagos en orden
        for (Pago pago : pagosPendientes) {
            if (dia.isBefore(pago.getFecha())) {
                continue;
            }

            if (!pago.isPagado()) {
                double montoAPagar = pago.getMonto();
                if (realizarPagos(montoAPagar)) {
                    pago.setPagado(true);
                    mensaje.append("El PagoRecurrente con ID ").append(pago.getPagoRecurrente().getId())
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
            mensaje.append("El PagoRecurrente con ID ").append(entry.getKey()).append(" para los meses ");
            List<String> meses = entry.getValue().stream()
                    .map(pago -> Integer.toString(pago.getMes() + 1))
                    .collect(Collectors.toList());
            mensaje.append(String.join(",", meses));
            mensaje.append(" no ha sido pagado.\n");
        }


        return mensaje.toString();
    }



    public boolean realizarPagos(double monto){

        if(monto > saldo){
            return false;
        }

        saldo -= monto;

        return true;
    }

    public void actualizarPagoRecurrente(int id){
        PagoRecurrente pago = pagosRecurrentes.get(id);
        
    }

    public void mostrarPagoRecurrente(){
        // Imprimir cada entrada en pagosRecurrentes
        for (Map.Entry<Integer, PagoRecurrente> entry : pagosRecurrentes.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

    public boolean existenTransaccionesGastos(){

        for (Map.Entry<String, CategoriaGasto> entry : categoriasGasto.entrySet()) {

            if(!entry.getValue().getTransacciones().isEmpty()){
                return true;
            }

        }
        return false;
    }

    public String mostrarCategorias(String tipo) {
        String text = "";
        if (tipo.equals("Ingreso") || tipo.equals("Ambas")) {
            for (String categoria : this.categoriasIngreso.keySet()) {
                text += categoria + "\n";
            }
        }

        if (tipo.equals("Gasto") || tipo.equals("Ambas")) {
            for (String categoria : this.categoriasGasto.keySet()) {
                text += categoria + "\n";
            }
        }
        return text;
    }

    public String mostrarCategoriasOrdenadasAZ(String tipo) {
        List<String> categoriasOrdenadas = new ArrayList<>();

        if (tipo.equals("Ingreso") || tipo.equals("Ambas")) {
            categoriasOrdenadas.addAll(this.categoriasIngreso.keySet());
        }

        if (tipo.equals("Gasto") || tipo.equals("Ambas")) {
            categoriasOrdenadas.addAll(this.categoriasGasto.keySet());
        }

        bubbleSort(categoriasOrdenadas);

        String text = "";
        for (String categoria : categoriasOrdenadas) {
            text += categoria + "\n";
        }
        return text;
    }

    public String mostrarCategoriasOrdenadasZA(String tipo) {
        List<String> categoriasOrdenadas = new ArrayList<>();

        if (tipo.equals("Ingreso") || tipo.equals("Ambas")) {
            categoriasOrdenadas.addAll(this.categoriasIngreso.keySet());
        }

        if (tipo.equals("Gasto") || tipo.equals("Ambas")) {
            categoriasOrdenadas.addAll(this.categoriasGasto.keySet());
        }

        bubbleSort(categoriasOrdenadas);
        Collections.reverse(categoriasOrdenadas);

        String text = "";
        for (String categoria : categoriasOrdenadas) {
            text += categoria + "\n";
        }
        return text;
    }

    private void bubbleSort(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).compareToIgnoreCase(list.get(j + 1)) > 0) {
                    // intercambia temp y list.get(i)
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }


    private void insertionSort(List<Transaccion> transacciones, Comparator<Transaccion> comparator, boolean ascendente) {
        for (int i = 1; i < transacciones.size(); ++i) {
            Transaccion key = transacciones.get(i);
            int j = i - 1;
            if (ascendente) {
                while (j >= 0 && comparator.compare(transacciones.get(j), key) > 0) {
                    transacciones.set(j + 1, transacciones.get(j));
                    j = j - 1;
                }
            } else {
                while (j >= 0 && comparator.compare(transacciones.get(j), key) < 0) {
                    transacciones.set(j + 1, transacciones.get(j));
                    j = j - 1;
                }
            }
            transacciones.set(j + 1, key);
        }
    }


    public String ordenarTransacciones(String categoria, boolean ascendente, Comparator<Transaccion> comparacion) {
        StringBuilder sb = new StringBuilder();
        CategoriaIngreso catIngreso = this.categoriasIngreso.get(categoria);
        CategoriaGasto catGasto = this.categoriasGasto.get(categoria);

        if (catIngreso != null) {
            List<Transaccion> transacciones = new ArrayList<>(catIngreso.getTransacciones());
            insertionSort(transacciones, comparacion ,ascendente);
            sb.append("Transacciones de la categoría de ingresos '" + categoria + "': \n");
            transacciones.forEach(transaccion -> sb.append("Id: "+ transaccion.getId() + ", Monto: " + transaccion.getMonto() + ", Impuesto: " + transaccion.getImpuesto() +
                     ", Tasa Impuesto: " + transaccion.getTasaImpuesto() + ", Fecha: " + transaccion.getFecha() + ", Descripción: " + transaccion.getDescripcion() + "\n"));
        } else if (catGasto != null) {
            List<Transaccion> transacciones = new ArrayList<>(catGasto.getTransacciones());
            insertionSort(transacciones, comparacion ,ascendente);
            sb.append("Transacciones de la categoría de gastos '" + categoria + "': \n");
            transacciones.forEach(transaccion -> sb.append("Id: "+ transaccion.getId() + ", Monto: " + transaccion.getMonto() + ", Impuesto: " + transaccion.getImpuesto() +
                    ", Tasa Impuesto: " + transaccion.getTasaImpuesto() + ", Fecha: " + transaccion.getFecha() + ", Descripción: " + transaccion.getDescripcion() + "\n"));
        } else {
            sb.append("La categoría '" + categoria + "' no existe.");
        }

        return sb.toString();
    }

    public Map<String, CategoriaIngreso> getCategoriasIngreso() {
        return categoriasIngreso;
    }

    public Map<String, CategoriaGasto> getCategoriasGasto() {
        return categoriasGasto;
    }

    public void setPresupuestoTotal(double presupuestoTotal) {
        this.presupuestoTotal = presupuestoTotal;
    }

    public double getPresupuestoTotal() {
        return presupuestoTotal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public HashMap<Integer, PagoRecurrente> getPagosRecurrentes() {
        return pagosRecurrentes;
    }
}
