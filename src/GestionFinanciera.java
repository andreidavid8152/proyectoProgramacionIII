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
        // Se obtiene la categoría de ingreso con la clave especificada.
        CategoriaIngreso cat = this.categoriasIngreso.get(categoria);

        // Calcula el impuesto correspondiente al monto dado.
        Double impuesto = monto * (tasaImpuesto / 100);

        // Añade el monto al saldo total.
        saldo += monto;

        // Verifica si la categoría existe.
        if (cat == null) {
            // Si no existe, se imprime un mensaje y se retorna false.
            System.out.println("La categoría de ingreso no existe.");
            return false;
        }

        // Si la categoría existe, se crea una nueva transacción.
        Transaccion transaccion = new Transaccion(cat.getNextId(), monto, fecha, categoria, descripcion, impuesto, tasaImpuesto);

        // Agrega la transacción a la categoría de ingreso.
        cat.agregarTransaccion(transaccion);

        // Actualiza el monto total de ingresos de la categoría.
        cat.setIngresos(cat.getIngresos() + monto);
        return true;
    }

    public int agregarGasto(double monto, Date fecha, String descripcion, String categoria, double tasaImpuesto) {
        // Obtiene la categoría de gasto con la clave especificada.
        CategoriaGasto cat = this.categoriasGasto.get(categoria);

        // Calcula el impuesto correspondiente al monto dado.
        Double impuesto = monto * (tasaImpuesto / 100);

        // Verifica si la categoría existe.
        if (cat == null) {
            // Si no existe, se imprime un mensaje y se retorna -1.
            System.out.println("La categoría de gasto no existe.");
            return -1;
        }

        // Si la categoría existe, se crea una nueva transacción.
        Transaccion transaccion = new Transaccion(cat.actnextId(), monto, fecha, categoria, descripcion, impuesto, tasaImpuesto);

        // Intenta agregar la transacción a la categoría de gasto.
        if (!cat.agregarTransaccion(transaccion)) {
            // Si la transacción excede el presupuesto de la categoría, se imprime un mensaje y se retorna 0.
            System.out.println(categoria);
            System.out.println("El gasto excede el presupuesto de la categoría.");
            return 0;
        }

        // Si la transacción es agregada con éxito, se actualiza el presupuesto de la categoría.
        cat.setPresupuesto(cat.getPresupuesto() - transaccion.getMonto());
        return 1;
    }

    public boolean crearCategoriaIngreso(String categoria) {
        // Verifica si el nombre de la categoría es válido.
        if (!validarCategoria(categoria)) {
            return false;
        }

        // Si es válido, se crea una nueva categoría de ingreso y se agrega al mapa de categorías de ingreso.
        this.categoriasIngreso.put(categoria, new CategoriaIngreso(categoria));
        return true;
    }

    public boolean crearCategoriaGasto(String categoria, double presupuesto) {
        // Verifica si el nombre de la categoría es válido.
        if (!validarCategoria(categoria)) {
            return false;
        }

        // Si es válido, se crea una nueva categoría de gasto y se agrega al mapa de categorías de gasto.
        this.categoriasGasto.put(categoria, new CategoriaGasto(categoria, presupuesto));
        return true;
    }

    public boolean crearCategoriaPrestamo(String categoria){
        // Verifica si el nombre de la categoría es válido.
        if(!validarCategoria(categoria)){
            return false;
        }
        // Si es válido, crea una nueva categoría de préstamos y la agrega al mapa de categorías de préstamos.
        this.categoriasPrestamos.put(categoria, new CategoriaPrestamo(categoria));
        return true;
    }

    public boolean crearCategoriaDeuda(String categoria){
        // Verifica si el nombre de la categoría es válido.
        if (!validarCategoria(categoria)) {
            return false;
        }
        // Si es válido, crea una nueva categoría de deudas y la agrega al mapa de categorías de deudas.
        this.categoriasDeudas.put(categoria, new CategoriaDeuda(categoria));
        return true;
    }

    public boolean editarNombreCategoria(String viejoNombre, String nuevoNombre) {
        // Verifica si el nuevo nombre de categoría ya existe en el sistema.
        if (validarCategoria(nuevoNombre)) {
            // Verifica en todas las categorías (Ingreso, Gasto, Préstamo, Deuda) si la antigua categoría existe.
            // Si existe, se actualiza el nombre de la categoría y el nombre de la categoría en todas las transacciones asociadas.
            // Aquí hay un ejemplo para la categoría de Ingresos. Los otros casos son similares.
            if (this.categoriasIngreso.containsKey(viejoNombre)) {
                CategoriaIngreso cat = this.categoriasIngreso.get(viejoNombre);
                this.categoriasIngreso.remove(viejoNombre);
                this.categoriasIngreso.put(nuevoNombre, cat);
                for (Transaccion transaccion : cat.getTransacciones()) {
                    transaccion.setCategoria(nuevoNombre);
                }
                return true;
            }
            // Si la categoría no existe, se imprime un mensaje y se retorna false.
            else {
                System.out.println("La categoría '" + viejoNombre + "' no existe.");
                return false;
            }
        }
        // Si el nuevo nombre de categoría ya existe en el sistema, se imprime un mensaje y se retorna false.
        else {
            System.out.println("El nuevo nombre de categoría '" + nuevoNombre + "' ya existe en el sistema.");
            return false;
        }
    }

    public int eliminarCategoria(String categoria) {
        // Este método intenta eliminar una categoría de las categorías de Ingresos, Gastos, Préstamos o Deudas.
        // Antes de eliminar, verifica si la categoría tiene transacciones asociadas.
        // Si la categoría tiene transacciones asociadas, imprime un mensaje y retorna 1.
        // Si la categoría no tiene transacciones asociadas, se elimina y se imprime un mensaje de éxito. Retorna 0.
        // Si la categoría no existe, imprime un mensaje y retorna -1.
        // Aquí hay un ejemplo para la categoría de Ingresos. Los otros casos son similares.
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
        }
        else {
            System.out.println("La categoría '" + categoria + "' no existe.");
            return -1;
        }
    }


    public boolean asignarPresupuestoGeneral(double presupuesto){
        // Imprime el saldo actual, el presupuesto total y el nuevo presupuesto que se intenta asignar.
        System.out.println("Saldo actual: " + saldo);
        System.out.println("Presupuesto total actual: " + presupuestoTotal);
        System.out.println("Nuevo presupuesto a asignar: " + presupuesto);

        // Si el nuevo presupuesto es mayor que el saldo disponible, retorna falso.
        if(presupuesto > saldo){
            return false;
        }

        // Actualiza el saldo y el presupuesto total.
        saldo = (saldo - presupuesto) + presupuestoTotal;
        presupuestoTotal = presupuesto;

        // Recorre el mapa de categorías de gasto y asigna a cada una un presupuesto e impuestos igual a cero.
        for (Map.Entry<String, CategoriaGasto> entry : categoriasGasto.entrySet()) {
            entry.getValue().setPresupuesto(0);
            entry.getValue().setImpuestos(0);
        }

        return true;
    }

    public boolean aumentarPresupuestoGeneral(double presupuesto){
        // Si el presupuesto que se desea aumentar es mayor que el saldo disponible, retorna falso.
        if(presupuesto > saldo){
            return false;
        }

        // Incrementa el presupuesto total y decrementa el saldo por el mismo valor.
        presupuestoTotal += presupuesto;
        saldo -= presupuesto;

        return true;
    }

    public boolean asignarPresupuestoACategoriaGasto(String nombreCategoria, double monto) {
        // Obtiene la categoría de gasto con el nombre especificado.
        CategoriaGasto cat = this.categoriasGasto.get(nombreCategoria);

        // Si el presupuesto total es mayor o igual al monto especificado o si el monto es menor o igual
        // al presupuesto de la categoría, entonces se asigna el monto como nuevo presupuesto de la categoría
        // y se actualiza el presupuesto total.
        if(presupuestoTotal >= monto || monto <= cat.getPresupuesto()){
            presupuestoTotal = (presupuestoTotal + cat.getPresupuesto()) - monto;
            cat.setPresupuesto(monto);
        }else{
            return false;
        }

        return true;
    }


    public boolean aumentarPresupuestoACategoriGasto(String nombre, double monto){
        // Obtiene la categoría de gasto por el nombre
        CategoriaGasto cat = this.categoriasGasto.get(nombre);

        // Calcula el nuevo monto sumando el monto actual de la categoría con el monto de aumento
        double montoNuevo = cat.getPresupuesto() + monto;

        // Si el presupuesto total es menor al monto de aumento, no se puede aumentar y se retorna false
        if(presupuestoTotal < monto){
            return false;
        }

        // Se asigna el nuevo monto a la categoría
        cat.setPresupuesto(montoNuevo);

        // Se resta el monto de aumento del presupuesto total
        presupuestoTotal -= monto;
        return true;
    }

    public boolean validarCategoria(String categoria) {
        // Se verifica si la categoría ya existe en alguna de las listas de categorías.
        // Si existe, se imprime un mensaje y se retorna false
        if (this.categoriasIngreso.containsKey(categoria) || this.categoriasGasto.containsKey(categoria)
                || this.categoriasDeudas.containsKey(categoria) || this.categoriasPrestamos.containsKey(categoria)) {
            System.out.println("La categoría ya existe.");
            return false;
        }
        return true;  // Si no existe, retorna true
    }

    public int registrarPagoRecurrente(double monto, String moneda, String frecuencia, LocalDate fechaInicio,
                                       boolean pagadoCompletamente, String descripcion) {
        int sePago = 0;

        // Crea un nuevo objeto de tipo PagoRecurrente
        PagoRecurrente nuevoPago = new PagoRecurrente(monto, moneda, frecuencia, fechaInicio, descripcion, numPagos);
        numPagos++;

        // Llena el ArrayList con las fechas de pago
        int frecuenciaInt = Integer.parseInt(frecuencia);
        for (int i = 0; i < frecuenciaInt; i++) {
            nuevoPago.getFechasPago().add(fechaInicio.plusMonths(i).toString());
        }

        // Verifica si el pago ya ha sido realizado completamente
        if(pagadoCompletamente){
            nuevoPago.setSoloRegistro(true);
            Collections.fill(nuevoPago.getPagados(), true);
            sePago = -1;
        } else if (fechaInicio.equals(app.dia)) { // Verifica si la fecha de inicio es igual a la fecha actual
            // Intenta realizar el pago
            if (realizarPagos(monto, nuevoPago.getMoneda())) {
                nuevoPago.getPagados().set(0, true);
                System.out.println("El PagoRecurrente con ID " + nuevoPago.getId() + " para el mes 1 ha sido pagado.");
                sePago = 1;
            } else {
                System.out.println("No se puede pagar el PagoRecurrente con ID " + nuevoPago.getId() + " para el mes 1.");
            }
        } else {
            sePago = -1;
        }

        // Agrega el nuevo pago al HashMap de pagos recurrentes
        this.pagosRecurrentes.put(nuevoPago.getId(), nuevoPago);
        return sePago;
    }

    public int registrarDeudaOPrestamo(double monto, String moneda, String frecuencia, LocalDate fechaInicio, boolean pagadoCompletamente, String descripcion, String categoria){
        // Se establece un valor por defecto para el factor de aumento
        double aumento = 1;

        // Dependiendo de la moneda se ajusta el factor de aumento
        if(moneda.equals("GBP")){
            aumento *= 1.33;
        }else if(moneda.equals("EUR")){
            aumento *= 1.18;
        }

        Finanzas cat;

        // Se verifica si la categoría es de deudas y se obtiene
        if(categoriasDeudas.containsKey(categoria)){
            cat = categoriasDeudas.get(categoria);
        }else{
            // Si no, se asume que es de préstamos y se obtiene
            cat = categoriasPrestamos.get(categoria);

            // Si la fecha de inicio es igual a la fecha actual, se incrementa el saldo
            if(fechaInicio.equals(app.dia)){
                System.out.println("hecho");
                saldo += monto * aumento;
            }
        }

        // Se registra el pago recurrente para la categoría obtenida
        return cat.registrarPagoRecurrente(monto, moneda, frecuencia, fechaInicio, pagadoCompletamente, descripcion);
    }

    public int actualizarDeudaOPrestamo(int id, double monto, String moneda, String frecuencia, LocalDate fechaInicio, String descripcion, String categoria){
        // Se establece un valor por defecto para el factor de aumento
        double aumento = 1;

        // Dependiendo de la moneda se ajusta el factor de aumento
        if(moneda.equals("GBP")){
            aumento *= 1.33;
        }else if(moneda.equals("EUR")){
            aumento *= 1.18;
        }

        Finanzas cat;

        // Se verifica si la categoría es de deudas y se obtiene
        if(categoriasDeudas.containsKey(categoria)){
            cat = categoriasDeudas.get(categoria);
        }else{
            // Si no, se asume que es de préstamos y se obtiene
            cat = categoriasPrestamos.get(categoria);

            // Si la fecha de inicio es igual a la fecha actual, se incrementa el saldo
            if(fechaInicio.equals(app.dia)){
                System.out.println("hecho");
                saldo += monto * aumento;
            }
        }

        // Se imprime el saldo y el monto
        System.out.println("Saldo: " + saldo);
        System.out.println("Monto: " + monto);

        // Se actualiza el pago recurrente para la categoría obtenida
        return cat.actualizarPagoRecurrente(id, monto, moneda, frecuencia, fechaInicio, descripcion);
    }

    public int buscarPagoRecurrente(int id){
        // Buscar el PagoRecurrente en el HashMap usando el id
        PagoRecurrente pago = pagosRecurrentes.get(id);

        // Dependiendo del estado del pago se retorna un valor diferente
        if(pago == null){
            return 0; //No se encontro
        } else if(pago.isSoloRegistro() || noEstaPagandose(pago.getPagados())){
            return 1; //Dejar editar todo
        } else{
            return -1; //No dejar editar
        }
    }


    public int buscarPrestamoODeuda(int id, String categoria){
        // Inicializamos la respuesta con -3, que representará que el pago no fue encontrado
        int resp = -3;
        // Verificamos si la categoría está en el mapa de categorías de deudas y si el id está en los pagos recurrentes de esta categoría
        if(categoriasDeudas.containsKey(categoria) && categoriasDeudas.get(categoria).getPagosRecurrentes().containsKey(id)){
            // Si es así, usamos el método buscarPagoRecurrente de la categoría de deuda para obtener el id del pago recurrente
            resp = categoriasDeudas.get(categoria).buscarPagoRecurrente(id);
            // Hacemos el mismo proceso para las categorías de préstamos
        }else if(categoriasPrestamos.containsKey(categoria) && categoriasPrestamos.get(categoria).getPagosRecurrentes().containsKey(id)){
            resp = categoriasPrestamos.get(categoria).buscarPagoRecurrente(id);
        }

        // Devolvemos la respuesta
        return resp;
    }

    public boolean noEstaPagandose(ArrayList<Boolean> pagados) {
        // Recorremos el array de booleans que representan si un pago ha sido hecho o no
        for (boolean pagado : pagados) {
            // Si encontramos un pago que ha sido hecho (true), devolvemos false, indicando que no todos los pagos están sin hacer
            if (pagado) {
                return false;
            }
        }
        // Si todos los pagos están sin hacer (false), devolvemos true
        return true;
    }

    public HashMap<Integer, PagoRecurrente> mostrarPagosCategorizados(boolean bool) {
        // Creamos un nuevo HashMap para guardar los pagos filtrados
        HashMap<Integer, PagoRecurrente> pagosFiltrados = new HashMap<>();
        // Recorremos todos los pagos recurrentes
        for (Map.Entry<Integer, PagoRecurrente> entry : pagosRecurrentes.entrySet()) {
            // Si bool es true, queremos los pagos que han sido completamente pagados o son solo un registro
            if (bool) {
                if (entry.getValue().isPagadoCompletamente() || entry.getValue().isSoloRegistro()) {
                    // Si el pago cumple con las condiciones, lo agregamos al mapa de pagos filtrados
                    pagosFiltrados.put(entry.getKey(), entry.getValue());
                }
                // Si bool es false, queremos los pagos que no han sido completamente pagados y no son solo un registro
            } else {
                if (!entry.getValue().isPagadoCompletamente() && !entry.getValue().isSoloRegistro()) {
                    pagosFiltrados.put(entry.getKey(), entry.getValue());
                }
            }
        }
        // Devolvemos el mapa de pagos filtrados
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
        // Iniciamos un mensaje vacío
        String mensaje = "";
        // Recorremos todas las categorías de deudas
        for (CategoriaDeuda cat : categoriasDeudas.values()) {
            // Agregamos el mensaje de los pagos pendientes de cada categoría de deudas al mensaje general
            mensaje += cat.verificarPagosPendientes();
        }
        // Hacemos lo mismo para las categorías de préstamos
        for (CategoriaPrestamo cat : categoriasPrestamos.values()) {
            mensaje += cat.verificarPagosPendientes();
        }

        // Devolvemos el mensaje
        return mensaje;
    }

    public boolean realizarPagos(double monto, String moneda){
        // Dependiendo de la moneda, convertimos el monto a dólares
        if (moneda.equals("EUR")) {
            monto = monto * 1.18;  // monto en dólares
        } else if (moneda.equals("GBP")) {
            monto = monto * 1.33;  // monto en dólares
        }

        // Si el monto es mayor que el saldo, devolvemos false
        if(monto > saldo){
            return false;
        }

        // Descontamos el monto del saldo
        saldo -= monto;

        // Devolvemos true indicando que se realizó el pago
        return true;
    }

    public int actualizarPagoRecurrente(int id, double monto, String moneda, String frecuencia, LocalDate fechaInicio, String descripcion){
        // Variable que indica si el pago fue hecho o no
        int sePago = 0;

        // Obtenemos el pago recurrente a actualizar
        PagoRecurrente pago = pagosRecurrentes.get(id);

        // Actualizamos los datos del pago recurrente
        pago.setMonto(monto);
        pago.setMoneda(moneda);
        pago.setFrecuencia(frecuencia);
        pago.setFechaInicio(fechaInicio);
        pago.setDescripcion(descripcion);

        // Limpiamos las fechas de pago y las recalculamos
        pago.getFechasPago().clear();
        for (int i = 0; i < Integer.parseInt(frecuencia); i++) {
            // Agregar i meses a la fecha de inicio y guardarla en el ArrayList
            pago.getFechasPago().add(fechaInicio.plusMonths(i).toString());
        }

        // Dependiendo si el pago es solo registro o no, marcamos todos los pagos como realizados o no realizados
        if(pago.isSoloRegistro()){
            pago.setPagados(new ArrayList<>(Collections.nCopies(Integer.parseInt(frecuencia), true)));
            sePago = -1;
        }else{
            pago.setPagados(new ArrayList<>(Collections.nCopies(Integer.parseInt(frecuencia), false)));
            sePago = -1;

            // Si la fecha de inicio es hoy y se puede realizar el pago, marcamos el primer pago como realizado
            if(pago.getFechaInicio().equals(app.dia) && realizarPagos(pago.getMonto(), pago.getMoneda())){
                pago.getPagados().set(0, true);
                sePago = 1;
            }
        }

        // Devolvemos si el pago se realizó o no
        return sePago;
    }


    public void eliminarPagoRecurrente(int id){
        pagosRecurrentes.remove(id);
    }

    public void eliminarDeudaPrestamo(int id, String categoria){
        // Si la categoría especificada es una deuda
        if(categoriasDeudas.containsKey(categoria)){
            // Se elimina el pago recurrente con la ID especificada de las deudas de esa categoría
            categoriasDeudas.get(categoria).getPagosRecurrentes().remove(id);
        }else{
            // Si no es una deuda, se asume que es un préstamo y se elimina de esa categoría
            categoriasPrestamos.get(categoria).getPagosRecurrentes().remove(id);
        }
    }

    public void mostrarPagoRecurrente(){
        // Imprimir cada pago recurrente almacenado en la estructura de datos pagosRecurrentes
        for (Map.Entry<Integer, PagoRecurrente> entry : pagosRecurrentes.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

    public boolean existenTransaccionesGastos(){
        // Para cada categoría de gasto
        for (Map.Entry<String, CategoriaGasto> entry : categoriasGasto.entrySet()) {
            // Si alguna categoría de gasto tiene alguna transacción, retornar verdadero
            if(!entry.getValue().getTransacciones().isEmpty()){
                return true;
            }
        }
        // Si ninguna categoría de gasto tiene transacciones, retornar falso
        return false;
    }

    public String mostrarCategorias(String tipo) {
        // String para acumular los nombres de las categorías
        String text = "";

        // Si se piden categorías de Ingreso o todas las categorías
        if (tipo.equals("Ingreso") || tipo.equals("Ambas")) {
            for (String categoria : this.categoriasIngreso.keySet()) {
                text += categoria + "\n";
            }
        }
        // Si se piden categorías de Gasto o todas las categorías
        if (tipo.equals("Gasto") || tipo.equals("Ambas")) {
            for (String categoria : this.categoriasGasto.keySet()) {
                text += categoria + "\n";
            }
        }
        // Si se piden categorías de Préstamo o todas las categorías
        if(tipo.equals("Prestamo") || tipo.equals("Ambas")){
            for(String categoria : this.categoriasPrestamos.keySet()){
                text += categoria + "\n";
            }
        }
        // Si se piden categorías de Deuda o todas las categorías
        if(tipo.equals("Deuda") || tipo.equals("Ambas")){
            for(String categoria : this.categoriasDeudas.keySet()){
                text += categoria + "\n";
            }
        }

        // Retornar los nombres de las categorías
        return text;
    }


    public String mostrarDeudasPrestamos(String categoria, Boolean bool){
        // Cadena para almacenar los mensajes
        String mensaje = "";
        // Mapa para almacenar pagos filtrados
        HashMap<Integer, PagoRecurrente> pagosFiltrados = new HashMap<>();
        Finanzas pago;

        // Comprueba si la categoría pertenece a Deudas
        if(categoriasDeudas.containsKey(categoria)){
            pago = categoriasDeudas.get(categoria);
        }else{
            // Si no, asume que pertenece a Prestamos
            pago = categoriasPrestamos.get(categoria);
        }

        // Filtra pagos en base a su estado
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

        // Formatea el mensaje con pagos filtrados
        for (PagoRecurrente pagoRecurrente : pagosFiltrados.values()) {
            mensaje += pagoRecurrente.toString() + '\n';
        }

        return mensaje;
    }

    public boolean esDeuda(String cat){
        // Comprueba si una categoría es de tipo Deuda
        if(categoriasDeudas.containsKey(cat)){
            return true;
        }
        return false;
    }

    public String mostrarCategoriasOrdenadasAZ(String tipo) {
        // Lista para almacenar categorías
        List<String> categoriasOrdenadas = new ArrayList<>();

        // Agrega las categorías en función del tipo
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

        // Ordena las categorías en orden alfabético con el método bubbleSort()
        bubbleSort(categoriasOrdenadas);

        // Construye el texto final con las categorías ordenadas
        String text = "";
        for (String categoria : categoriasOrdenadas) {
            text += categoria + "\n";
        }
        return text;
    }


    public String mostrarCategoriasOrdenadasZA(String tipo) {
        // Crear una lista para almacenar las categorías
        List<String> categoriasOrdenadas = new ArrayList<>();

        // Añadir categorías a la lista según el tipo requerido
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

        // Ordenar la lista en orden alfabético y luego invertirla para obtener Z-A
        bubbleSort(categoriasOrdenadas);
        Collections.reverse(categoriasOrdenadas);

        // Crear una cadena con las categorías ordenadas
        String text = "";
        for (String categoria : categoriasOrdenadas) {
            text += categoria + "\n";
        }
        return text;
    }

    private void bubbleSort(List<String> list) {
        // Implementar el algoritmo de ordenamiento burbuja para ordenar la lista de categorías
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).compareToIgnoreCase(list.get(j + 1)) > 0) {
                    // Intercambiar elementos si el actual es mayor que el siguiente
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private void insertionSort(List<Transaccion> transacciones, Comparator<Transaccion> comparator, boolean ascendente) {
        // Implementar el algoritmo de ordenamiento por inserción para ordenar la lista de transacciones
        for (int i = 1; i < transacciones.size(); ++i) {
            Transaccion key = transacciones.get(i);
            int j = i - 1;
            // Mover los elementos del arreglo que son mayores que la clave, a una posición adelante de su posición actual
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
        // Crear un StringBuilder para construir la salida
        StringBuilder sb = new StringBuilder();

        // Obtener la categoría de ingresos y gastos que corresponde a la cadena de categoría proporcionada
        CategoriaIngreso catIngreso = this.categoriasIngreso.get(categoria);
        CategoriaGasto catGasto = this.categoriasGasto.get(categoria);

        // Si la categoría corresponde a una categoría de ingresos...
        if (catIngreso != null) {
            // Crear una lista de las transacciones en esa categoría
            List<Transaccion> transacciones = new ArrayList<>(catIngreso.getTransacciones());

            // Ordenar la lista utilizando el algoritmo de ordenamiento por inserción y el comparador proporcionado
            insertionSort(transacciones, comparacion ,ascendente);

            // Agregar la información de las transacciones ordenadas a la salida
            sb.append("Transacciones de la categoría de ingresos '" + categoria + "': \n");
            transacciones.forEach(transaccion -> sb.append("Id: "+ transaccion.getId() + ", Monto: " + transaccion.getMonto() + ", Impuesto: " + transaccion.getImpuesto() +
                    ", Tasa Impuesto: " + transaccion.getTasaImpuesto() + ", Fecha: " + transaccion.getFecha() + ", Descripción: " + transaccion.getDescripcion() + "\n"));
        }
        // Si la categoría corresponde a una categoría de gastos...
        else if (catGasto != null) {
            // Crear una lista de las transacciones en esa categoría
            List<Transaccion> transacciones = new ArrayList<>(catGasto.getTransacciones());

            // Ordenar la lista utilizando el algoritmo de ordenamiento por inserción y el comparador proporcionado
            insertionSort(transacciones, comparacion ,ascendente);

            // Agregar la información de las transacciones ordenadas a la salida
            sb.append("Transacciones de la categoría de gastos '" + categoria + "': \n");
            transacciones.forEach(transaccion -> sb.append("Id: "+ transaccion.getId() + ", Monto: " + transaccion.getMonto() + ", Impuesto: " + transaccion.getImpuesto() +
                    ", Tasa Impuesto: " + transaccion.getTasaImpuesto() + ", Fecha: " + transaccion.getFecha() + ", Descripción: " + transaccion.getDescripcion() + "\n"));
        }
        // Si la categoría no corresponde a una categoría de ingresos o gastos...
        else {
            // Indicar que la categoría no existe
            sb.append("La categoría '" + categoria + "' no existe.");
        }

        // Devolver la salida como una cadena
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
