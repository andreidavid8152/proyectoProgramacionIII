public class CategoriaGasto extends Categoria {
    private double presupuesto;

    public CategoriaGasto(String nombre, double presupuesto) {
        super(nombre);
        this.presupuesto = presupuesto;
    }
    // Constructor de la clase que recibe el nombre de la categoría y el presupuesto asignado

    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        double totalGasto = transaccion.getMonto();
        // Se obtiene el monto de la transacción

        if (totalGasto > this.presupuesto) {
            return false;
        }
        // Se verifica si el monto de la transacción supera el presupuesto de la categoría.
        // En caso afirmativo, la transacción no se agrega y se devuelve false.

        impuestos += transaccion.getImpuesto();
        // Se actualiza el total de impuestos sumando el impuesto de la transacción

        this.transacciones.add(transaccion);
        // Se agrega la transacción a la lista de transacciones de la categoría

        return true;
    }

    public int editarTransaccion(int id, double monto, String descripcion, double impuesto) {
        Transaccion transaccion = buscarTransaccion(id);
        // Se busca la transacción con el ID especificado

        if (transaccion != null) {
            if (monto > this.presupuesto) {
                System.out.println("El monto editado excede el presupuesto total de la categoría.");
                return 0;
            }
            // Se verifica si el monto editado supera el presupuesto de la categoría.
            // En caso afirmativo, se muestra un mensaje de error y se devuelve 0.

            transaccion.setTasaImpuesto(impuesto);
            // Se actualiza la tasa de impuesto de la transacción

            presupuesto = (presupuesto + transaccion.getMonto()) - monto;
            // Se ajusta el presupuesto restando el monto original de la transacción y sumando el monto editado

            impuestos = (impuestos - transaccion.getImpuesto()) + (monto * (impuesto / 100));
            // Se actualiza el total de impuestos restando el impuesto original de la transacción y sumando el nuevo impuesto

            transaccion.setImpuesto(monto * (impuesto / 100));
            // Se actualiza el impuesto de la transacción

            transaccion.setMonto(monto);
            // Se actualiza el monto de la transacción

            transaccion.setDescripcion(descripcion);
            // Se actualiza la descripción de la transacción

            return 1;  // La transacción se edita correctamente
        }
        return -1;  // No se encontró la transacción con el ID especificado
    }

    public boolean eliminarTransaccion(int id) {
        Transaccion transaccion = buscarTransaccion(id);
        // Se busca la transacción con el ID especificado

        if (transaccion != null) {
            this.presupuesto += transaccion.getMonto();
            // Se reajusta el presupuesto al eliminar la transacción sumando el monto de la transacción

            this.impuestos = impuestos - transaccion.getImpuesto();
            // Se actualiza el total de impuestos restando el impuesto de la transacción

            transacciones.remove(transaccion);
            // Se elimina la transacción de la lista de transacciones de la categoría

            return true;  // La transacción se elimina exitosamente
        }
        return false;  // No se encontró la transacción con el ID especificado
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
}
