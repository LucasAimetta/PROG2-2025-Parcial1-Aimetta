package empresa.model;

import empresa.utils.enums;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "contrato_servicio")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private int id;
    @Column(name = "nombre_cliente",length = 100,nullable = false)
    private String nombreCliente;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servicio",nullable = false)
    private enums.tipoServicio tipoServicio;
    @Column(name = "tarifa_mensual",nullable = false)
    private double tarifaMensual;
    @Column(name = "fecha_inicio",nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin",nullable = false)
    private LocalDate fechaFin;
    @Enumerated(EnumType.STRING)
    private enums.estado estado;
    @OneToMany(mappedBy = "contrato")
    private List<Pago> pagos;


    public Contrato(String nombreCliente, enums.tipoServicio tipoServicio, double tarifaMensual, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.tarifaMensual = tarifaMensual;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado= enums.estado.ACTIVO;
        this.pagos=new ArrayList<>();
    }

    public Contrato() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
//    id_contrato       BIGINT AUTO_INCREMENT PRIMARY KEY,
//    nombre_cliente    VARCHAR(100) NOT NULL,
//    tipo_servicio     ENUM('AGUA','ELECTRICIDAD','GAS') NOT NULL,
//    tarifa_mensual    DECIMAL(12,2) NOT NULL,
//    fecha_inicio      DATE NOT NULL,
//    fecha_fin         DATE NOT NULL,
//    estado            ENUM('ACTIVO','CANCELADO','VENCIDO') DEFAULT 'ACTIVO'