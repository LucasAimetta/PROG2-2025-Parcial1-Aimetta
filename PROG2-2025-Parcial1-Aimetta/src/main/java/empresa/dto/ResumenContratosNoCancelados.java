package empresa.dto;

public class ResumenContratosNoCancelados {
    private double montoEsperado;
    private double montoPagado;
    private int idContrato;

    public ResumenContratosNoCancelados(double montoEsperado, double montoPagado, int idContrato) {
        this.montoEsperado = montoEsperado;
        this.montoPagado = montoPagado;
        this.idContrato = idContrato;
    }

    public ResumenContratosNoCancelados() {
    }

    public double getMontoEsperado() {
        return montoEsperado;
    }

    public void setMontoEsperado(double montoEsperado) {
        this.montoEsperado = montoEsperado;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
}
