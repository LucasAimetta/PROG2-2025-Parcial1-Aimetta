package empresa.dto;

import empresa.utils.enums;

public class ResumenContratosCancelados {

    private enums.tipoServicio tipoServicio;
    private long cantidadContratos;
    private double totalCobrado;

    public ResumenContratosCancelados(enums.tipoServicio tipoServicio, long cantidadContratos, double totalCobrado) {
        this.tipoServicio = tipoServicio;
        this.cantidadContratos = cantidadContratos;
        this.totalCobrado = totalCobrado;
    }

    public enums.tipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(enums.tipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public long getCantidadContratos() {
        return cantidadContratos;
    }

    public void setCantidadContratos(long cantidadContratos) {
        this.cantidadContratos = cantidadContratos;
    }

    public double getTotalCobrado() {
        return totalCobrado;
    }

    public void setTotalCobrado(double totalCobrado) {
        this.totalCobrado = totalCobrado;
    }
}
