package empresa.dto;

import empresa.utils.enums;

public class ResultadoNuevoPago {
    private int idContrato;
    private double montoPagado;
    private enums.estado estadoActualizado;

    public ResultadoNuevoPago(int idContrato, double montoPagado, enums.estado estadoActualizado) {
        this.idContrato = idContrato;
        this.montoPagado = montoPagado;
        this.estadoActualizado = estadoActualizado;
    }

    public ResultadoNuevoPago() {
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public enums.estado getEstadoActualizado() {
        return estadoActualizado;
    }

    public void setEstadoActualizado(enums.estado estadoActualizado) {
        this.estadoActualizado = estadoActualizado;
    }
}
// id_contrato, monto_pagado, estado_actualizado