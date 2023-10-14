package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PacienteConConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if(datosAgendarConsulta.idPaciente()==null)
            return;

        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndData(datosAgendarConsulta.idPaciente(), datosAgendarConsulta.data());

        if(pacienteConConsulta){
            throw new ValidationException("Este paciente ya tiene una consulta en ese horario");
        }


    }
}
