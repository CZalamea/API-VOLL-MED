package med.voll.api.domain.pacientes;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(Long id, String nombre, String email, String telefono, String documento,
                                     DatosDireccion direccion) {
}
