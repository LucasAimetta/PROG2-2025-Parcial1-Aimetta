package empresa.dto;

import empresa.model.Contrato;

import java.time.LocalDate;

public class PagoDTO {
    private Contrato contrato;
    private LocalDate fechaPago;
    private double monto;

    public PagoDTO(Contrato contrato, LocalDate fechaPago, double monto) {
        this.contrato = contrato;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public PagoDTO() {
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
