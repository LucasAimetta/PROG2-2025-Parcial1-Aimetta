package empresa.service;

import empresa.dto.*;
import empresa.model.Contrato;
import empresa.model.Pago;
import empresa.utils.HibernateUtil;
import empresa.utils.enums;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Logica {
    private static Logica instance;
    private Logica(){}

    public static Logica getInstance() {
        if(instance==null){
            instance=new Logica();
        }
        return instance;
    }

    public ResultadoNuevoPago registrarPago(NuevoPago nuevoPago){
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


public List<ContratoDTO> obtenerContratos(FiltrosContratos filtrosContratos){
        try (Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Contrato> cq = cb.createQuery(Contrato.class);
            Root<Contrato> root = cq.from(Contrato.class);

            List<Predicate> predicates = new ArrayList<>();

            Predicate nombre = cb.like(root.get("nombreCliente"),"%" + filtrosContratos.getNombreCliente() + "%");
            predicates.add(nombre);

            if(filtrosContratos.getFechaInicioDesde()!=null && filtrosContratos.getFechaInicioHasta()!=null){
                Predicate fechas = cb.between(root.get("fechaInicio"),filtrosContratos.getFechaInicioDesde(),filtrosContratos.getFechaInicioHasta());
                predicates.add(fechas);
            }

            if(filtrosContratos.getTipoServicio()!=null){
                Predicate tipoServicio = cb.equal(root.get("tipoServicio"),filtrosContratos.getTipoServicio());
                predicates.add(tipoServicio);
            }
            if(filtrosContratos.getTarifaMensualDesde()>0 && filtrosContratos.getTarifaMensualHasta()>0 ){
                Predicate tarifaDesde = cb.gt(root.get("tarifaMensual"), filtrosContratos.getTarifaMensualDesde());
                predicates.add(tarifaDesde);
            }

            if( filtrosContratos.getTarifaMensualHasta()>0 ){
            Predicate tarifaHasta = cb.lt(root.get("tarifaMensual"), filtrosContratos.getTarifaMensualHasta());
            predicates.add(tarifaHasta);
            }

            cq.select(root).where(predicates.toArray(new Predicate[0])).orderBy(cb.desc(root.get("fechaInicio")));

            List<Contrato> contratos = session.createQuery(cq).list();
            List<ContratoDTO> contratoDTOS = new ArrayList<>();
            for(Contrato c:contratos){
                contratoDTOS.add(new ContratoDTO(c.getNombreCliente(),c.getTipoServicio(),c.getTarifaMensual(),c.getFechaInicio(),c.getFechaFin(),c.getEstado(),new ArrayList<>()));
            }
            return contratoDTOS;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}



    public List<ResumenContratosCancelados> obtenerResumenContratosCancelados(FiltrosContratos filtrosContratos){
        try (Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Contrato> root = cq.from(Contrato.class);

            if(filtrosContratos.getFechaInicioHasta()==null||filtrosContratos.getFechaInicioDesde()==null||filtrosContratos.getFechaInicioDesde().isAfter(filtrosContratos.getFechaInicioHasta()))
            {
                return new ArrayList<>();
            }


            Expression<Integer> diferenciaAños = cb.diff(
                    cb.function("YEAR", Integer.class, root.get("fechaFin")),
                    cb.function("YEAR", Integer.class, root.get("fechaInicio"))
            );

            Expression<Integer> diferenciaMeses = cb.diff(
                    cb.function("MONTH", Integer.class, root.get("fechaFin")),
                    cb.function("MONTH", Integer.class, root.get("fechaInicio"))
            );

            Expression<Integer> totalDiferenciaMeses = cb.sum(
                    cb.prod(diferenciaAños, cb.literal(12)),
                    diferenciaMeses
            );

            cq.multiselect(
                    root.get("tipoServicio"),
                    cb.count(root.get("id")),
                    cb.sum(
                            cb.prod(
                                    root.get("tarifaMensual"),totalDiferenciaMeses
                            )
                    )
            ).where(cb.equal(root.get("estado"),enums.estado.CANCELADO)).groupBy(root.get("tipoServicio"));

            List<Object[]> resultado = session.createQuery(cq).list();
            List<ResumenContratosCancelados> resumenContratosCancelados = new ArrayList<>();
            for(Object[] o : resultado){
                resumenContratosCancelados.add(new ResumenContratosCancelados((enums.tipoServicio) o[0], (Long) o[1],(double) o[2]));
            }
            return resumenContratosCancelados;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<ResumenContratosNoCancelados> obtenerResumenContratoNoCancelado(){
        try (Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Contrato> root = cq.from(Contrato.class);
            Join<Contrato,Pago> join = root.join("pagos" , JoinType.LEFT);

            Expression<Integer> diferenciaAños = cb.diff(
                    cb.function("YEAR", Integer.class, root.get("fechaFin")),
                    cb.function("YEAR", Integer.class, root.get("fechaInicio"))
            );

            Expression<Integer> diferenciaMeses = cb.diff(
                    cb.function("MONTH", Integer.class, root.get("fechaFin")),
                    cb.function("MONTH", Integer.class, root.get("fechaInicio"))
            );

            Expression<Integer> totalDiferenciaMeses = cb.sum(
                    cb.prod(diferenciaAños, cb.literal(12)),
                    diferenciaMeses
            );


            cq.multiselect(
                    cb.prod(root.get("tarifaMensual"), totalDiferenciaMeses),
                    cb.sum(join.get("monto")),
                    root.get("id")

            ).where(cb.notEqual(root.get("estado"),enums.estado.CANCELADO)).groupBy(root.get("id"));


            List<Object[]> resultado = session.createQuery(cq).list();
            List<ResumenContratosNoCancelados> resumenContratosNoCancelados = new ArrayList<>();
            for(Object[] o: resultado){
                resumenContratosNoCancelados.add(new ResumenContratosNoCancelados((Double) o[0], (Double) o[1],(int) o[2]));
            }
return resumenContratosNoCancelados;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
