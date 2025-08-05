package empresa.model;

import empresa.utils.enums;

import java.time.LocalDate;
import java.util.List;

public class Contrato {
    private int id;
    private String nombreCliente;
    private enums.tipoServicio tipoServicio;
    private double tarifaMensual;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private enums.estado estado;
    private List<Pago> pagos;




}
//    id_contrato       BIGINT AUTO_INCREMENT PRIMARY KEY,
//    nombre_cliente    VARCHAR(100) NOT NULL,
//    tipo_servicio     ENUM('AGUA','ELECTRICIDAD','GAS') NOT NULL,
//    tarifa_mensual    DECIMAL(12,2) NOT NULL,
//    fecha_inicio      DATE NOT NULL,
//    fecha_fin         DATE NOT NULL,
//    estado            ENUM('ACTIVO','CANCELADO','VENCIDO') DEFAULT 'ACTIVO'