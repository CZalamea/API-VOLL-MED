package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if(datosAgendarConsulta.idPaciente()==null){
            return;

        }

        var pacienteActivo = pacienteRepository.findActivoById(datosAgendarConsulta.idPaciente());

        if(!pacienteActivo){
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
        }

        var medicoActivo = medicoRepository.findActivoById(datosAgendarConsulta.idMedico());
    }
}
