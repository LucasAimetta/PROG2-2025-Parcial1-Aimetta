package empresa.model;

import java.time.LocalDate;

public class Pago {
    private int id;
    private Contrato contrato;
    private LocalDate fechaPago;
    private double monto;
}
// id_pago         BIGINT AUTO_INCREMENT PRIMARY KEY,
//    id_contrato     BIGINT NOT NULL,
//    fecha_pago      DATE NOT NULL,
//    monto           DECIMAL(12,2) NOT NULL,