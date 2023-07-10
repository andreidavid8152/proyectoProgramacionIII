import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestionFinanciera {
    private Map<String, CategoriaIngreso> categoriasIngreso;
    private Map<String, CategoriaGasto> categoriasGasto;
    private HashMap<Integer, PagoRecurrente> pagosRecurrentes;
    private HashMap<String, CategoriaPrestamo> categoriasPrestamos;
    private HashMap<String, CategoriaDeuda> categoriasDeudas;

    private int numPagos;

    private double presupuestoTotal;
    public static double saldo;

    public GestionFinanciera() {
        numPagos = 1;
        this.pagosRecurrentes = new HashMap<>();
        this.categoriasIngreso = new HashMap<>();
        this.categoriasGasto = new HashMap<>();
        this.categoriasPrestamos = new HashMap<>();
        this.categoriasDeudas = new HashMap<>();
        this.presupuestoTotal = 0;
        saldo = 500;
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
        this.categoriasIngreso.put(categoria, new CategoriaIngreso(categoria));
        return true;
    }

    public boolean crearCategoriaGasto(String categoria, double presupuesto) {
        if (!validarCategoria(categoria)) {
            return false;
        }
        this.categoriasGasto.put(categoria, new CategoriaGasto(categoria, presupuesto));
        return true;
    }

    public boolean crearCategoriaPrestamo(String categoria){
        if(!validarCategoria(categoria)){
            return false;
        }
        this.categoriasPrestamos.put(categoria, new CategoriaPrestamo(categoria));
        return true;
    }

    public boolean crearCategoriaDeuda(String categoria){
        if (!validarCategoria(categoria)) {
            return false;
        }
        this.categoriasDeudas.put(categoria, new CategoriaDeuda(categoria));
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
            } else if(this.categoriasPrestamos.containsKey(viejoNombre)){
                CategoriaPrestamo cat = this.categoriasPrestamos.get(viejoNombre);
                this.categoriasPrestamos.remove(viejoNombre);
                this.categoriasPrestamos.put(nuevoNombre, cat);
                return true;
            }else if(this.categoriasDeudas.containsKey(viejoNombre)){
                CategoriaDeuda cat = this.categoriasDeudas.get(viejoNombre);
                this.categoriasDeudas.remove(viejoNombre);
                this.categoriasDeudas.put(nuevoNombre, cat);
                return true;
            }else {
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
        }  else if (this.categoriasPrestamos.containsKey(categoria)) {
            CategoriaPrestamo cat = this.categoriasPrestamos.get(categoria);
            // Verificar si hay presupuesto asignado y transacciones
            if (!cat.getPagosRecurrentes().isEmpty()) {
                System.out.println("La categoría de prestamos '" + categoria + "' tiene transacciones asignadas. No se puede eliminar.");
                return 1;
            } else {
                this.categoriasPrestamos.remove(categoria);
                System.out.println("Categoría de prestamos '" + categoria + "' eliminada con éxito.");
                return 0;
            }
        } else if (this.categoriasDeudas.containsKey(categoria)) {
            CategoriaDeuda cat = this.categoriasDeudas.get(categoria);
            // Verificar si hay presupuesto asignado y transacciones
            if (!cat.getPagosRecurrentes().isEmpty()) {
                System.out.println("La categoría de deuda '" + categoria + "' tiene transacciones asignadas. No se puede eliminar.");
                return 1;
            } else {
                this.categoriasDeudas.remove(categoria);
                System.out.println("Categoría de deuda '" + categoria + "' eliminada con éxito.");
                return 0;
            }
        }else {
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
        if (this.categoriasIngreso.containsKey(categoria) || this.categoriasGasto.containsKey(categoria) || this.categoriasDeudas.containsKey(categoria) || this.categoriasPrestamos.containsKey(categoria)) {
            System.out.println("La categoría ya existe.");
            return false;
        }
        return true;
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
            if (realizarPagos(monto, nuevoPago.getMoneda())) {
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

    public int registrarDeudaOPrestamo(double monto, String moneda, String frecuencia, LocalDate fechaInicio, boolean pagadoCompletamente, String descripcion, String categoria){

        double aumento = 1;
        if(moneda.equals("GBP")){
            aumento *= 1.33;
        }else if(moneda.equals("EUR")){
            aumento *= 1.18;
        }

        Finanzas cat;
        if(categoriasDeudas.containsKey(categoria)){
            cat = categoriasDeudas.get(categoria);
        }else{
            cat = categoriasPrestamos.get(categoria);
            if(fechaInicio.equals(app.dia)){
                System.out.println("hecho");
                saldo += monto * aumento;
            }
        }

        return cat.registrarPagoRecurrente(monto, moneda, frecuencia, fechaInicio, pagadoCompletamente, descripcion);
    }

    public int actualizarDeudaOPrestamo(int id, double monto, String moneda, String frecuencia, LocalDate fechaInicio, String descripcion, String categoria){

        double aumento = 1;
        if(moneda.equals("GBP")){
            aumento *= 1.33;
        }else if(moneda.equals("EUR")){
            aumento *= 1.18;
        }

        Finanzas cat;
        if(categoriasDeudas.containsKey(categoria)){
            cat = categoriasDeudas.get(categoria);
        }else{
            cat = categoriasPrestamos.get(categoria);
            if(fechaInicio.equals(app.dia)){
                System.out.println("hecho");
                saldo += monto * aumento;
            }
        }
        System.out.println("Saldo: " + saldo);
        System.out.println("Monto: " + monto);

        return cat.actualizarPagoRecurrente(id, monto, moneda, frecuencia, fechaInicio, descripcion);

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

    public int buscarPrestamoODeuda(int id, String categoria){

        int resp = -3; //Pago no encontrado
        if(categoriasDeudas.containsKey(categoria) && categoriasDeudas.get(categoria).getPagosRecurrentes().containsKey(id)){
            resp = categoriasDeudas.get(categoria).buscarPagoRecurrente(id);
        }else if(categoriasPrestamos.containsKey(categoria) && categoriasPrestamos.get(categoria).getPagosRecurrentes().containsKey(id)){
            resp = categoriasPrestamos.get(categoria).buscarPagoRecurrente(id);
        }

        return resp;
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
                if (realizarPagos(montoAPagar, pago.getPagoRecurrente().getMoneda())) {
                    pago.setPagado(true);
                    mensaje.append("El PagoRecurrente con ID ").append(pago.getPagoRecurrente().getId())
                            .append(" para el mes ").append(pago.getMes() + 1).append(" ha sido pagado.\n");
                }
                // Verificar si todos los pagos han sido realizados
                if (pago.getPagoRecurrente().getPagados().stream().allMatch(p -> p == true)) {
                    pago.getPagoRecurrente().setPagadoCompletamente(true);
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


    public String verificarPagosPendientesDeudasPrestamos(){
        String mensaje = "";
        for (CategoriaDeuda cat : categoriasDeudas.values()) {
            mensaje += cat.verificarPagosPendientes();
        }

        for (CategoriaPrestamo cat : categoriasPrestamos.values()) {
            mensaje += cat.verificarPagosPendientes();
        }

        return mensaje;
    }

    public boolean realizarPagos(double monto, String moneda){

        if (moneda.equals("EUR")) {
            monto = monto * 1.18;  // monto en dólares
        } else if (moneda.equals("GBP")) {
            monto = monto * 1.33;  // monto en dólares
        }

        if(monto > saldo){
            return false;
        }

        saldo -= monto;

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

            if(pago.getFechaInicio().equals(app.dia) && realizarPagos(pago.getMonto(), pago.getMoneda())){
                pago.getPagados().set(0, true);
                sePago = 1;
            }
        }

        return sePago;
    }

    public void eliminarPagoRecurrente(int id){
        pagosRecurrentes.remove(id);
    }

    public void eliminarDeudaPrestamo(int id, String categoria){
        if(categoriasDeudas.containsKey(categoria)){
            categoriasDeudas.get(categoria).getPagosRecurrentes().remove(id);
        }else{
            categoriasPrestamos.get(categoria).getPagosRecurrentes().remove(id);
        }
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

        if(tipo.equals("Prestamo") || tipo.equals("Ambas")){
            for(String categoria : this.categoriasPrestamos.keySet()){
                text += categoria + "\n";
            }
        }

        if(tipo.equals("Deuda") || tipo.equals("Ambas")){
            for(String categoria : this.categoriasDeudas.keySet()){
                text += categoria + "\n";
            }
        }

        return text;
    }

    public String mostrarDeudasPrestamos(String categoria, Boolean bool){

        String mensaje = "";
        HashMap<Integer, PagoRecurrente> pagosFiltrados = new HashMap<>();
        Finanzas pago;

        if(categoriasDeudas.containsKey(categoria)){
            pago = categoriasDeudas.get(categoria);
        }else{
            pago = categoriasPrestamos.get(categoria);
        }

        for (Map.Entry<Integer, PagoRecurrente> entry : pago.getPagosRecurrentes().entrySet()) {
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

        for (PagoRecurrente pagoRecurrente : pagosFiltrados.values()) {
            mensaje += pagoRecurrente.toString() + '\n';
        }

        return mensaje;
    }

    public boolean esDeuda(String cat){
        if(categoriasDeudas.containsKey(cat)){
            return true;
        }
        return false;
    }

    public String mostrarCategoriasOrdenadasAZ(String tipo) {
        List<String> categoriasOrdenadas = new ArrayList<>();

        if (tipo.equals("Ingreso") || tipo.equals("Ambas")) {
            categoriasOrdenadas.addAll(this.categoriasIngreso.keySet());
        }

        if (tipo.equals("Gasto") || tipo.equals("Ambas")) {
            categoriasOrdenadas.addAll(this.categoriasGasto.keySet());
        }

        if(tipo.equals("Prestamo") || tipo.equals("Ambas")){
            categoriasOrdenadas.addAll(this.categoriasPrestamos.keySet());
        }

        if(tipo.equals("Deuda") || tipo.equals("Ambas")){
            categoriasOrdenadas.addAll(this.categoriasDeudas.keySet());
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

        if(tipo.equals("Prestamo") || tipo.equals("Ambas")){
            categoriasOrdenadas.addAll(this.categoriasPrestamos.keySet());
        }

        if(tipo.equals("Deuda") || tipo.equals("Ambas")){
            categoriasOrdenadas.addAll(this.categoriasDeudas.keySet());
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

    public HashMap<String, CategoriaPrestamo> getCategoriasPrestamos() {
        return categoriasPrestamos;
    }
    public HashMap<String, CategoriaDeuda> getCategoriasDeudas() {
        return categoriasDeudas;
    }
}
