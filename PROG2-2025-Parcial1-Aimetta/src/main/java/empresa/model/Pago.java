package empresa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "pago_servicio")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;
    @Column(name = "fecha_pago",nullable = false)
    private LocalDate fechaPago;
    @Column(nullable = false)
    private double monto;


    public Pago( Contrato contrato, LocalDate fechaPago, double monto) {

        this.contrato = contrato;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Pago() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
// id_pago         BIGINT AUTO_INCREMENT PRIMARY KEY,
//    id_contrato     BIGINT NOT NULL,
//    fecha_pago      DATE NOT NULL,
//    monto           DECIMAL(12,2) NOT NULL,