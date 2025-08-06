package empresa.service;

import empresa.dto.nuevoPago;
import empresa.model.Contrato;
import empresa.model.Pago;
import empresa.utils.HibernateUtil;
import empresa.utils.enums;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicaTest {
    private Logica logica;
    private Session session;
    private Contrato contrato1;
    private Contrato contrato2;
    private Pago pago1;

    @BeforeEach
    void setUp(){
        logica = Logica.getInstance();
        session = HibernateUtil.getSession();
        session.beginTransaction();
        contrato1 = crearContrato("Lucas Aimetta", enums.tipoServicio.AGUA, 50000, LocalDate.of(2024,1,1),LocalDate.of(2027,1,1) );
        contrato2 = crearContrato("Tadeo Isaac", enums.tipoServicio.ELECTRICIDAD, 75000, LocalDate.of(2010,1,1), LocalDate.of(2026,1,1));
        session.persist(contrato1);
        session.persist(contrato2);
        pago1 = crearPago(contrato2,LocalDate.now(),15000);
        session.persist(pago1);
        session.getTransaction().commit();

    }
    @AfterEach
    void tearDown() {
        if (session != null && session.isOpen()) {
            session.beginTransaction();
            session.createQuery("delete from Pago").executeUpdate();
            session.createQuery("delete from Contrato").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    public void registrarPagoTest(){
        //ARRANGE
        Pago pago = crearPago(contrato1,LocalDate.now(),70000);
        nuevoPago

        //ACT
        logica.registrarPago()
        //ASSERT

    }

    public static Pago crearPago(Contrato contrato, LocalDate fechaPago, double monto) {
     return new Pago(contrato,fechaPago,monto);
    }

    public static Contrato crearContrato(String nombreCliente, enums.tipoServicio tipoServicio, double tarifaMensual, LocalDate fechaInicio, LocalDate fechaFin){
        return new Contrato(nombreCliente,tipoServicio,tarifaMensual,fechaInicio,fechaFin);
    }
}
