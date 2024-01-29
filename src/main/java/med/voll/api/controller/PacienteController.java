package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente, UriComponentsBuilder uriComponentsBuilder){

        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(),paciente.getNombre(),paciente.getEmail(),
                                                            paciente.getTelefono() , paciente.getDocumento(),
                                                                new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(),
                                                                    paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(), paciente.getDireccion().getComplemento()));

        URI url =  uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listarPacientes(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {

        //retorna solo pacientes activos
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map(DatosListaPaciente::new));

        //--retorna todos
        //return ResponseEntity.ok(pacienteRepository.findAll(paginacion).map(DatosListaPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@RequestBody DatosActualizarPaciente datosActualizarPaciente){

        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatosPacientes(datosActualizarPaciente);

        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(),paciente.getNombre(),paciente.getEmail(),
                                    paciente.getTelefono() , paciente.getDocumento(),
                                        new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(),
                                            paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(), paciente.getDireccion().getComplemento())));

    }

    /*
    //DELETE REAL CON PATH VARIABLES
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        pacienteRepository.delete(paciente);

    }*/

    //DELETE LOGICO SOLO DESACTIVA EL PACIENTE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> eliminarMedico(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);

        if (paciente.getActivo()) {
            paciente.desactivarPaciente();
        }else
            paciente.activarPaciente();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornaDatosPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);

        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(),paciente.getNombre(),paciente.getEmail(),
                                paciente.getTelefono() , paciente.getDocumento(),
                                    new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(),
                                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(), paciente.getDireccion().getComplemento()));

        return ResponseEntity.ok(datosPaciente);
    }
}
