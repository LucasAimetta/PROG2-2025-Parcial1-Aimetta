package empresa.dto;

import empresa.model.Pago;
import empresa.utils.enums;

import java.time.LocalDate;
import java.util.List;

public class ContratoDTO {
    private String nombreCliente;
    private enums.tipoServicio tipoServicio;
    private double tarifaMensual;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private enums.estado estado;
    private List<Pago> pagos;

    public ContratoDTO(String nombreCliente, enums.tipoServicio tipoServicio, double tarifaMensual, LocalDate fechaInicio, LocalDate fechaFin, enums.estado estado, List<Pago> pagos) {
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.tarifaMensual = tarifaMensual;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.pagos = pagos;
    }

    public ContratoDTO() {
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public enums.tipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(enums.tipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(double tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public enums.estado getEstado() {
        return estado;
    }

    public void setEstado(enums.estado estado) {
        this.estado = estado;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
