package empresa.service;

import empresa.dto.ResultadoNuevoPago;
import empresa.dto.nuevoPago;
import empresa.model.Contrato;
import empresa.model.Pago;
import empresa.utils.HibernateUtil;
import empresa.utils.enums;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Logica {
    private static Logica instance;
    private Logica(){}

    public static Logica getInstance() {
        if(instance==null){
            instance=new Logica();
        }
        return instance;
    }

    public ResultadoNuevoPago registrarPago(nuevoPago nuevoPago){
    try (Session session = HibernateUtil.getSession()){
        session.beginTransaction();
        Contrato contrato = session.get(Contrato.class,nuevoPago.getIdContrato());
        if(contrato==null) {
        return  new ResultadoNuevoPago();
        }
        Pago pago = new Pago(contrato, LocalDate.now(),nuevoPago.getAmount());
        session.persist(pago);
        long mesesContrato = ChronoUnit.MONTHS.between(contrato.getFechaInicio(),contrato.getFechaFin());
        double totalEsperado = mesesContrato * contrato.getTarifaMensual();
        if(pago.getMonto()>=totalEsperado){
            contrato.setEstado(enums.estado.CANCELADO);
        } else if (contrato.getFechaFin().isBefore(LocalDate.now())) {
            contrato.setEstado(enums.estado.VENCIDO);
        }
        session.merge(contrato);
        session.getTransaction().commit();
        return new ResultadoNuevoPago(contrato.getId(), nuevoPago.getAmount(), contrato.getEstado());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }


}
