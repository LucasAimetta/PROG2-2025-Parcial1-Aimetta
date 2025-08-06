package empresa.dto;

import empresa.utils.enums;

import java.time.LocalDate;

public class FiltrosContratos {
    private String nombreCliente;
    private enums.tipoServicio tipoServicio;
    private LocalDate fechaInicioDesde;
    private LocalDate fechaInicioHasta;
    private double tarifaMensualDesde;
    private double tarifaMensualHasta;

    public FiltrosContratos(String nombreCliente, enums.tipoServicio tipoServicio, LocalDate fechaInicioDesde, LocalDate fechaInicioHasta, double tarifaMensualDesde, double tarifaMensualHasta) {
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.fechaInicioDesde = fechaInicioDesde;
        this.fechaInicioHasta = fechaInicioHasta;
        this.tarifaMensualDesde = tarifaMensualDesde;
        this.tarifaMensualHasta = tarifaMensualHasta;
    }

    public FiltrosContratos() {
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

    public LocalDate getFechaInicioDesde() {
        return fechaInicioDesde;
    }

    public void setFechaInicioDesde(LocalDate fechaInicioDesde) {
        this.fechaInicioDesde = fechaInicioDesde;
    }

    public LocalDate getFechaInicioHasta() {
        return fechaInicioHasta;
    }

    public void setFechaInicioHasta(LocalDate fechaInicioHasta) {
        this.fechaInicioHasta = fechaInicioHasta;
    }

    public double getTarifaMensualDesde() {
        return tarifaMensualDesde;
    }

    public void setTarifaMensualDesde(double tarifaMensualDesde) {
        this.tarifaMensualDesde = tarifaMensualDesde;
    }

    public double getTarifaMensualHasta() {
        return tarifaMensualHasta;
    }

    public void setTarifaMensualHasta(double tarifaMensualHasta) {
        this.tarifaMensualHasta = tarifaMensualHasta;
    }
}
