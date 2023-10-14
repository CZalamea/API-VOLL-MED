package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        var primerHorario = datosAgendarConsulta.data().withHour(7);
        var ultimoHorario = datosAgendarConsulta.data().withHour(18);

        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndDataBetween(datosAgendarConsulta.idPaciente(),primerHorario, ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("el paciente ya tiene una consulta para ese dia");
        }

    }


}
