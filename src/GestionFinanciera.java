import java.util.*;

public class GestionFinanciera {
    private Map<String, CategoriaIngreso> categoriasIngreso;
    private Map<String, CategoriaGasto> categoriasGasto;
    private double presupuestoTotal;

    public GestionFinanciera() {
        this.categoriasIngreso = new HashMap<>();
        this.categoriasGasto = new HashMap<>();
        this.presupuestoTotal = 0;
    }

    public boolean agregarIngreso(double monto, Date fecha, String descripcion, String categoria) {
        CategoriaIngreso cat = this.categoriasIngreso.get(categoria);
        Transaccion transaccion = new Transaccion(cat.getNextId(),monto, fecha, categoria, descripcion);
        if (cat == null) {
            System.out.println("La categoría de ingreso no existe.");
            return false;
        }
        cat.agregarTransaccion(transaccion);
        return true;
    }


    public int agregarGasto(double monto, Date fecha, String descripcion, String categoria) {
        CategoriaGasto cat = this.categoriasGasto.get(categoria);
        if (cat == null) {
            System.out.println("La categoría de gasto no existe.");
            return -1;
        }

        Transaccion transaccion = new Transaccion(cat.actnextId(), monto, fecha, categoria, descripcion);
        if (!cat.agregarTransaccion(transaccion)) {
            System.out.println("El gasto excede el presupuesto de la categoría.");
            return 0;
        }
        // Actualizar el presupuesto de la categoría.
        cat.setPresupuesto(cat.getPresupuesto() - transaccion.getMonto());
        return 1;
    }


    public boolean validarCategoria(String categoria) {
        if (this.categoriasIngreso.containsKey(categoria) || this.categoriasGasto.containsKey(categoria)) {
            System.out.println("La categoría ya existe.");
            return false;
        }
        return true;
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

    public boolean asignarPresupuestoACategoriaGasto(String nombreCategoria, double monto) {
        CategoriaGasto cat = this.categoriasGasto.get(nombreCategoria);

        // Verifica si la categoría existe
        if (cat == null) {
            System.out.println("La categoría proporcionada no existe.");
            return false;
        }

        // Verifica si el monto excede al presupuesto total
        if (monto > presupuestoTotal) {
            System.out.println("El monto excede el presupuesto total.");
            return false;
        }

        double presupuestoActual = cat.getPresupuesto();
        double totalAsignado = this.categoriasGasto.values().stream()
                .mapToDouble(CategoriaGasto::getPresupuesto)
                .sum();

        // Verifica si el monto, más lo ya asignado (excluyendo el presupuesto actual de la categoría), excede al presupuesto total
        if ((totalAsignado - presupuestoActual + monto) > presupuestoTotal) {
            System.out.println("El monto excede el presupuesto total disponible.");
            return false;
        }

        // Asigna el nuevo presupuesto a la categoría de gasto
        cat.setPresupuesto(monto);

        // Restamos la diferencia entre el monto nuevo y el presupuesto actual del presupuesto total
        presupuestoTotal -= (monto - presupuestoActual);

        return true;
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

    public String ordenarTransacciones(String categoria, boolean ascendente) {
        StringBuilder sb = new StringBuilder();
        CategoriaIngreso catIngreso = this.categoriasIngreso.get(categoria);
        CategoriaGasto catGasto = this.categoriasGasto.get(categoria);

        if (catIngreso != null) {
            List<Transaccion> transacciones = new ArrayList<>(catIngreso.getTransacciones());
            insertionSort(transacciones, ascendente);
            sb.append("Transacciones de la categoría de ingresos '" + categoria + "': \n");
            transacciones.forEach(transaccion -> sb.append("Monto: " + transaccion.getMonto() + ", Descripción: " + transaccion.getDescripcion() + "\n"));
        } else if (catGasto != null) {
            List<Transaccion> transacciones = new ArrayList<>(catGasto.getTransacciones());
            insertionSort(transacciones, ascendente);
            sb.append("Transacciones de la categoría de gastos '" + categoria + "': \n");
            transacciones.forEach(transaccion -> sb.append("Monto: " + transaccion.getMonto() + ", Descripción: " + transaccion.getDescripcion() + "\n"));
        } else {
            sb.append("La categoría '" + categoria + "' no existe.");
        }

        return sb.toString();
    }


    private void insertionSort(List<Transaccion> transacciones, boolean ascendente) {
        for (int i = 1; i < transacciones.size(); ++i) {
            Transaccion key = transacciones.get(i);
            int j = i - 1;
            if (ascendente) {
                while (j >= 0 && transacciones.get(j).getMonto() > key.getMonto()) {
                    transacciones.set(j + 1, transacciones.get(j));
                    j = j - 1;
                }
            } else {
                while (j >= 0 && transacciones.get(j).getMonto() < key.getMonto()) {
                    transacciones.set(j + 1, transacciones.get(j));
                    j = j - 1;
                }
            }
            transacciones.set(j + 1, key);
        }
    }

}
