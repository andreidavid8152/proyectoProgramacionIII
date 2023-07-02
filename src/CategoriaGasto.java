public class CategoriaGasto extends Categoria {
    private double presupuesto;

    public CategoriaGasto(String nombre, double presupuesto) {
        super(nombre);
        this.presupuesto = presupuesto;
    }

    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        double totalGasto = transaccion.getMonto();
        System.out.println(totalGasto);
        System.out.println(presupuesto);
        if (totalGasto > this.presupuesto) {
            return false;
        }
        impuestos += transaccion.getImpuesto();
        this.transacciones.add(transaccion);
        return true;
    }

    public int editarTransaccion(int id, double monto, String descripcion, double impuesto) {
        Transaccion transaccion = buscarTransaccion(id);
        if (transaccion != null) {
            if (monto > this.presupuesto) {
                System.out.println("El monto editado excede el presupuesto total de la categoría.");
                return 0;
            }
            transaccion.setTasaImpuesto(impuesto);
            presupuesto = (presupuesto+transaccion.getMonto())-monto;
            impuestos = (impuestos-transaccion.getImpuesto())+(monto*(impuesto/100));
            transaccion.setImpuesto(monto*(impuesto/100));
            transaccion.setMonto(monto);
            transaccion.setDescripcion(descripcion);
            return 1;
        }
        return -1;
    }

    public boolean eliminarTransaccion(int id) {
        Transaccion transaccion = buscarTransaccion(id);
        System.out.println("P:" +presupuesto);
        if (transaccion != null) {
            this.presupuesto += transaccion.getMonto(); // Se reajusta el presupuesto al eliminar la transacción
            this.impuestos = impuestos - transaccion.getImpuesto();
            transacciones.remove(transaccion);
            System.out.println("P:" +presupuesto);
            return true;  // La transacción se elimina exitosamente
        }
        return false;  // No se encontró la transacción
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
}