package empresa.service;

import empresa.dto.*;
import empresa.model.Contrato;
import empresa.model.Pago;
import empresa.utils.HibernateUtil;
import empresa.utils.enums;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

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
        pago1 = crearPago(contrato2,LocalDate.now(),150000000);
        contrato2.setEstado(enums.estado.CANCELADO);
        contrato2.getPagos().add(pago1);
        session.persist(pago1);
        session.merge(contrato2); //ASIGNO EL ESTADO A PATA, YA QUE NO DEBERIA IMPLEMENTAR EL METODO EN EL SET UP
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
@Test
    public void registrarPagoTest(){
        //ARRANGE
        NuevoPago nuevoPago = new NuevoPago(contrato1.getId(), 8000000); //EL TOTAL ESPERADO ES DE 1800000
        //ACT
      ResultadoNuevoPago resultadoNuevoPago = logica.registrarPago(nuevoPago);
        //ASSERT
        Assertions.assertEquals( enums.estado.CANCELADO,resultadoNuevoPago.getEstadoActualizado());
        Assertions.assertEquals(nuevoPago.getAmount(),resultadoNuevoPago.getMontoPagado());
        Assertions.assertEquals(contrato1.getId(), resultadoNuevoPago.getIdContrato());

    }

    @Test
    public void obtenerContratosTest(){
        //ARRANGE
        FiltrosContratos filtrosContratos = new FiltrosContratos("Lucas Aimetta",null,null,null,0,100000);
        //ACT
        List<ContratoDTO> contratos = logica.obtenerContratos(filtrosContratos);
        //ASSERT
        Assertions.assertEquals("Lucas Aimetta",contratos.getFirst().getNombreCliente());
        Assertions.assertEquals(1,contratos.size());
        Assertions.assertEquals(contratos.isEmpty(),false);
    }



    @Test
    public void obtenerResumenContratoCanceladoTest(){
        //ARRANGE
        FiltrosContratos filtrosContratos = new FiltrosContratos(null, null, LocalDate.of(2000,1,1), LocalDate.of(2028,1,1),0,180000);
        //ACT
        List<ResumenContratosCancelados>  resultado = logica.obtenerResumenContratosCancelados(filtrosContratos);

        //ASSERT
    Assertions.assertEquals( enums.tipoServicio.ELECTRICIDAD,resultado.getFirst().getTipoServicio());
    Assertions.assertEquals(1,resultado.getFirst().getCantidadContratos());
    }


    public static Pago crearPago(Contrato contrato, LocalDate fechaPago, double monto) {
     return new Pago(contrato,fechaPago,monto);
    }

    public static Contrato crearContrato(String nombreCliente, enums.tipoServicio tipoServicio, double tarifaMensual, LocalDate fechaInicio, LocalDate fechaFin){
        return new Contrato(nombreCliente,tipoServicio,tarifaMensual,fechaInicio,fechaFin);
    }
}
