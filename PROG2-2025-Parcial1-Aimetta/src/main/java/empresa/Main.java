package empresa;

import empresa.dto.FiltrosContratos;
import empresa.dto.ResumenContratosCancelados;
import empresa.service.Logica;

import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Logica logica = Logica.getInstance();
        FiltrosContratos filtrosContratos = new FiltrosContratos(null, null, LocalDate.of(2000,1,1), LocalDate.of(2028,1,1),0,180000);
        //ACT
        List<ResumenContratosCancelados> resultado = logica.obtenerResumenContratosCancelados(filtrosContratos);

    }
}