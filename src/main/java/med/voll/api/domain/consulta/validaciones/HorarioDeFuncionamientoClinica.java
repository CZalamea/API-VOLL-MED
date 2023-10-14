package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {


    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        var domingo = DayOfWeek.SUNDAY.equals(datosAgendarConsulta.data().getDayOfWeek());
        var antesDeApertura = datosAgendarConsulta.data().getHour()<7 ;
        var despuesDeCierre = datosAgendarConsulta.data().getHour()>19;

        if(domingo || antesDeApertura || despuesDeCierre){
            throw new ValidationException("El horario de atencion de la clinica es de lunes a sabados de 07:00 a 19:00 horas");

        }
    }
}
