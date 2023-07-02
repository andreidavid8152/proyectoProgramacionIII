public class CategoriaIngreso extends Categoria {

    private double ingresos;

    public CategoriaIngreso(String nombre) {
        super(nombre);
    }

    public double getIngresos() {
        return ingresos;
    }

    public int editarTransaccion(int id, double monto, String descripcion, double impuesto) {
        Transaccion transaccion = buscarTransaccion(id);
        if (transaccion != null) {
            transaccion.setTasaImpuesto(impuesto);
            ingresos = (ingresos-transaccion.getMonto())+monto;
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
        if (transaccion != null) {
            this.ingresos -= transaccion.getMonto(); // Se reajusta el presupuesto al eliminar la transacción
            this.impuestos = impuestos - transaccion.getImpuesto();
            transacciones.remove(transaccion);
            return true;  // La transacción se elimina exitosamente
        }
        return false;  // No se encontró la transacción
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
}