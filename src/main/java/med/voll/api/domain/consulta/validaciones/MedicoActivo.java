package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if(datosAgendarConsulta.idMedico()==null){
            return;
        }

        var medicoActivo = medicoRepository.findActivoById(datosAgendarConsulta.idMedico());
        if(!medicoActivo){
            throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
        }
    }
}
