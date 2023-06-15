public class CategoriaGasto extends Categoria {
    private double presupuesto;

    public CategoriaGasto(String nombre, double presupuesto) {
        super(nombre);
        this.presupuesto = presupuesto;
    }

    @Override
    public int editarTransaccion(int id, double monto, String descripcion) {
        Transaccion transaccion = buscarTransaccion(id);
        if (transaccion != null) {
            if (monto > this.presupuesto) {
                System.out.println("El monto editado excede el presupuesto total de la categoría.");
                return 0;
            }
            this.presupuesto -= transaccion.getMonto();
            transaccion.setMonto(monto);
            transaccion.setDescripcion(descripcion);
            return 1;
        }
        return -1;
    }

    @Override
    public boolean eliminarTransaccion(int id) {
        Transaccion transaccion = buscarTransaccion(id);
        System.out.println("P:" +presupuesto);
        if (transaccion != null) {
            this.presupuesto += transaccion.getMonto(); // Se reajusta el presupuesto al eliminar la transacción
            transacciones.remove(transaccion);
            System.out.println("P:" +presupuesto);
            return true;  // La transacción se elimina exitosamente
        }
        return false;  // No se encontró la transacción
    }


    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        double totalGasto = transaccion.getMonto();
        System.out.println(totalGasto);
        System.out.println(presupuesto);
        if (totalGasto > this.presupuesto) {
            return false;
        }
        this.transacciones.add(transaccion);
        return true;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getPresupuesto() {
        return presupuesto;
    }
}